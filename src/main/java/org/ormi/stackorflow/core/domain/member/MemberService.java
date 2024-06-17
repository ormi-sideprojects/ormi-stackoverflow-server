package org.ormi.stackorflow.core.domain.member;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.core.domain.common.RoleType;
import org.ormi.stackorflow.core.domain.common.exception.ResourceNotFoundException;
import org.ormi.stackorflow.infra.member.MemberEntity;
import org.ormi.stackorflow.infra.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public boolean existsBySessionId(String sessionId) {
		return memberRepository.findBySessionId(sessionId).isPresent();
	}

	@Transactional
	public MemberEntity createSession(String sessionId) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setSessionId(sessionId);
		memberEntity.setRole(RoleType.ANONYMOUS);

		return memberRepository.save(memberEntity);
	}

	@Transactional
	public MemberEntity getBySessionId(String sessionId) {
		return memberRepository.findBySessionId(sessionId)
			.orElseThrow(() -> new ResourceNotFoundException("sessionId", sessionId));
	}
}
