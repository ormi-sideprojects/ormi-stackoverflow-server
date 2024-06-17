package org.ormi.stackorflow.core.domain.staff;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.infra.staff.StaffRepository;
import org.ormi.stackorflow.infra.member.MemberRepository;
import org.ormi.stackorflow.infra.staff.StaffEntity;
import org.ormi.stackorflow.infra.member.MemberEntity;
import org.ormi.stackorflow.core.domain.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffService {

	private final StaffRepository staffRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public StaffEntity getByCode(String certificationCode) {
		return staffRepository.findByCode(certificationCode)
			.orElseThrow(() -> new ResourceNotFoundException("Staff", certificationCode));
	}

	@Transactional
	public StaffEntity update(StaffEntity staffEntity, String sessionId) {

		MemberEntity memberEntity = memberRepository.findBySessionId(sessionId).get();
		staffEntity.setMemberEntity(memberEntity);
		staffEntity.updateMemberRole();

		return staffEntity;
	}
}
