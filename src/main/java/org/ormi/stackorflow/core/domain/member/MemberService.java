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

	@Transactional
	public MemberEntity findOrCreateMember(String cookieValue) {

		return memberRepository.findById(cookieValue).orElseGet(() -> {
			MemberCreate memberCreate = MemberCreate.builder()
				.id(cookieValue)
				.role(RoleType.ANONYMOUS)
				.build();
			return memberRepository.save(memberCreate.toEntity());
		});
	}

}
