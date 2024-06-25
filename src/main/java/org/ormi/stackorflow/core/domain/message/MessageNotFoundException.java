package org.ormi.stackorflow.core.domain.message;

import org.springframework.http.HttpStatus;

public final class MessageNotFoundException extends MessageException {

  public MessageNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
