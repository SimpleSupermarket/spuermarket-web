package org.simplesupermarket.web.auth;


import org.simplesupermarket.web.app.service.OwnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 验证用户名与密码
 */
@RestController
@RequestMapping("/login")
public class UserVerificationHandle implements AuthenticationProvider {



    @Autowired
    private OwnService ownService;

    /**
     * 验证用户名密码
     * */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        if (password == null || password.isEmpty()) {
            return null;
        }
        UserDetails userDetials;
        try {
            userDetials = ownService.getManageUserLogin(username,password);
        } catch (UsernameNotFoundException e) {
            return null;
        }
        //TODO 获取用户权限列表
        // Collection<? extends GrantedAuthority> authorities = userDetials.getAuthorities();
      //  Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
       // authorities.add(new SimpleGrantedAuthority("管理员"));

        //判断用户密码是否正确
        if (userDetials != null  ) {
            return new UsernamePasswordAuthenticationToken(userDetials, password, userDetials.getAuthorities());
        } else {
            /*密码不正确*/
            return null;//new UsernamePasswordAuthenticationToken(userDetials,password,null);
        }
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }


    /**
     * 没有登录
     * @return 没有登录的内容
     */
    @RequestMapping("/NoLogin")
    public boolean noLogin(HttpServletResponse response) {
        response.setStatus(401);
        //TODO 没有登录
        return false;
    }


    /**
     * 登录失败URL
     * spring security 在登录失败时会跳转只当前url的POST
     * @return 失败是的返回值 false
     */
    @RequestMapping("/loginNO")
    public Boolean loginNO() {
        return false;
    }

    /**
     * 登录成功URL
     *
     * @return 成功时的返回值 true
     */
    @RequestMapping("/loginOK")
    public Boolean loginOK() {
        return true;
    }

}