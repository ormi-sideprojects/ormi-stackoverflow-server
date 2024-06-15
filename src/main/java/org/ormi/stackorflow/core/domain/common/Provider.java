package org.ormi.stackorflow.core.domain.common;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Provider {
  private final UUID memberId;
  private ProviderRole role;

  public Provider(UUID memberId) {
    this.memberId = memberId;
  }
}
