package org.ormi.stackorflow.core.domain.message;

import org.ormi.stackorflow.infra.modules.discord.DiscordMember;

public record DiscordDomainUser(
    long id,
    long discordId,
    String nickname,
    String profileImageUrl
) {

  public static DiscordDomainUser from(DiscordMember discordMember) {
    return new DiscordDomainUser(-1L, discordMember.id(), discordMember.nickname(), discordMember.profileImageUrl());
  }
}
