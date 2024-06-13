package org.ormi.stackorflow.domain.notification.dto;

import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public final class NotificationResponse {

    int id;
    int receiverId;
    int SenderId;
    int target; // original article id
    String original; // comment or article
    String message;
    ZonedDateTime createdAt;

}

