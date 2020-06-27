package com.abhi.springsecurityjpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhi.springsecurityjpa.UserRepository;
import com.abhi.springsecurityjpa.model.MyUserDetails;
import com.abhi.springsecurityjpa.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException{
		//return new MyUserDetails(s);
		Optional<User> user= userRepository.findByUserName(userName);
		user.orElseThrow(()-> new UsernameNotFoundException("Not Found :"+userName));
		return user.map(MyUserDetails:: new).get();
	}

}
