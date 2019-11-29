package io.welfareteam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.welfareteam.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
