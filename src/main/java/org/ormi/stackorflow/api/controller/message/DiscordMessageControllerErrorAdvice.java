package org.ormi.stackorflow.api.controller.message;

import org.ormi.stackorflow.core.domain.message.MessageException;
import org.ormi.stackorflow.infra.common.Responses;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DiscordMessageControllerErrorAdvice {

  @ExceptionHandler(MessageException.class)
  public Responses<Void> messageExceptionHandler(MessageException e) {
    return Responses.error(e.status(), e.getMessage());
  }
}
