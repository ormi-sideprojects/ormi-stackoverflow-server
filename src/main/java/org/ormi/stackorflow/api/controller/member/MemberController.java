package org.ormi.stackorflow.api.controller.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.api.controller.member.response.MemberResponse;
import org.ormi.stackorflow.core.domain.member.MemberService;
import org.ormi.stackorflow.infra.common.Responses;
import org.ormi.stackorflow.infra.member.MemberEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/check-session")
	public Responses<MemberResponse> checkSession(HttpSession httpSession) {

		// 넘어온 세션 값이 DB에 존재한다면, 그냥 가져오고 없으면 insert
		String sessionId = httpSession.getId();

		if (!memberService.existsBySessionId(sessionId)) {
			// 여기서 없으면 insert
			return Responses.ok("사용자 세션 발급 성공", toMemberResponse(memberService.createSession(sessionId)));
		}

		// 있는 경우에 대한 로직
		return Responses.ok("사용자 세션 가져오기 성공", toMemberResponse(memberService.getBySessionId(sessionId)));
	}

	public MemberResponse toMemberResponse(MemberEntity memberEntity) {
		MemberResponse memberResponse = new MemberResponse();
		memberResponse.setId(memberEntity.getId());
		memberResponse.setSessionId(memberEntity.getSessionId());
		memberResponse.setCode(memberEntity.getRole());

		return memberResponse;
	}

}
