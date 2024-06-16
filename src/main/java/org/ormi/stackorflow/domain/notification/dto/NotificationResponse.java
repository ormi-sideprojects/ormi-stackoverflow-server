package org.ormi.stackorflow.domain.notification.dto;

import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ormi.stackorflow.infra.notification.NotificationEntity;

@Getter
@Setter
public final class NotificationResponse {

    int id;
    int receiverId;
    int senderId;
    int target; // original article id
    String domain; // comment or article
    String message;
    ZonedDateTime createdAt = ZonedDateTime.now();

}

