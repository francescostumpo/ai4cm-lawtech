package com.ibm.snam.ai4legal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.snam.ai4legal.model.Language;
import com.ibm.snam.ai4legal.model.UserPreference;
import com.ibm.snam.ai4legal.repositories.LanguageRepository;
import com.ibm.snam.ai4legal.repositories.UserPreferenceRepository;
import com.ibm.snam.ai4legal.util.Constants;


@RestController
@RequestMapping("/settings")
public class UserPreferenceController {
	
	@Autowired
	UserPreferenceRepository userPreferenceRepository;
	
	@Autowired
	LanguageRepository languageRepository;
	
	@PostMapping("/setUserLanguage")
	public void setLanguage(HttpServletRequest request,String language){
		if(language.equals("it") || language.equals("en")){
			HttpSession session = request.getSession();
			String userName = (String)session.getAttribute(Constants.EMAIL);
			UserPreference userPreference = userPreferenceRepository.findById(userName).get();
			Language lan = languageRepository.findByName(language);
			userPreference.setIdLanguage(lan.getId());
			userPreferenceRepository.save(userPreference);		
		}
	}
	
	@PostMapping("/setTextFont")
	public void setTextFont(HttpServletRequest request, String font){
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute(Constants.EMAIL);
		UserPreference userPreference = userPreferenceRepository.findById(userName).get();
		userPreference.setTextFont(font);
	}
	
	@PostMapping("/textSize")
	public void setTextSize(HttpServletRequest request, String size){
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute(Constants.EMAIL);
		UserPreference userPreference = userPreferenceRepository.findById(userName).get();
		userPreference.setTextSize(size);
	}
	
}
