package ru.lenoblgis.introduse.sergey.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.lenoblgis.introduse.sergey.domen.user.User;
import ru.lenoblgis.introduse.sergey.domen.user.UserRole;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		User user = userService.getUserByLogin(username);
		
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		if(user != null){
			GrantedAuthority role = new SimpleGrantedAuthority(user.getRoleStr());
			roles.add(role);
			UserDetails userDetails =
	                new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
			return userDetails;
		}else{
			return null;
		}
	}

}
