package org.ormi.stackorflow.infra.modules.discord;

import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.PartialMember;
import discord4j.core.object.entity.User;

public record DiscordMember(
  long id,
  String nickname
) {

  public static DiscordMember from(Member member) {
    return new DiscordMember(
        member.getUserData().id().asLong(),
        member.getNickname().orElse("")
    );
  }

  public static DiscordMember from(User user) {
    return new DiscordMember(
        user.getId().asLong(),
        user.getGlobalName().orElse("")
    );
  }

  public static DiscordMember from(PartialMember partialMember) {
    return new DiscordMember(
        partialMember.getId().asLong(),
        partialMember.getMemberData().nick().get().orElse("")
    );
  }
}
