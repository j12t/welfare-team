package io.welfareteam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.welfareteam.api.entity.Mood;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {

}
