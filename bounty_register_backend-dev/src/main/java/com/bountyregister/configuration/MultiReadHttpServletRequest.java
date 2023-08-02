package com.bountyregister.configuration;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

	private byte[] cacheBody;

	public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
		super(request);
		if ((request.getContentType() == null)
				|| (request.getContentType().toLowerCase().indexOf("multipart/form-data") <= -1)) {
			InputStream inputStream = request.getInputStream();
			this.cacheBody = StreamUtils.copyToByteArray(inputStream);

		}
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new CachedBodyServletInputStream(cacheBody);

	}

	@Override
	public BufferedReader getReader() throws IOException {
		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(cacheBody);
		return new BufferedReader(new InputStreamReader(arrayInputStream));

	}

}
