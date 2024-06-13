package org.ormi.stackorflow.infra.notification;

import jakarta.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;

    public NotificationRepository(NotificationJpaRepository notificationJpaRepository) {
        this.notificationJpaRepository = notificationJpaRepository;
    }

    public void save(@Valid NotificationResponse notificationResponse) {

        NotificationEntity notificationEntity = NotificationEntity.builder()
                .receiverId(notificationResponse.getReceiverId())
                .senderId(notificationResponse.getSenderId())
                .message(notificationResponse.getMessage())
                .original(notificationResponse.getOriginal())
                .target(notificationResponse.getTarget())
                .createdAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();

        notificationJpaRepository.save(notificationEntity);
    }

//    public Notification findById(int receiverId) {
//        return notificationRepository.findById(receiverId);
//    }

    public List<NotificationResponse> findAllById(int receiverId) {

        List<NotificationEntity> notificationEntities =
                notificationJpaRepository.findByReceiverId(receiverId);

        List<NotificationResponse> notificationResponses = new ArrayList<>();
        for (NotificationEntity notificationEntity : notificationEntities) {
            NotificationResponse notificationResponse = NotificationResponse.builder()
                    .SenderId(notificationEntity.getSenderId())
                    .target(notificationEntity.getTarget())
                    .createdAt(notificationEntity.getCreatedAt())
                    .message(notificationEntity.getMessage())
                    .original(notificationEntity.getOriginal())
                    .build();

            notificationResponses.add(notificationResponse);
        }

        return notificationResponses;
    }

    public void deleteById(long id) {
    }
}
// 리스트를 먼저 불러오고(API) 그 다음 소켓으로 실시간 알림
