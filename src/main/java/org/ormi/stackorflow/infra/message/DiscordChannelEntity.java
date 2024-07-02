package org.ormi.stackorflow.infra.message;

import static java.util.Objects.requireNonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.ormi.stackorflow.core.domain.message.DiscordDomainChannel;

@Entity
@Table(name = "discord_channel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscordChannelEntity {

  @Id
  @Column(unique = true)
  private long id;

  private String channelName;

  @Builder
  DiscordChannelEntity(long id, String channelName) {
    this.id = id;
    this.channelName = channelName;
  }

  public void updateChannelName(String newChannelName) {
    requireNonNull(newChannelName, "newChannelName");
    this.channelName = newChannelName;
  }

  public DiscordDomainChannel toDomainObject() {
    return new DiscordDomainChannel(id, channelName);
  }

  public static DiscordChannelEntity of(DiscordDomainChannel discordDomainChannel) {
    return DiscordChannelEntity.builder()
        .id(discordDomainChannel.channelId())
        .channelName(discordDomainChannel.channelName())
        .build();
  }
}
