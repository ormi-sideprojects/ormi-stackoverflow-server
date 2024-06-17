package org.ormi.stackorflow.api.controller.member.response;

import lombok.Getter;
import lombok.Setter;
import org.ormi.stackorflow.core.domain.common.RoleType;

@Getter
@Setter
public class MemberResponse {

	private Long id;
	private String sessionId;
	private RoleType code;
}
