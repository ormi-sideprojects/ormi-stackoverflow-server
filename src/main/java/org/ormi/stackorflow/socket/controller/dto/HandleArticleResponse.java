package org.ormi.stackorflow.socket.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HandleArticleResponse {

  private int articleId;
  private String content;
  private String nickname;
  private String discordChannelName;
  private boolean isMeToo;
  private int meTooNumber;
  private int commentNumber;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private ZonedDateTime createdAt;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private ZonedDateTime updatedAt;
}
