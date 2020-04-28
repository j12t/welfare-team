package io.welfareteam.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.welfareteam.api.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("select t from Team t where t.board = :id")
	public Page<Team> findByBoardId(Pageable pageable, @Param("id") Long boardId);

	@Query("select t from Team t where t.mailSetting.sendingTime >= :timeBefore and t.mailSetting.sendingTime < :timeAfter")
	public List<Team> findBySettingMailSendingTimeBetween(@Param("timeBefore") Date timeBefore, @Param("timeAfter") Date timeAfter);

}
