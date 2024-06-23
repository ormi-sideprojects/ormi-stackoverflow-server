package org.ormi.stackorflow.domain.notification.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.ormi.stackorflow.domain.socket.controller.WebSocketGateway;
import org.ormi.stackorflow.infra.notification.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;
    private final WebSocketGateway webSocketGateway;

    // 실시간 활동 알림 생성
    public void createRealTimeActivityNotification(UUID senderId, long articleId, String staffNickname) {
        NotificationResponse dto = NotificationResponse.builder()
                .senderId(senderId)
                .receiverId(null)
                .message(staffNickname + "님이 새로운 글을 올렸어요!")
                .domain("article")
                .target(articleId)
                .createdAt(ZonedDateTime.now())
                .build();

        webSocketGateway.handleNotification(dto);
    }

    // 내 질문에 대한 댓글 알림 생성
    public void createMyArticleCommentNotification(UUID senderId, UUID receiverId, long commentId, String staffNickname) {
        NotificationResponse dto = NotificationResponse.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .message("당신의 질문에 " + staffNickname + "님이 답변했습니다.")
                .domain("comment")
                .target(commentId)
                .createdAt(ZonedDateTime.now())
                .build();

        saveMyArticleCommentNotification(dto);

        webSocketGateway.handleNotification(dto);
    }

    // 내 질문에 대한 댓글 알림 조회
    public List<NotificationResponse> findMyArticleNotifications(UUID receiverId) {
        return notificationRepository.findByReceiverId(receiverId);
    }

    // 내 질문에 대한 댓글 알림 저장
    public void saveMyArticleCommentNotification(NotificationResponse dto) {
        notificationRepository.save(dto);
    }
}
