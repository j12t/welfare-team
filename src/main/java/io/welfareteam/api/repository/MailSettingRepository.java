package io.welfareteam.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.welfareteam.api.entity.MailSetting;

@Repository
public interface MailSettingRepository extends JpaRepository<MailSetting, Long> {

}
