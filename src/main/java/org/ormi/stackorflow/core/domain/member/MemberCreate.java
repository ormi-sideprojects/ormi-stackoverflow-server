package org.ormi.stackorflow.core.domain.member;


import lombok.Builder;
import lombok.Getter;
import org.ormi.stackorflow.core.domain.common.RoleType;
import org.ormi.stackorflow.infra.member.MemberEntity;

@Getter
@Builder
public class MemberCreate {

	private String id;
	private RoleType role;

	public MemberEntity toEntity() {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setId(this.id);
		memberEntity.setRole(this.role);

		return memberEntity;
	}
}

