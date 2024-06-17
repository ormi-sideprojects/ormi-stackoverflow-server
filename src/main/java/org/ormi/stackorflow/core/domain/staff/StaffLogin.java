package org.ormi.stackorflow.core.domain.staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StaffLogin {

	private final String certificationCode;

	@Builder
	public StaffLogin(@JsonProperty("certificationCode") String certificationCode) {
		this.certificationCode = certificationCode;
	}
}
