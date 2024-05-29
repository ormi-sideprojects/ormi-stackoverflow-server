package org.ormi.stackorflow.infra.article;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Entity
@Table(name = "article_contents")
abstract public class ArticleContentEntity {

  @Id
  @GeneratedValue
  private long id;
  private String content;
}
