package org.ormi.stackorflow.infra.common;

import lombok.Getter;

@Getter
public class ErrorApiResponse<T> {

  private final String message;
  private final String errorCode;

  public ErrorApiResponse(String message) {
    this(message, null);
  }

  public ErrorApiResponse(String message, String errorCode) {
    this.message = message;
    this.errorCode = errorCode;
  }
}
