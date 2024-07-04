package org.ormi.stackorflow.core.domain.common.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String datasource, String code) {
		super(datasource + "에서 발급받은 code인 " + code + "를 찾을 수 없습니다.");
	}
}
