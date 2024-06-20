package org.ormi.stackorflow.api.controller.staff;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.api.controller.staff.response.StaffResponse;
import org.ormi.stackorflow.core.domain.common.CookieUtil;
import org.ormi.stackorflow.core.domain.staff.StaffLogin;
import org.ormi.stackorflow.core.domain.staff.StaffService;
import org.ormi.stackorflow.infra.common.Responses;
import org.ormi.stackorflow.infra.staff.StaffEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StaffController {

	private final StaffService staffService;

	@PostMapping("/auth/staff/login")
	public Responses<StaffResponse> staffLogin(@RequestBody StaffLogin staffLoginDto, HttpServletRequest request) {

		// 요청으로 들어온 인증코드로 해당 스태프를 조회한다.
		StaffEntity beforeStaffLogin = staffService.getByCode(staffLoginDto.getCertificationCode());

		// 스탭 로그인 시, 쿠키 값을 memberId에 넣어준다.
		String anonymousMemberId = CookieUtil.getCookieValue(request, CookieUtil.ANONYMOUS_MEMBER_COOKIE_NAME);

		StaffEntity staffEntity = staffService.update(beforeStaffLogin, anonymousMemberId);

		return Responses.ok("성공적으로 스탭의 정보를 가져왔습니다.", toStaffResponse(staffEntity));
	}

	public StaffResponse toStaffResponse(StaffEntity staffEntity) {
		StaffResponse staffResponse = new StaffResponse();
		staffResponse.setId(staffEntity.getId());
		staffResponse.setMemberId(staffEntity.getMemberEntity().getId());
		staffResponse.setCode(staffEntity.getCode());
		staffResponse.setNickname(staffEntity.getNickname());
		staffResponse.setStaffRole(staffEntity.getStaffRole());

		return staffResponse;
	}

}
