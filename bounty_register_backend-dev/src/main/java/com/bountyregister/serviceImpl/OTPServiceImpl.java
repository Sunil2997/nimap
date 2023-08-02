package com.bountyregister.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bountyregister.entities.OtpEntity;
import com.bountyregister.exceptionHandling.ResourceNotFoundException;
import com.bountyregister.repositories.OTPRepository;
import com.bountyregister.service.OtpInterface;

@Service
public class OTPServiceImpl implements OtpInterface {

	@Autowired
	private OTPRepository otpRepository;

	@Override
	public OtpEntity saveOtp(String email, String otp, Long userId, Date expiry) throws Exception {

		try {
			otpRepository.deleteAllByEmail(email);

			OtpEntity entities = new OtpEntity();
			entities.setUserId(userId);
			entities.setEmail(email.toLowerCase());
			entities.setOtp(otp);
			entities.setExpireAt(expiry);
			otpRepository.save(entities);

		} catch (ResourceNotFoundException e) {
			throw new Exception(e);
		}
		return null;

	}

}

