package org.simplesupermarket.web.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 董文强
 * @Time 2018/12/8 1:07
 */
public class LoginAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuthenticationEntryPoint.class);

    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public LoginAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }


    /**
     * Performs the redirect (or forward) to the login form URL.
     */
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(401);
        //response.setCharacterEncoding("ISO-8859-1");
        response.getWriter().println("NoLongin");
    }
}
