package org.ormi.stackorflow.domain.notification.dto;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public final class NotificationResponse {

    long id;
    String receiverId;
    String senderId;
    long target; // original article id
    String domain; // comment or article
    String message;
    ZonedDateTime createdAt;

}

