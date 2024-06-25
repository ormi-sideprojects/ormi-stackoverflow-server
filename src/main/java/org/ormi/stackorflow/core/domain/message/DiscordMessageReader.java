package org.ormi.stackorflow.core.domain.message;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DiscordMessageReader {

  private final DiscordMessageRepository discordMessageRepository;

  public DiscordMessageReader(DiscordMessageRepository discordMessageRepository) {
    this.discordMessageRepository = discordMessageRepository;
  }

  public List<Message> getByDiscordUser(long discordUserId) {
    return discordMessageRepository.findByDiscordUserId(discordUserId);
  }

  public Message getById(long messageId) {
    Message message = discordMessageRepository.findByMessageId(messageId);
    if (message == null) {
      throw new MessageNotFoundException("메시지가 존재하지 않습니다.");
    }
    return message;
  }
}
