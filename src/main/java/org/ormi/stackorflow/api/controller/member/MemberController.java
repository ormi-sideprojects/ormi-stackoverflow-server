package org.ormi.stackorflow.api.controller.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.api.controller.member.response.MemberResponse;
import org.ormi.stackorflow.core.domain.common.CookieUtil;
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
	public Responses<MemberResponse> checkSession(HttpServletRequest request) {

		// 넘어온 쿠키 값이 DB에 존재하면 가져오고 없으면 insert
		String anonymousMemberId = CookieUtil.getCookieValue(request, CookieUtil.ANONYMOUS_MEMBER_COOKIE_NAME);

		return Responses.ok("사용자 쿠키 등록 성공", MemberResponse.fromEntity(memberService.findOrCreateMember(anonymousMemberId)));
	}

}

