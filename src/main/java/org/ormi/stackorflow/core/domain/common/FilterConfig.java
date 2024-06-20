package org.ormi.stackorflow.core.domain.common;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<AnonymousMemberFilter> customAnonymousMemberFilter() {
		FilterRegistrationBean<AnonymousMemberFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new AnonymousMemberFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1); // 필터 순서 설정

		return registrationBean;
	}
}
