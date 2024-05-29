package org.ormi.stackorflow.infra.common;

import java.util.List;
import lombok.Getter;

@Getter
public class PaginationApiResponse<T> extends AbstractResponse<List<T>> {

  private final int currentPage;
  private final int nextPage;
  private final int totalItemSize;
  private final boolean hasNext;

  public PaginationApiResponse(
      String message,
      List<T> results,
      PagedMetadata pagedMetadata
  ) {
    super(message, results);

    this.currentPage = pagedMetadata.currentPage;
    this.nextPage = pagedMetadata.nextPage;
    this.totalItemSize = pagedMetadata.totalItemSize;
    this.hasNext = pagedMetadata.hasNext;
  }

  public record PagedMetadata(
      int currentPage,
      int nextPage,
      int totalItemSize,
      boolean hasNext
  ) {

    public static PagedMetadata of(
        int currentPage,
        int nextPage,
        int totalItemSize,
        boolean hasNext
    ) {
      return new PagedMetadata(currentPage, nextPage, totalItemSize, hasNext);
    }
  }
}
