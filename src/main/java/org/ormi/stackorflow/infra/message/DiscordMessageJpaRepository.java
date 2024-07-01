package org.ormi.stackorflow.infra.message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordMessageJpaRepository extends JpaRepository<DiscordMessageEntity, Long> {

}
