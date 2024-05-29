package org.ormi.stackorflow.infra.common;

public final class DefaultApiResponse<T> extends AbstractResponse<T> {

  public DefaultApiResponse(String message, T result) {
    super(message, result);
  }
}
