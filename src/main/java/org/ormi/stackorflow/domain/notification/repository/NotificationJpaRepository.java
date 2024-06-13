package org.ormi.stackorflow.domain.notification.repository;

import jakarta.validation.Valid;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationJpaRepository {

    private final NotificationRepository notificationRepository;

    public NotificationJpaRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void save(@Valid NotificationResponse notificationResponse) {
        Notification notification = new Notification();
        notification.setId(notificationResponse.getId());
        notification.setReceiverId(notificationResponse.getReceiverId());
        notification.setSenderId(notificationResponse.getSenderId());
        notification.setMessage(notificationResponse.getMessage());
        notification.setOriginal(notificationResponse.getOriginal());
        notification.setTarget(notificationResponse.getTarget());
        ZonedDateTime nowSeoul = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        notification.setCreatedAt(nowSeoul);
        notificationRepository.save(notification);
    }

//    public Notification findById(int receiverId) {
//        return notificationRepository.findById(receiverId);
//    }

    public List<Notification> findAllById(int receiverId) {
        return notificationRepository.findByReceiverId(receiverId);
    }

    public void deleteById(long id) {
    }
}
// 리스트를 먼저 불러오고(API) 그 다음 소켓으로 실시간 알림
