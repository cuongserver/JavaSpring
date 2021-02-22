package demo.spring.config.pipeline;

import demo.spring.annotations.AllowAnonymous;
import demo.spring.utilities.JwtUtility;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final String AUTH_HEADER = "Authorization";
    private final JwtUtility jwtUtility;

    public AuthorizationInterceptor(JwtUtility jwtUtility) {
        this.jwtUtility = jwtUtility;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            var method = (HandlerMethod) handler;
            AllowAnonymous attr = method.getMethodAnnotation(AllowAnonymous.class);
            if (attr != null) return true;
            var token = request.getHeader(AUTH_HEADER);
            if (token == null) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("");
                return false;
            }
            var isTokenValid = jwtUtility.validateToken(token);
            if (!isTokenValid) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("");
                return false;
            }

            return true;
        } catch (ClassCastException e) {
            return true;
        }
    }
}
