package org.ormi.stackorflow.domain.notification.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.ormi.stackorflow.infra.notification.NotificationRepository;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Getter
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    // 실시간 활동 알림 생성
    public NotificationResponse createRealTimeActivityNotification(String senderId, long articleId) {
        NotificationResponse dto = NotificationResponse.builder()
                .senderId(senderId)
                .receiverId("000000-0000-0000-0000-000000000000")
                .message(senderId + "님이 새로운 글을 올렸어요!")
                .domain("article")
                .target(articleId)
                .createdAt(ZonedDateTime.now())
                .build();


        return dto; // <- 이 코드는 지워야됨
    }

    // 내 질문에 대한 댓글 알림 생성
    public NotificationResponse createMyArticleCommentNotification(String senderId, String receiverId, long commentId) {
        NotificationResponse dto = NotificationResponse.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .message(receiverId + "님의 질문에 " + senderId + "님이 새로운 글을 올렸어요!")
                .domain("comment")
                .target(commentId)
                .createdAt(ZonedDateTime.now())
                .build();

        saveMyArticleCommentNotification(dto);

        // 소켓 호출 코드 들어갈 자리

        return dto; // <- 이 코드는 지워야됨
    }

    // 내 질문에 대한 댓글 알림 조회
    public List<NotificationResponse> findMyArticleNotifications(UUID receiverId) {
        return notificationRepository.findAllById(receiverId);
    }

    // 내 질문에 대한 댓글 알림 저장
    public void saveMyArticleCommentNotification(NotificationResponse dto) {
        notificationRepository.save(dto);
    }
}
