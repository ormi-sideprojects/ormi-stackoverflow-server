package org.ormi.stackorflow.socket.controller;

import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.socket.controller.dto.HandleArticleResponse;
import org.ormi.stackorflow.socket.controller.dto.HandleCommentResponse;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

/**
 * 웹소켓 Controller(Gateway) 파일입니다.
 *
 * @author 강채현
 */
@Controller
@RequiredArgsConstructor
public class WebSocketGateway {

  private final SimpMessageSendingOperations simpMessageSendingOperations;

  // 게시글 정보 응답 이벤트
  public void handleArticle(HandleArticleResponse dto) {
    simpMessageSendingOperations.convertAndSend("/sub/articles", dto);
  }

  // 게시글 내의 댓글 응답 이벤트
  public void handleComment(int articleId, HandleCommentResponse dto) {
    simpMessageSendingOperations.convertAndSend("/sub/articles/" + articleId + "/comments", dto);
  }
}