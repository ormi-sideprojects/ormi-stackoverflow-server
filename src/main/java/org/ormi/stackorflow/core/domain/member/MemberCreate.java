package org.ormi.stackorflow.core.domain.member;


import lombok.Builder;
import lombok.Getter;
import org.ormi.stackorflow.core.domain.common.auth.RoleType;

@Getter
public class MemberCreate {

	private String id;
	private RoleType role;

	@Builder
	public MemberCreate(String id, RoleType role) {
		this.id = id;
		this.role = role;
	}
}
