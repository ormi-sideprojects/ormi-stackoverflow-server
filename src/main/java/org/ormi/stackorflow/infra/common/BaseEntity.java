package org.ormi.stackorflow.infra.common;

import jakarta.persistence.MappedSuperclass;
import java.time.ZonedDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
abstract public class BaseEntity {
  @CreationTimestamp
  protected ZonedDateTime createdAt;
  @UpdateTimestamp
  protected ZonedDateTime updatedAt;
}
