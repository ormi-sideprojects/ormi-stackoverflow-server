package org.ormi.stackorflow.core.domain.member;


import jakarta.transaction.Transactional;
import java.util.Optional;
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

	public MemberEntity findOrCreateMember(String cookieValue) {
		Optional<MemberEntity> memberEntityOptional = memberRepository.findById(cookieValue);

		if (memberEntityOptional.isPresent()) {
			return memberEntityOptional.get();
		}

		MemberCreate memberCreate = MemberCreate.anonymous(cookieValue);

		return memberRepository.save(memberCreate.toEntity());
	}

}
