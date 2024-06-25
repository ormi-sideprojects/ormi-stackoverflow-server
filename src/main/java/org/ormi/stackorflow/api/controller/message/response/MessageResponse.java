package org.ormi.stackorflow.api.controller.message.response;

import org.ormi.stackorflow.core.domain.message.Message;

public record MessageResponse(
    String content,
    String channelName
) {

  public static MessageResponse from(Message message) {
    return new MessageResponse(message.messageContent().content(), message.channel().channelName());
  }
}
