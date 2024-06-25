package org.ormi.stackorflow.infra.message;

import static java.util.Objects.requireNonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.ormi.stackorflow.core.domain.message.DiscordDomainUser;

@Entity
@Table(name = "discord_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscordUserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "oauth_user_id", updatable = false, unique = true)
  private long discordOauthUserId;

  @NotNull
  private String nickname;

  private String profileImageUrl;

  @Builder
  DiscordUserEntity(long discordOauthUserId, String nickname, String profileImageUrl) {
    this.discordOauthUserId = discordOauthUserId;
    this.nickname = nickname;
    this.profileImageUrl = profileImageUrl;
  }

  public void updateNickname(String newNickname) {
    requireNonNull(newNickname, "newNickname");
    this.nickname = newNickname;
  }

  public void updateProfileImage(String newProfileImageUrl) {
    requireNonNull(newProfileImageUrl, "newProfileImageUrl");
    this.profileImageUrl = newProfileImageUrl;
  }

  public DiscordDomainUser toDomainObject() {
    return new DiscordDomainUser(id, discordOauthUserId, nickname, profileImageUrl);
  }

  public static DiscordUserEntity of(DiscordDomainUser discordDomainUser) {
    return DiscordUserEntity.builder()
        .discordOauthUserId(discordDomainUser.discordId())
        .nickname(discordDomainUser.nickname())
        .profileImageUrl(discordDomainUser.profileImageUrl())
        .build();
  }
}
