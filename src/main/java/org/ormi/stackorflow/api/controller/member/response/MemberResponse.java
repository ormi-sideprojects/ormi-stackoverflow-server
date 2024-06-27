package org.ormi.stackorflow.api.controller.member.response;

import lombok.Getter;
import lombok.Setter;
import org.ormi.stackorflow.core.domain.common.RoleType;
import org.ormi.stackorflow.infra.member.MemberEntity;

@Getter
@Setter
public class MemberResponse {

	private String id;
	private RoleType role;

	public static MemberResponse fromEntity(MemberEntity memberEntity) {
		MemberResponse response = new MemberResponse();
		response.setId(memberEntity.getId());
		response.setRole(memberEntity.getRole());
		return response;
	}
}