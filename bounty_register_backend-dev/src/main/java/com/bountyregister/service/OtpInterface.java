package com.bountyregister.service;

import java.util.Date;

import com.bountyregister.entities.OtpEntity;

public interface OtpInterface {

	public OtpEntity saveOtp(String email, String otp1, Long userId, Date expiry) throws Exception;

}
