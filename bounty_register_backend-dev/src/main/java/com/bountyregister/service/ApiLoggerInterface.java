package com.bountyregister.service;

import org.springframework.stereotype.Service;

import com.bountyregister.entities.ApiLoggerEntity;


@Service
public interface ApiLoggerInterface {

	void createApiLog(ApiLoggerEntity api);

}
