package org.ormi.stackorflow.api.controller.staff.response;

import lombok.Getter;
import lombok.Setter;
import org.ormi.stackorflow.core.domain.common.auth.RoleType;

@Getter
@Setter
public class StaffResponse {

	private int id;
	private Long memberId;
	private String code;
	private String nickname;
	private RoleType staffRole;
}
