package org.ormi.stackorflow.infra.article;

import static org.ormi.stackorflow.infra.article.QArticleCommentEntity.articleCommentEntity;
import static org.ormi.stackorflow.infra.article.QArticleEntity.articleEntity;
import static org.ormi.stackorflow.infra.article.QArticleMeTooEntity.articleMeTooEntity;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ormi.stackorflow.core.domain.article.Article;
import org.ormi.stackorflow.core.domain.article.QArticle;
import org.ormi.stackorflow.core.domain.article.QArticleMeToo;
import org.ormi.stackorflow.core.domain.common.auth.Provider;
import org.ormi.stackorflow.core.domain.common.QProvider;
import org.ormi.stackorflow.core.domain.common.QTimestamps;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
class ArticleQueryDslRepository {

  private final JPAQueryFactory queryFactory;

  List<Article> getHomeThreadList(Provider provider) {
    return queryFactory.select(new QArticle(articleEntity.id, new QProvider(articleEntity.memberId),
            Expressions.asString("익명"), articleEntity.content, articleEntity.discordChannelName,
            articleEntity.type, new QArticleMeToo(articleMeTooEntity.count().intValue(),
            JPAExpressions.selectOne().from(articleMeTooEntity).exists()),
            articleCommentEntity.count().intValue(),
            new QTimestamps(articleEntity.createdAt, articleEntity.updatedAt))).from(articleEntity)
        .leftJoin(articleCommentEntity).on(articleCommentEntity.article.id.eq(articleEntity.id))
        .leftJoin(articleMeTooEntity).on(articleMeTooEntity.article.id.eq(articleEntity.id))
        .groupBy(articleCommentEntity.article).groupBy(articleMeTooEntity.article).fetch();
  }
}
