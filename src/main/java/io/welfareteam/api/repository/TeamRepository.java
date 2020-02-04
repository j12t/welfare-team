package io.welfareteam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.welfareteam.api.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
