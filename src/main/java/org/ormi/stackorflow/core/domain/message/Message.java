package org.ormi.stackorflow.core.domain.message;

public record Message(
    MessageContent messageContent,
    DiscordDomainUser user,
    DiscordDomainChannel channel
) {
}
