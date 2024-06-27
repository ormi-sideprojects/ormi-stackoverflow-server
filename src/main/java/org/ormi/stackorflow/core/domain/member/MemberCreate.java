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

		return memberEntity.create(this);
	}

	public static MemberCreate anonymous(String cookieValue) {
		return MemberCreate.builder()
			.id(cookieValue)
			.role(RoleType.ANONYMOUS)
			.build();
	}
}

