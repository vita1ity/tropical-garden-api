package org.crama.tropicalgarden.garden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeTypeRepository extends JpaRepository<TreeType, Long> {

	
	
}
