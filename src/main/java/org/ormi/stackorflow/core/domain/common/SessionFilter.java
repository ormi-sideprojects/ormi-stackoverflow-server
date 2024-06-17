package org.ormi.stackorflow.core.domain.common;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		try {
			HttpSession session = request.getSession(false);

			if (session == null) {
				// 세션이 없으면 세션 생성
				session = request.getSession(true);
				session.setAttribute(SessionConst.ANONYMOUS_SESSION, session);
//				log.info("New session created with ID: " + session.getId());
//				log.info("New session expired Date: " + session.getMaxInactiveInterval());
			} else {
				// 세션이 있으면 "ANONYMOUS_SESSION" 속성 확인 및 설정
				Object sessionAttribute = session.getAttribute(SessionConst.ANONYMOUS_SESSION);
				if (sessionAttribute == null) {
					session.setAttribute(SessionConst.ANONYMOUS_SESSION, session);
//					log.info("Existing session with ID: " + session.getId() + " and attribute set to existingSession");
				} else {
//					log.info("Existing session with ID: " + session.getId() + " and attribute: " + sessionAttribute);
				}
			}

			// 필터 체인을 통해 요청을 다음 필터로 전달하거나 서블릿으로 전달
			filterChain.doFilter(servletRequest, servletResponse);

		} catch (Exception e) {
			// 예외 발생 시 처리 로직
			// 세션 발급에 실패할 경우에는 서버 측 문제일 가능성이 높기 때문에 500에러를 반환한다.
//			log.info("Exception in SessionFilter: " + e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Internal Server Error: " + e.getMessage());
		}
	}
}
