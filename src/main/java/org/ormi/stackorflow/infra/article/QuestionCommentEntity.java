package org.ormi.stackorflow.infra.article;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.ormi.stackorflow.infra.user.UserEntity;

@Entity
@Getter
@Table(name = "question_comments")
class QuestionCommentEntity {
  @Id @GeneratedValue
  private long id;
  @NotNull
  private String content;
  @NotNull
  private boolean isAdopted;
  @JoinColumn(name = "user_id")
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private UserEntity user;
}
