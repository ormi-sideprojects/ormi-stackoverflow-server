package org.ormi.stackorflow.infra.message;

import static org.ormi.stackorflow.infra.message.QDiscordUserEntity.*;
import static org.ormi.stackorflow.infra.message.QDiscordChannelEntity.*;
import static org.ormi.stackorflow.infra.message.QDiscordMessageEntity.*;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.ormi.stackorflow.core.domain.message.DiscordDomainChannel;
import org.ormi.stackorflow.core.domain.message.DiscordDomainUser;
import org.ormi.stackorflow.core.domain.message.DiscordMessageRepository;
import org.ormi.stackorflow.core.domain.message.Message;
import org.ormi.stackorflow.core.domain.message.MessageContent;
import org.springframework.stereotype.Repository;

@Repository
public class DiscordMessageQueryDslRepository implements DiscordMessageRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public DiscordMessageQueryDslRepository(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<Message> findByDiscordUserId(long discordUserId) {
    return jpaQueryFactory.select(getMessageProjections())
        .from(discordMessageEntity)
        .where(discordMessageEntity.discordUserId.eq(discordUserId))
        .leftJoin(discordUserEntity).on(discordUserEntity.discordOauthUserId.eq(discordMessageEntity.discordUserId))
        .leftJoin(discordChannelEntity).on(discordChannelEntity.id.eq(discordMessageEntity.discordChannelId))
        .fetch();
  }

  @Override
  public Message findByMessageId(long messageId) {
    return jpaQueryFactory.select(getMessageProjections())
    .from(discordMessageEntity)
    .where(discordMessageEntity.messageId.eq(messageId))
    .leftJoin(discordChannelEntity)
      .on(discordMessageEntity.discordChannelId.eq(discordChannelEntity.id))
    .leftJoin(discordUserEntity)
      .on(discordMessageEntity.discordUserId.eq(discordUserEntity.id))
    .fetchOne();
  }

  private ConstructorExpression<Message> getMessageProjections() {
    return Projections.constructor(
        Message.class,
        Projections.constructor(
            MessageContent.class,
            discordMessageEntity.id,
            discordMessageEntity.content
        ),
        Projections.constructor(
            DiscordDomainUser.class,
            discordUserEntity.id,
            discordUserEntity.discordOauthUserId,
            QDiscordUserEntity.discordUserEntity.nickname,
            QDiscordUserEntity.discordUserEntity.profileImageUrl
        ),
        Projections.constructor(
            DiscordDomainChannel.class,
            discordChannelEntity.id,
            discordChannelEntity.channelName
        )
    );
  }
}
