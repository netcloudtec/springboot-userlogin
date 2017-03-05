package com.hsbc.userlogin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hsbc.userlogin.domain.User;
import com.hsbc.userlogin.service.UserService;




/**
 * 自定义用户获取策略
 * 实现UserDetailsService接口重写loadUserByUsername方法
 * 根据用户名进行查询用户信息
 * 
 *
 * 
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUserName(username);

		if (user == null) {
			throw new RuntimeException("用户名 " + username + " 不存在");
		}

		return new SecurityUser(user);
	}

}
