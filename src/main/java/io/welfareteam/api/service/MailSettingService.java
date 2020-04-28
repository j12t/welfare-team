package io.welfareteam.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.welfareteam.api.repository.MailSettingRepository;

@Service
@Transactional
public class MailSettingService {

	@Autowired
	private MailSettingRepository mailSettingRepository;
	
}
