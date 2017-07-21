package org.crama.tropicalgarden.garden;

import java.util.List;

import org.crama.tropicalgarden.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTreeRepository extends JpaRepository<UserTree, Long> {

	List<UserTree> findByUser(User user);

	UserTree findByUserAndTreeType(User user, TreeType tree);
	
}
