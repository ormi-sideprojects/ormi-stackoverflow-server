package org.ormi.stackorflow.infra.article;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.ormi.stackorflow.infra.common.BaseEntity;
import org.ormi.stackorflow.infra.user.DiscordUserEntity;

@Entity
@Table(name = "question_mentions")
class QuestionMentionEntity extends BaseEntity {
  @Id @GeneratedValue
  private long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "discord_user_id", updatable = false)
  @NotNull
  private DiscordUserEntity discordUser;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id", updatable = false)
  @NotNull
  private QuestionEntity questionEntity;
}
