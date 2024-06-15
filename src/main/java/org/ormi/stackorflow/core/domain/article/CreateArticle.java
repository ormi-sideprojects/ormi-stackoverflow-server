package org.ormi.stackorflow.core.domain.article;

import org.ormi.stackorflow.core.domain.common.Provider;

public record CreateArticle(Provider provider, String content, String discordChannelName, String password,
                            ArticleType type) {

}
