package org.ormi.stackorflow.domain.notification.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.ormi.stackorflow.domain.notification.service.NotificationService;
import org.ormi.stackorflow.infra.common.Responses;
import org.ormi.stackorflow.infra.notification.NotificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
final class NotificationController {

    @Autowired
    private final NotificationService notificationService;
    private final ModelMapper modelMapper;

    // 내 질문에 대한 댓글 알림 리스트를 불러오는 기능
    @GetMapping("/me")
    public Responses<List<NotificationResponse>> findMyArticleNotification(@RequestParam UUID receiverId) {

        List<NotificationEntity> dto = notificationService.findMyArticleNotifications(receiverId);

        List<NotificationResponse> notificationResponses = dto.stream()
                .map(data -> modelMapper.map(data, NotificationResponse.class))
                .collect(Collectors.toList());

        return Responses.ok("내 질문 알림 호출 성공", notificationResponses);
    }

}
