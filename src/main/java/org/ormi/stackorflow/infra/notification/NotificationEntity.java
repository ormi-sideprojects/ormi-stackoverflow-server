package org.ormi.stackorflow.infra.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "notifications")
public final class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int receiverId;
    int senderId;
    int target; // original article id
    String original; // comment or article
    String message;
    ZonedDateTime createdAt;

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "id=" + id +
                ", receiverId=" + receiverId +
                ", senderId=" + senderId +
                ", target='" + target + '\'' +
                ", original='" + original + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
