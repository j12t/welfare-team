package io.welfareteam.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.welfareteam.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Returns a unique user identified by login without password.
	 *
	 * @return a reference to the entity with the given identifier.
	 */
	Optional<User> findByLogin(String login);

}
