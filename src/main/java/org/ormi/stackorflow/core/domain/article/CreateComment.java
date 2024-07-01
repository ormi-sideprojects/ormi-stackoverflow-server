package org.ormi.stackorflow.core.domain.article;

import org.ormi.stackorflow.core.domain.common.auth.Provider;

public record CreateComment(Provider provider, long articleId, String content) {

}
