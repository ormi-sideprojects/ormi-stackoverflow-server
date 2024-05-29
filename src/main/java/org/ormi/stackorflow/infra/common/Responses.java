package org.ormi.stackorflow.infra.common;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class Responses<T> extends ResponseEntity<Object> {

  Responses(HttpStatusCode status, AbstractResponse<T> response) {
    super(response, status);
  }

  Responses(HttpStatusCode statusCode, ErrorApiResponse<T> response) {
    super(response, statusCode);
  }

  public static <U> Responses<U> ok(String message) {
    return ok(message, null);
  }

  public static <U> Responses<U> ok(String message, U result) {
    return of(HttpStatus.OK, message, result);
  }

  public static <U> Responses<List<U>> paginated(
      String message,
      List<U> result,
      PaginationApiResponse.PagedMetadata pagedMetadata
  ) {
    return new Responses<>(HttpStatus.OK, new PaginationApiResponse<>(message, result, pagedMetadata));
  }
  public static <U> Responses<U> created(String message) {
    return of(HttpStatus.CREATED, message);
  }

  public static <U> Responses<U> created(String message, U result) {
    return of(HttpStatus.CREATED, message, result);
  }

  public static <U> Responses<U> badRequest(String message) {
    return badRequest(message, null);
  }

  public static <U> Responses<U> badRequest(String message, String errorCode) {
    return error(HttpStatus.BAD_REQUEST, message, errorCode);
  }

  public static <U> Responses<U> error(HttpStatusCode status, String message) {
    return error(status, message, null);
  }

  public static <U> Responses<U> error(HttpStatusCode status, String message, String errorCode) {
    return new Responses<>(status, new ErrorApiResponse<>(message, errorCode));
  }

  public static <U> Responses<U> of(HttpStatusCode statusCode, String message) {
    return of(statusCode, message, null);
  }

  public static <U> Responses<U> of(HttpStatusCode statusCode, String message, U result) {
    if (statusCode.isError()) {
      return error(statusCode, message);
    }
    return new Responses<>(statusCode, new DefaultApiResponse<>(message, result));
  }
}
