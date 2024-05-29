package org.ormi.stackorflow.infra.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ormi.stackorflow.domain.common.Role;
import org.ormi.stackorflow.infra.common.BaseEntity;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class UserEntity extends BaseEntity {

  @Id
  @GeneratedValue
  private long id;
  private String nickname;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  public UserEntity(long id, String nickname, Role role) {
    this.id = id;
    this.nickname = nickname;
    this.role = role;
  }

  public UserEntity(ZonedDateTime createdAt, ZonedDateTime updatedAt, long id, String nickname,
      Role role) {
    super(createdAt, updatedAt);
    this.id = id;
    this.nickname = nickname;
    this.role = role;
  }
}
