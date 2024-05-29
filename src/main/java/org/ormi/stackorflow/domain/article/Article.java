package org.ormi.stackorflow.domain.article;

import lombok.Getter;
import org.ormi.stackorflow.domain.common.Provider;
import org.ormi.stackorflow.domain.common.Timestamp;

/***
 * 사용자들의 질문, 강사님의 실시간 채팅에 해당하는 요소입니다.
 * 모두 한가지 리스트로 함께 출력해야하는 요구사항이 있으며
 * 각 종류마다 리스트의 요소와, 세부사항의 요소가 다를 수 있기에
 * 인터페이스를 활용해 해당 요소들을 분리했습니다.
 * {@link LecturerDiscordContent}, {@link QuestionContent}
 * {@link }
 */
@Getter
public final class Article {

  private final long id;
  private final Provider provider;
  private final ArticleContent content;
  private ArticleInfo info;
  private final Timestamp timestamp;

  public Article(long id, Provider provider, ArticleContent content,
      Timestamp timestamp) {
    this.id = id;
    this.provider = provider;
    this.content = content;
    this.timestamp = timestamp;
  }
}
