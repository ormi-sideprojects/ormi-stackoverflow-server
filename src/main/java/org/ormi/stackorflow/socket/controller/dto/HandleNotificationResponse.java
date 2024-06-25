package org.ormi.stackorflow.socket.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HandleNotificationResponse {

  long id;
  Optional<String> receiverId;
  String senderId;
  long target; // original article id
  String domain; // comment or article
  String message;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  ZonedDateTime createdAt;
}
