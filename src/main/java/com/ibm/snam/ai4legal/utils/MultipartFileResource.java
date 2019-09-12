package com.ibm.snam.ai4legal.utils;

import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;

public class MultipartFileResource extends InputStreamResource {

	private String filename;

	public MultipartFileResource(InputStream inputStream, String filename) {
		super(inputStream);
		this.filename = filename;
	}

	@Override
	public String getFilename() {
		return this.filename;
	}
}