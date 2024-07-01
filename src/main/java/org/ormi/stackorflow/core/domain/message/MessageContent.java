package org.ormi.stackorflow.core.domain.message;

public record MessageContent(
    long messageId,
    String content
) {

  public static MessageContent of(long messageId, String content) {
    return new MessageContent(messageId, content);
  }
}
