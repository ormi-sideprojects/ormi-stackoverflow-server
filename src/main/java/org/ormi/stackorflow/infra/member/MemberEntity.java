package org.ormi.stackorflow.infra.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.ormi.stackorflow.core.domain.common.RoleType;
import org.ormi.stackorflow.infra.staff.StaffEntity;

@Entity
@Getter
@Setter
@Table(name = "members")
public class MemberEntity {

	@Id
	@Column(name = "member_id")
	private String id;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RoleType role;

	@OneToOne(mappedBy = "memberEntity", fetch = FetchType.LAZY)
	private StaffEntity staffEntity;

	public void updateRole(RoleType role) {
		this.role = role;
	}
}

