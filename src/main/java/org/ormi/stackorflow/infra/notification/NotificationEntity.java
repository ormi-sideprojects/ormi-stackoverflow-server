package org.ormi.stackorflow.infra.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "notifications")
public final class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int receiverId;
    int senderId;
    int target; // original article id
    String domain; // comment or article
    String message;
    ZonedDateTime createdAt;
}
