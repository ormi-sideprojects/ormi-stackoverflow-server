package org.ormi.stackorflow.infra.message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordChannelJpaRepository extends JpaRepository<DiscordChannelEntity, Long> {
}
