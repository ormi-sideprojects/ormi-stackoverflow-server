package org.ormi.stackorflow.infra.common;

import jakarta.persistence.MappedSuperclass;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract public class BaseEntity {

  @CreationTimestamp
  protected ZonedDateTime createdAt;
  @UpdateTimestamp
  protected ZonedDateTime updatedAt;

  public BaseEntity(ZonedDateTime createdAt, ZonedDateTime updatedAt) {
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
