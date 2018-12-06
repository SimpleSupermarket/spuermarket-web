package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.domain.UserView;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.OwnService;
import org.simplesupermarket.web.app.service.UserService;
import org.simplesupermarket.web.auth.UserDetail;
import org.simplesupermarket.web.db.mapper.RoleMapper;
import org.simplesupermarket.web.db.mapper.UserMapper;
import org.simplesupermarket.web.db.model.Role;
import org.simplesupermarket.web.db.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @date 2018年12月03日
 */
@Service
public class UserServiceImpl extends AbstractSuperServiceImpl<User> implements OwnService, UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails getManageUserLogin(String username, String password) {
        List<User> userList = userMapper.selectAll(null, username);
        User user = userList.stream()
                .filter(v -> v.getCode().equals(username))
                .filter(v -> v.getPassword().equals(password))
                .filter(v -> v.getState().equals(0))
                .findFirst().orElse(null);
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(user, userDetail);
        List<Role> roles = new ArrayList<>(1);
        roles.add(roleMapper.selectByPrimaryKey(user.getRoleId()));
        userDetail.setRoles(roles);
        return userDetail;
    }

    @Override
    public List getList(Map<String, String> sd) {
        String name = sd.get("name");
        if (StringUtils.isEmpty(name) || name.length() > 100) {
            name = null;
        }
        return getList(name);
    }

    public List getList(String name) {
        List<UserView> list = new Vector<>();
        List<User> userList = userMapper.selectAll(name, null);
        Map<Long, UserView> map = new ConcurrentHashMap();
        Map<Long, Long> mapUser = new ConcurrentHashMap();
        Map<Long, Long> mapRole = new ConcurrentHashMap();
        userList.forEach(user -> {
            UserView userView = new UserView();
            BeanUtils.copyProperties(user, userView);
            userView.setCreationdate(super.format.format(user.getCreationdate()));
            userView.setGender(user.getGender() == 1 ? "女" : "男");
            userView.setState(user.getState() == 0 ? "正常" : "停用");
            userView.setPassword("不可查看密码");
            mapUser.put(user.getCreatedby(), user.getId());
            mapRole.put(user.getRoleId(), user.getId());
            map.put(user.getId(), userView);
            list.add(userView);
        });

        userMapper.selectByIds(
                Arrays.asList(mapUser.keySet().toArray(new Long[0])))
                .forEach(user -> {
                    Long mapId = mapUser.get(user.getId());
                    map.get(mapId).setCreatedby(user);
                });
        roleMapper.selectByIds(
                Arrays.asList(mapUser.keySet().toArray(new Long[0])))
                .forEach(role -> {
                    Long mapId = mapUser.get(role.getId());
                    map.get(mapId).setRoleId(role);
                });
        return new ArrayList(map.values());
    }
}
