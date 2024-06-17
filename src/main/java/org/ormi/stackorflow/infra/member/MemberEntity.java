package org.ormi.stackorflow.infra.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(name = "session_id")
	private String sessionId;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RoleType role;

	@OneToOne(mappedBy = "memberEntity")
	private StaffEntity staffEntity;

}
