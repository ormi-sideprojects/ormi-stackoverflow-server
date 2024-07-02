package org.ormi.stackorflow.infra.notification;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.ormi.stackorflow.domain.notification.dto.NotificationResponse;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;
    private final ModelMapper modelMapper;

    public NotificationRepository(NotificationJpaRepository notificationJpaRepository, ModelMapper modelMapper) {
        this.notificationJpaRepository = notificationJpaRepository;
        this.modelMapper = modelMapper;
    }

    public void save(NotificationEntity dto) {

        notificationJpaRepository.save(dto);
    }

    public List<NotificationEntity> findByReceiverId(UUID receiverId) {

        return notificationJpaRepository.findByReceiverId(receiverId);

//        return notificationEntities.stream()
//                .map(data -> modelMapper.map(data, NotificationResponse.class))
//                .collect(Collectors.toList());
    }
}
