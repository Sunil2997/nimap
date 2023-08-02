package com.bountyregister.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bountyregister.entities.ApiLoggerEntity;
import com.bountyregister.repositories.ApiLoggerRepository;
import com.bountyregister.service.ApiLoggerInterface;

@Service("apiLoggerServiceImpl")
public class ApiLoggerServiceImpl implements ApiLoggerInterface {

	@Autowired
	private ApiLoggerRepository apiLoggerRepository;

	public ApiLoggerServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createApiLog(ApiLoggerEntity api) {

		apiLoggerRepository.save(api);
		

	}

}
