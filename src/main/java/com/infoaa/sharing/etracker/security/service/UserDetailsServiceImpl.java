package com.infoaa.sharing.etracker.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infoaa.sharing.etracker.model.Role;
import com.infoaa.sharing.etracker.model.User;
import com.infoaa.sharing.etracker.repository.UserRepository;

@Transactional
@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (user == null) {
            throw new UsernameNotFoundException(
              "No user found with username: "+ username);
        }

		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getPassword(), 
				getAuthorities(user.getRoles()));
	}
	
    private static Set<GrantedAuthority> getAuthorities (Set<Role> roles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : roles) {
        	grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }
}
