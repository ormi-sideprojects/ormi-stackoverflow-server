package org.ormi.stackorflow.infra.notification;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Integer> {
//    Notification findById(int receiverId);

    List<NotificationEntity> findByReceiverId(int receiverId);

}
