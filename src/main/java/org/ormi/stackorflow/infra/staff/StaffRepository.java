package org.ormi.stackorflow.infra.staff;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {

	Optional<StaffEntity> findByCode(String code);

}
