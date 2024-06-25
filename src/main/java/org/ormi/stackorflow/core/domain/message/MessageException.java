package org.ormi.stackorflow.core.domain.message;

import org.springframework.http.HttpStatus;

public class MessageException extends RuntimeException {

  private final HttpStatus errorStatusCode;

  public MessageException(HttpStatus errorStatusCode, String message) {
    super(message);
    this.errorStatusCode = errorStatusCode;
  }

  public HttpStatus status() {
    return errorStatusCode;
  }
}
