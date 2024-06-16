package org.ormi.stackorflow.domain.notification.dto;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

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

