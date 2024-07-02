package org.ormi.stackorflow.core.domain.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

public class CookieUtil {

	public static final String ANONYMOUS_MEMBER_COOKIE_NAME = "ANONYMOUS_MEMBER";

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}

		return Stream.of(cookies)
			.map(Cookie::getName)
			.filter(cookieName::equals)
			.findAny()
			.orElse(null);
	}
}
