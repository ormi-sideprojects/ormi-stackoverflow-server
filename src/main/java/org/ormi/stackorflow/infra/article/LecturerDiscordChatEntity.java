package org.ormi.stackorflow.infra.article;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.ormi.stackorflow.infra.user.DiscordUserEntity;

@Entity
@Getter
public class LecturerDiscordChatEntity extends ArticleContentEntity{
  private String channelName;
  @JoinColumn(name = "discord_user_id", updatable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  @NotNull
  private DiscordUserEntity discordUser;
}
