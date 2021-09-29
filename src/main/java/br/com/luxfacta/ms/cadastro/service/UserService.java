package br.com.luxfacta.ms.cadastro.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.luxfacta.ms.cadastro.security.UserSS;


public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}