package org.ormi.stackorflow.socket.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HandleCommentResponse {

  private String nickname;
  private String content;
  private boolean isMeLikes;
  private int likesNumber;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private ZonedDateTime createdAt;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
  private ZonedDateTime updatedAt;

}
