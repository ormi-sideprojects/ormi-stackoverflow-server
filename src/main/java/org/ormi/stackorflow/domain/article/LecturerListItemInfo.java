package org.ormi.stackorflow.domain.article;

public record LecturerListItemInfo(int likeNumber, int commentNumber, boolean isLiked) implements
    ArticleInfo {

}
