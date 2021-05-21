package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
	
	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("START");
		LOGGER.debug(authHeader);
		
		Map<String, String> map = new HashMap<String, String>();
		String user = getUser(authHeader);
		String jwtToken = generateJwt(user);
		
		map.put("token", jwtToken);
		
		LOGGER.debug("map : {} ", map);
		LOGGER.info("END");
		return map;
	}
	

	private String getUser(String authHeader) {
		
		LOGGER.info("START");
		String encodedCredentials = authHeader.substring(6);
		
		String decodeCredentials = new String(Base64.getDecoder().decode(encodedCredentials));
		LOGGER.debug("decodeCredentials : {}", decodeCredentials);
		String user = decodeCredentials.substring(0, decodeCredentials.indexOf(":"));
		LOGGER.debug("user : {}", user);
		
		LOGGER.info("END");
		return user;
	}

	private String generateJwt(String user) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		
		builder.setIssuedAt(new Date());
		
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretKey");
		String token = builder.compact();
		LOGGER.debug("jwtToken : {}", token);
		LOGGER.info("END");
		return token;
	}
	
}
