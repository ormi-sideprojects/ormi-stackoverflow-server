package org.ormi.stackorflow.core.domain.common;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AnonymousMemberFilter implements Filter {

	@Override
	public void doFilter(
		ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

		String anonymousMemberId = CookieUtil.getCookieValue(httpRequest, CookieUtil.ANONYMOUS_MEMBER_COOKIE_NAME);

		// 쿠키가 없는 경우 쿠키 발급
		if (anonymousMemberId == null) {
			String cookieId = UUID.randomUUID().toString();
			Cookie anonymousUserCookie = new Cookie(CookieUtil.ANONYMOUS_MEMBER_COOKIE_NAME, cookieId);
			anonymousUserCookie.setPath("/");
			anonymousUserCookie.setHttpOnly(true);
			anonymousUserCookie.setMaxAge(-1); // 쿠키 유효기간 지정 X
			httpResponse.addCookie(anonymousUserCookie);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}
}
