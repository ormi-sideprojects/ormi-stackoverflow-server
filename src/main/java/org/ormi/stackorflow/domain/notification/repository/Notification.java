package org.ormi.stackorflow.domain.notification.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int receiverId;
    int SenderId;
    int target; // original article id
    @Column(name = "original")
    String original; // comment or article
    String message;
    ZonedDateTime createdAt;
    int test;

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "id=" + id +
                ", receiverId=" + receiverId +
                ", SenderId=" + SenderId +
                ", target='" + target + '\'' +
                ", original='" + original + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
