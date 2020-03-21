package io.welfareteam.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.welfareteam.api.entity.Mood;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {

	@Query("select m from Mood m where m.user.id = :id")
	public Page<Mood> findByUserId(Pageable pageable,  @Param("id") Long id);
}
