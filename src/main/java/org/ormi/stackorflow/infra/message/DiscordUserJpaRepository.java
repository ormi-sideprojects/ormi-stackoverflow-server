package org.ormi.stackorflow.infra.message;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordUserJpaRepository extends JpaRepository<DiscordUserEntity, Long> {

  Optional<DiscordUserEntity> findByDiscordOauthUserId(Long discordOauthUserId);
}
