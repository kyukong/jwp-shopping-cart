package woowacourse.auth.ui;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import woowacourse.auth.application.AuthService;
import woowacourse.auth.support.AuthorizationExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final AuthService authService;

    public LoginInterceptor(final AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        String accessToken = AuthorizationExtractor.extract(request);
        validateToken(accessToken);
        return super.preHandle(request, response, handler);
    }

    private void validateToken(final String accessToken) {
        if (accessToken == null || !authService.validateToken(accessToken)) {
            throw new IllegalArgumentException("로그인을 해주세요.");
        }
    }
}
