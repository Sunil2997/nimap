package com.bountyregister.service;

import org.springframework.stereotype.Service;

import com.bountyregister.dto.MailTemplateDto;

@Service
public interface MailTemplateInterface {
	
	void addMailTemplate(MailTemplateDto mailTemplateDto);
}
