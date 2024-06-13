package org.ormi.stackorflow.domain.notification.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.ormi.stackorflow.domain.notification.repository.Notification;
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
    public final <U> Responses<U> addNotification(@RequestBody NotificationResponse notificationResponse){

        notificationService.saveNotifications(notificationResponse);

        return Responses.created("알림 추가 성공!");
    }

    // 알림 리스트를 불러오는 기능(추후 인증객체를 통하는 방식으로 수정해야함)
    @GetMapping("/test")
    public final <U> Responses<List<Notification>> findNotification(@RequestParam int receiverId) {
        return Responses.created("알림 호출 성공", notificationService.getNotifications(receiverId));
    }

}
