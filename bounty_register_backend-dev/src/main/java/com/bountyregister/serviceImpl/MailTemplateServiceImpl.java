package com.bountyregister.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bountyregister.dto.MailTemplateDto;
import com.bountyregister.entities.MailTemplate;
import com.bountyregister.exceptionHandling.ResourceNotFoundException;
import com.bountyregister.repositories.MailRepository;
import com.bountyregister.service.MailTemplateInterface;

@Service
public class MailTemplateServiceImpl implements MailTemplateInterface {

	@Autowired
	MailRepository mailRepository;

	@Override
	public void addMailTemplate(MailTemplateDto mailTemplateDto) {

		String templatename = mailTemplateDto.getTemplatename().trim();
		if (mailRepository.existsByTemplatename(templatename)) {
			throw new ResourceNotFoundException("Template already present");
		}

		MailTemplate mailTemplate = new MailTemplate();
		mailTemplate.setMailtemp(mailTemplateDto.getMailtemp());
		mailTemplate.setTemplatename(templatename);

		mailRepository.save(mailTemplate);
	}

}