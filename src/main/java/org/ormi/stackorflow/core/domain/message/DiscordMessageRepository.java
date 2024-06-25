package org.ormi.stackorflow.core.domain.message;

import java.util.List;

public interface DiscordMessageRepository {

  List<Message> findByMessageId(long messageId);
}
