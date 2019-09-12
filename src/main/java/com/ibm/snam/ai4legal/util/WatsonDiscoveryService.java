package com.ibm.snam.ai4legal.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.ibm.snam.ai4legal.common.Config;

import net.sf.json.JSONObject;

public class WatsonDiscoveryService {
	
	private static WatsonDiscoveryService service;
	
	Logger logger = LoggerFactory.getLogger(WatsonDiscoveryService.class);
	
	public static WatsonDiscoveryService getService(){
		if(service == null) {
			service = new WatsonDiscoveryService();
		}
		return service;
	}
	
	public JSONObject callWatsonDiscoveryService(MultipartFile document, int idLanguage){
		logger.info("callWatsonDiscoveryService -- INIT --");
		/*
		 * Chiama il microservizio WDS per l'upload del file su Watson Discovery
		 */
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		final String filename = document.getOriginalFilename();

		ByteArrayResource contentsAsResource = null;

		try {
			contentsAsResource = new ByteArrayResource(document.getBytes()) {
				@Override
				public String getFilename() {
					return filename;
				}
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		body.add("document", contentsAsResource);
		body.add("language", idLanguage);
		String url = "";
		try {
			url = Config.getWdsUploadDoc();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		RestTemplate httpRestTemplate = new RestTemplate();
		
		String response = httpRestTemplate.postForObject(url, body, String.class);
		Gson gson = new Gson();
		JSONObject responseAsJsonObject = gson.fromJson(response, JSONObject.class);
		logger.info("responseAsJsonobject is: " + responseAsJsonObject);
		logger.info("callWatsonDiscoveryService -- END --");
		return responseAsJsonObject;
	}

	
	
}
