package org.ormi.stackorflow.domain.notification.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.ormi.stackorflow.infra.notification.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Getter
public class NotificationService {

    private final NotificationRepository notificationJpaRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // 알림 저장 메소드
    public void saveNotifications(NotificationResponse notificationResponse) {
        if (notificationResponse.getDomain().equals("article")) {
            notificationResponse.setReceiverId(0);
        }

        notificationJpaRepository.save(notificationResponse);
    }

    public List<NotificationResponse> getNotifications(int receiverId, int type) {
        // 누군가 게시글을 작성했을 때
        if (type == 1) {
            return notificationJpaRepository.findAllById(0);
        }

        // 누군가 내 질문에 답변글을 남겼을 때
        if (type == 2) {
            return notificationJpaRepository.findAllById(receiverId);
        }
        return null;
    }

    // 알림 삭제 메소드
//    private void deleteNotice(long id) {
//        notificationJpaRepository.deleteById(id);
//    }
}
