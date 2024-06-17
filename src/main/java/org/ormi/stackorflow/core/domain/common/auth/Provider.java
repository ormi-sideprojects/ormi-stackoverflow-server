package org.ormi.stackorflow.core.domain.common.auth;

import com.querydsl.core.annotations.QueryProjection;
import java.util.Objects;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Provider provider)) {
      return false;
    }
    return Objects.equals(memberId, provider.memberId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberId);
  }
}
