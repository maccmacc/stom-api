package com.stom.app.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.stom.app.security.JwtTokenProvider;

@Service
public class TokenService {
	
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	
	public Long extractUserIdFromAuthorizationHeader(String authorizationHeader) {
		String token = authorizationHeader.split(" ")[1];
		return tokenProvider.getUserIdFromJWT(token);
	}
	
	public Long extractUserIdFromToken(String token) {
		return tokenProvider.getUserIdFromJWT(token);
	}
	
	public String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		try {
		    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
		    	return bearerToken.substring(7, bearerToken.length());
		    }
		} catch(IllegalArgumentException exc) {
			return null;
		}
	    
	    return null;  
	}
	
}
