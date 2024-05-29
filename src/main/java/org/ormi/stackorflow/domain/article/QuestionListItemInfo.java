package org.ormi.stackorflow.domain.article;

public record QuestionListItemInfo(int likeNumber, int commentNumber, boolean isLiked, boolean isResolved) implements
    ArticleInfo {

}
