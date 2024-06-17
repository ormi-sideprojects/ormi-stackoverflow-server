package org.ormi.stackorflow.core.domain.common;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<SessionFilter> loggingFilter() {
		FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new SessionFilter());
		registrationBean.addUrlPatterns("/api/v1"); // 필터를 적용할 URL 패턴 설정
		registrationBean.setOrder(1); // 필터의 순서 설정 (낮은 숫자가 높은 우선순위)

		return registrationBean;
	}
}
