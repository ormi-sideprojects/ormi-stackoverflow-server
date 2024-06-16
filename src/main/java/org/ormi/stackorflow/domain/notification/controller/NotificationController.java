package org.ormi.stackorflow.domain.notification.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.ormi.stackorflow.domain.notification.service.NotificationService;
import org.ormi.stackorflow.infra.common.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
final class NotificationController {

    @Autowired
    private final NotificationService notificationService;

//     알림 리스트를 추가하는 기능
    @PostMapping
    public Responses<NotificationResponse> addNotification(@RequestBody NotificationResponse dto){

        notificationService.saveNotifications(dto);

        return Responses.created("알림 추가 성공!");
    }

    // 알림 리스트를 불러오는 기능(추후 인증객체를 통하는 방식으로 수정해야함)
    // type = 1 or 2 => type == 1일 경우 실시간 활동(게시글 작성 알림) / type == 2일 경우 내 질문(내 게시글에 대한 댓글 알림)
    @GetMapping("/me")
    public Responses<List<NotificationResponse>> findNotification (
            @RequestParam int receiverId,
            @RequestParam int type) {

        return Responses.created("알림 호출 성공", notificationService.getNotifications(receiverId, type));
    }

}
