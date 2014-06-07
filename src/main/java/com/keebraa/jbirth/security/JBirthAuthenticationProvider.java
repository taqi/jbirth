package com.keebraa.jbirth.security;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JBirthAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return authentication;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		if (clazz.equals(UserAuthentication.class)) {
			return true;
		}
		return false;
	}
}
