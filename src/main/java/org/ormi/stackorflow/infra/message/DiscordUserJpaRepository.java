package org.ormi.stackorflow.infra.message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordUserJpaRepository extends JpaRepository<DiscordUserEntity, Long> {

}
