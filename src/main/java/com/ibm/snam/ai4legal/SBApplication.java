package com.ibm.snam.ai4legal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication(scanBasePackages = {"com.ibm"})
@EnableOAuth2Sso
public class SBApplication extends WebSecurityConfigurerAdapter{

    public static void main(String[] args) {
        SpringApplication.run(SBApplication.class, args);
    }
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/searchContract")
		.and().ignoring().antMatchers("/uploadContractOnDB")
		.and().ignoring().antMatchers("/setUserLanguage")
		.and().ignoring().antMatchers("/setTextFont")
		.and().ignoring().antMatchers("/textSize");
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
    	http.authorizeRequests().antMatchers("/login","/logon")
		.authenticated().and().logout().logoutSuccessUrl("/login").permitAll()
		.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
    
}
