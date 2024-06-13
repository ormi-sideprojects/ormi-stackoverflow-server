package org.ormi.stackorflow.domain.notification.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
//    Notification findById(int receiverId);

    List<Notification> findByReceiverId(int receiverId);
}
