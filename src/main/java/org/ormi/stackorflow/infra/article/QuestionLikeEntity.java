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
import org.ormi.stackorflow.infra.common.BaseEntity;
import org.ormi.stackorflow.infra.user.UserEntity;

@Entity
@Getter
@Table(name = "question_likes")
class QuestionLikeEntity extends BaseEntity {
  @Id @GeneratedValue
  private long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", updatable = false)
  @NotNull
  private UserEntity user;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id", updatable = false)
  @NotNull
  private QuestionEntity question;
}
