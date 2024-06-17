package org.ormi.stackorflow.core.domain.common;

import com.querydsl.core.annotations.QueryProjection;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Provider {
  private final UUID memberId;
  private ProviderRole role;
  @QueryProjection
  public Provider(UUID memberId) {
    this.memberId = memberId;
  }
}
