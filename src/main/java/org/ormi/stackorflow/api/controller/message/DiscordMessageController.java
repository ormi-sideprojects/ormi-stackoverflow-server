package org.ormi.stackorflow.api.controller.message;

import java.util.List;
import org.ormi.stackorflow.api.controller.message.response.MessageResponse;
import org.ormi.stackorflow.core.domain.message.DiscordMessageReader;
import org.ormi.stackorflow.core.domain.message.Message;
import org.ormi.stackorflow.infra.common.Responses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DiscordMessageController {

  private final DiscordMessageReader discordMessageReader;

  public DiscordMessageController(DiscordMessageReader discordMessageReader) {
    this.discordMessageReader = discordMessageReader;
  }

  @GetMapping("/discord/{discordUserId}/messages")
  public Responses<List<MessageResponse>> getUserMessages(@PathVariable("discordUserId") long discordUserId) {
    List<Message> messages = discordMessageReader.getByDiscordUser(discordUserId);
    List<MessageResponse> responses = messages.stream()
        .map(MessageResponse::from)
        .toList();

    return Responses.ok("유저의 디스코드 메시지를 성공적으로 조회하였습니다", responses);
  }

  @GetMapping("/discord/messages")
  public Responses<MessageResponse> getSpecificMessage(@RequestParam("messageId") long messageId) {
    Message message = discordMessageReader.getById(messageId);
    MessageResponse response = MessageResponse.from(message);

    return Responses.ok("특정 id 메시지를 성공적으로 조회하였습니다.", response);
  }
}
