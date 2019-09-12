package com.ibm.snam.ai4legal.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.snam.ai4legal.model.Language;
import com.ibm.snam.ai4legal.model.UserPreference;
import com.ibm.snam.ai4legal.repositories.LanguageRepository;
import com.ibm.snam.ai4legal.repositories.UserPreferenceRepository;
import com.ibm.snam.ai4legal.util.Constants;

import net.sf.json.JSONObject;

@RestController
public class ViewController {

	@Autowired
	UserPreferenceRepository userPreferenceRepository;
	@Autowired
	LanguageRepository languageRepository;
	
	Logger logger = LoggerFactory.getLogger(ViewController.class);
	
	@GetMapping("/logon")
	public void logon(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("authenticated", true);
		// Prendo l'email dell'utente dal token;
		Authentication authentication = (Authentication) principal;
		JSONObject object = JSONObject.fromObject(authentication);
		JSONObject userAuthentication = JSONObject.fromObject(object.get("userAuthentication"));
		JSONObject details = JSONObject.fromObject(userAuthentication.get("details"));
		String email = details.getString("email");
		String name = object.getString("name");
		session.setAttribute(Constants.EMAIL, email);
		session.setAttribute(Constants.NAME, name);
		// Prendo le informazioni dell'utente dal DB
		UserPreference userPreference = userPreferenceRepository.findById(email).get();
		session.setAttribute(Constants.TEXT_FONT, userPreference.getTextFont());
		session.setAttribute(Constants.TEXT_SIZE, userPreference.getTextSize());
		Language languageUser = languageRepository.findById(userPreference.getIdLanguage()).get();
		session.setAttribute(Constants.LANGUAGE, languageUser.getName());
		response.sendRedirect("/homepage");
		logger.info("logged in as : " + session.getAttribute(Constants.EMAIL));
		
	}

	@GetMapping("/homepage")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals(true)) {
			modelAndView = new ModelAndView("homepage");
			return modelAndView;
		} else {
			response.sendRedirect("/logon");
			return null;
		}
	}
	
	@GetMapping("/analyze")
	public ModelAndView analyze(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals(true)) {
			modelAndView = new ModelAndView("analyze");
			return modelAndView;
		} else {
			response.sendRedirect("/logon");
			return null;
		}
	} 
	
	@GetMapping("/compare")
	public ModelAndView compare(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null; 
	    HttpSession session = request.getSession();
	    if (session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals(true)) {
		  modelAndView = new ModelAndView("compare");
		  return modelAndView;
	    } else {
		   response.sendRedirect("/logon");
		   return null;
	    }
	
	}
	
	@GetMapping("/settings")
	public ModelAndView settings(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals(true)) {
			modelAndView = new ModelAndView("settings");
			return modelAndView;
		} else {
			response.sendRedirect("/logon");
			return null;
		}
	}
	
	@GetMapping("/searchView")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("authenticated") != null && session.getAttribute("authenticated").equals(true)) {
			modelAndView = new ModelAndView("searchView");
			return modelAndView;
		} else {
			response.sendRedirect("/logon");
			return null;
		}
	}
}
