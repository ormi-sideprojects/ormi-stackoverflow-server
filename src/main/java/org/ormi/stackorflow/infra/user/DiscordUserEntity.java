package org.ormi.stackorflow.infra.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.ormi.stackorflow.domain.common.DiscordProvider;
import org.ormi.stackorflow.infra.common.BaseEntity;

@Entity
@Getter
@Table(name = "discord_providers")
public final class DiscordUserEntity extends BaseEntity {

  @Id
  private String discordUserId;
  @Column(nullable = false)
  private String nickname;

  public DiscordProvider toProvider() {
    return new DiscordProvider(discordUserId, nickname);
  }
}
