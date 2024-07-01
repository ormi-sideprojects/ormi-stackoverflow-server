package org.ormi.stackorflow.core.domain.common;

import com.querydsl.core.annotations.QueryProjection;
import java.time.ZonedDateTime;

public record Timestamps(ZonedDateTime createdAt, ZonedDateTime updatedAt) {
  @QueryProjection
  public Timestamps{}
}
