package com.keebraa.jbirth.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.keebraa.jbirth.dao.helpers.UserHelper;
import com.keebraa.jbirth.dao.objects.DBUser;
import com.keebraa.jbirth.security.UserAuthentication;

public class AuthService {
	public static void authorizeUser(DBUser user) {
		List<GrantedAuthority> authorities = UserHelper.getAuthorities(user);
		Authentication a = new UserAuthentication(user, authorities);
		SecurityContextHolder.getContext().setAuthentication(a);
	}
}
