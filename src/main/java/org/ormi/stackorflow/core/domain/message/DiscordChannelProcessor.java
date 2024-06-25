package org.ormi.stackorflow.core.domain.message;

import java.util.Optional;
import org.ormi.stackorflow.infra.message.DiscordChannelEntity;
import org.ormi.stackorflow.infra.message.DiscordChannelJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class DiscordChannelProcessor {

  private final DiscordChannelJpaRepository channelRepository;

  public DiscordChannelProcessor(DiscordChannelJpaRepository channelRepository) {
    this.channelRepository = channelRepository;
  }

  public void saveIfNotExists(DiscordDomainChannel channel) {
    Optional<DiscordChannelEntity> channelEntity =
        channelRepository.findById(channel.channelId());
    if (channelEntity.isPresent()) {
      return;
    }
    channelRepository.save(DiscordChannelEntity.of(channel));
  }
}
