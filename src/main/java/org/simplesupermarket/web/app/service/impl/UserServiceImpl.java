package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.domain.UserView;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.OwnService;
import org.simplesupermarket.web.app.service.UserService;
import org.simplesupermarket.web.app.domain.annotation.ViewClass;
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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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
        if (user == null) return null;
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(user, userDetail);
        List<Role> roles = new ArrayList<>(1);
        roles.add(roleMapper.selectByPrimaryKey(user.getRoleId()));
        userDetail.setRoles(roles);
        userDetail.setPassword("nonono~~~~~~~~");
        return userDetail;
    }

    @Override
    @ViewClass(UserView.class)
    public List getDbData(Map<String, String> sd) {
        String name = sd.get("name");
        if (StringUtils.isEmpty(name) || name.length() > 100) {
            name = null;
        }
        return getList(name);
    }

    public List getList(String name) {
        return userMapper.selectAll(name, null);
    }

    @Override
    public Boolean repassword(String oldPassword, String newPassword, Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
            userMapper.updateByPrimaryKeySelective(user);
            return true;
        }
        return false;
    }
}
