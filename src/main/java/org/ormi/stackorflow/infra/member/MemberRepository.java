package org.ormi.stackorflow.infra.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	Optional<MemberEntity> findBySessionId(String sessionId);

}
