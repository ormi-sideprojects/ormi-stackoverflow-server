package org.ormi.stackorflow.domain.notification.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.ormi.stackorflow.domain.notification.service.NotificationService;
import org.ormi.stackorflow.infra.common.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
final class NotificationController {

    @Autowired
    private final NotificationService notificationService;

    // 실시간 활동에 대한 알림 리스트를 불러오는 기능
    @GetMapping("/real-time")
    public Responses<List<NotificationResponse>> findReaTimeActivityNotification(UUID receiverId) {

        // 웹소켓으로 받아오는 코드 작성 해야됨

        return Responses.ok("실시간 활동 알림 호출 성공", notificationService.findMyArticleNotifications(receiverId));
    }

    // 내 질문에 대한 댓글알림 리스트를 불러오는 기능
    @GetMapping("/me")
    public Responses<List<NotificationResponse>> findMyArticleNotification(UUID receiverId) {

        // 웹소켓으로 받아오는 코드 작성 해야됨

        return Responses.ok("내 질문 알림 호출 성공", notificationService.findMyArticleNotifications(receiverId));
    }

    @PostMapping("test/real")
    public Responses<NotificationResponse> addReaTimeActivityNotification(
            @RequestBody NotificationResponse dto
    ) {
        return Responses.created("실시간 활동 알림 생성 성공",
                notificationService.createRealTimeActivityNotification(
                        dto.getSenderId(), dto.getTarget()));
    }

    @PostMapping("test/me")
    public Responses<NotificationResponse> addMyArticleCommentNotification(
            @RequestBody NotificationResponse dto
    ) {
        return Responses.created("실시간 활동 알림 생성 성공",
                notificationService.createMyArticleCommentNotification(
                        dto.getSenderId(), dto.getReceiverId(), dto.getTarget()));
    }

}
