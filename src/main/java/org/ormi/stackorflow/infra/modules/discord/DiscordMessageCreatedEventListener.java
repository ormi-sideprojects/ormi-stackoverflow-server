package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.Channel;
import discord4j.discordjson.json.ChannelData;
import discord4j.rest.entity.RestChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
final class DiscordMessageCreatedEventListener implements DiscordListener<MessageCreateEvent> {

  private final ApplicationEventPublisher eventPublisher;
  private final GatewayDiscordClient discordClient;

  public DiscordMessageCreatedEventListener(
      ApplicationEventPublisher eventPublisher,
      GatewayDiscordClient discordClient
  ) {
    this.eventPublisher = eventPublisher;
    this.discordClient = discordClient;
  }

  @Override
  public Mono<Void> execute(MessageCreateEvent event) {
    if (event.getMember().isEmpty()) {
      return Mono.empty();
    }
    Mono<ChannelData> channelDataMono = discordClient.getChannelById(event.getMessage().getChannelId())
      .map(Channel::getRestChannel)
      .flatMap(RestChannel::getData);
    Mono.zip(
        Mono.just(DiscordCreatedMessage.from(event)),
        channelDataMono,
        Mono.just(event.getMember())
    )
    .mapNotNull(tuple -> {
      DiscordChannel discordChannel = DiscordChannel.from(tuple.getT2());
      DiscordCreatedMessage discordCreatedMessage = tuple.getT1();
      Member member = tuple.getT3().orElse(null);
      if (member == null) {
        return null;
      }
      return DiscordMessageCreatedEvent.from(discordChannel, discordCreatedMessage, DiscordMember.from(member));
    })
    .subscribe(eventPublisher::publishEvent);

    return Mono.empty();
  }

  @Override
  public Class<MessageCreateEvent> type() {
    return MessageCreateEvent.class;
  }
}
