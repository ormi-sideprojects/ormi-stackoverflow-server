package org.ormi.stackorflow.infra.article;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.ormi.stackorflow.core.domain.article.ArticleType;

@Converter(autoApply = true)
public class ArticleTypeConverter implements AttributeConverter<ArticleType, String> {

  @Override
  public String convertToDatabaseColumn(ArticleType articleType) {
    return articleType.name();
  }

  @Override
  public ArticleType convertToEntityAttribute(String s) {
    return ArticleType.valueOf(s);
  }
}
