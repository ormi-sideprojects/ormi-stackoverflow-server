package org.ormi.stackorflow.infra.staff;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.ormi.stackorflow.core.domain.common.auth.RoleType;
import org.ormi.stackorflow.infra.member.MemberEntity;

@Entity
@Getter
@Setter
@Table(name = "staffs")
public class StaffEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	private int id;

	@OneToOne
	@JoinColumn(name = "member_id")
	private MemberEntity memberEntity;

	@Column(name = "code")
	private String code;

	@Column(name = "nickname")
	private String nickname;

	@Enumerated(EnumType.STRING)
	@Column(name = "staff_role")
	private RoleType staffRole;


	public void updateMemberRole() {
		this.memberEntity.setRole(RoleType.STAFF);
	}
}
