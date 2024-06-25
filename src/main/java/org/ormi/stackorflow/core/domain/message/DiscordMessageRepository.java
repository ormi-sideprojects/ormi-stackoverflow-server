package org.ormi.stackorflow.core.domain.message;

import java.util.List;

public interface DiscordMessageRepository {

  Message findByMessageId(long messageId);

  List<Message> findByDiscordUserId(long discordUserId);
}
