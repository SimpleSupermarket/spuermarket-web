package org.simplesupermarket.web.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @Time 2018/3/12 17:11
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);


    @Autowired
    UserVerificationHandle UserVerificationHandle;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置登录拦截验证跳转
        http.authorizeRequests()
                .antMatchers("/login/*", "/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/login").loginPage("/login/NoLogin")
                .failureForwardUrl("/login/loginNO").successForwardUrl("/login/loginOK")
                .passwordParameter("password").usernameParameter("username")
                .permitAll()//.defaultSuccessUrl().defaultSuccessUrl("/login/loginOK").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll().and();
        //关闭打开的csrf保护
        http.csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(userService);
        //设置登录验证规则为 UserVerificationConfig 类
        auth.authenticationProvider(UserVerificationHandle);
    }


    /****************去除权限验证的前缀********************/
    @Bean
    public SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setDefaultRolePrefix("");
        return defaultWebSecurityExpressionHandler;
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }

}
