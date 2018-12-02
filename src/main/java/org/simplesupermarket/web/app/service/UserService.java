package org.simplesupermarket.web.app.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.agile.campusnews.data.dao.UserMapper;
import team.agile.campusnews.data.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 董文强
 * @Time 2018/5/3 14:15
 */
@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    private UserMapper userMapper;

    private SimpleDateFormat dateFormat;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            // 从数据库查找用户

            if (username == null || username.isEmpty()) {
                throw new UsernameNotFoundException("用户名不能为空");
            }
            user = userMapper.selectByUserName(username);
            if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
                throw new UsernameNotFoundException("用户不存在");
            }


        } catch (Exception e) {
            throw  e;
        }
        return user;
    }

    public boolean addUser(User user) {

        //  user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
           /* if (userMapper.insert(user) == 1) {
                return true;
            }*/
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean alert(User user) {

        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
           /* if(userMapper.updateByPrimaryKeySelective(user) == 1){
                return true;
            }*/
            return false;
        } catch (Exception e) {
            return false;
        }
        // return false;
    }

    /**
     * 注册 TODO
     */
    @Transactional
    public Boolean regUser(User user, String classId, String role, String schoolTime) throws ParseException {
        user.setId(null);
        userMapper.insert(user);
        Integer id = user.getId();
        userMapper.regUserR(id, role);
        userMapper.regUserS(id, Integer.parseInt(classId));
        Date date = dateFormat.parse(schoolTime);
        userMapper.regUserStu(user.getCode(), id, date);
        return false;
    }

    public List getSchoolOSUser(Integer schhoID) {
        return userMapper.selectBySchoolId(schhoID);
    }
}
