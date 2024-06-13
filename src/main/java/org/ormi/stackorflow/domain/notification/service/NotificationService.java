package org.ormi.stackorflow.domain.notification.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.ormi.stackorflow.domain.notification.repository.Notification;
import org.ormi.stackorflow.domain.notification.repository.NotificationJpaRepository;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Getter
public class NotificationService {

//    private List<Notification> notifications;

    private NotificationJpaRepository notificationJpaRepository;

    // 1. DB에 저장된 것을 1분? 주기로 끌어오는 방법
    // 2. 웹소켓을 사용하여 실시간으로 알림을 끌어오는 방법

    // 알림 추가 메소드
//    public void addNotification(Notification notification) {
//
//        saveNotifications(notification);
//    }

    // 알림 저장 메소드
    public void saveNotifications(NotificationResponse notificationResponse) {
        notificationJpaRepository.save(notificationResponse);
    }

    // 알림을 불러오는 메소드

//    public Notification getNotification(int receiverId) {
//        return notificationJpaRepository.findById(receiverId);
//    }

    public List<Notification> getNotifications(int receiverId) {
        return notificationJpaRepository.findAllById(receiverId);
    }

    // 알림 삭제 메소드
    private void deleteNotice(long id) {
        notificationJpaRepository.deleteById(id);
    }
}
