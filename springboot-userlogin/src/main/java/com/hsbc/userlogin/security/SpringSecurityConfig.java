package com.hsbc.userlogin.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 系统安全配置
 * SpringSecurity的配置和SpringMvc的而配置相似。只需要在配置上类上添加注解@EnableWebSecurity，并且让这个配置类继承WebSecurityConfigurerAdapter
 * 我们可以重写configure方法来来配置相关的安全配置。
 * 
 * 1:configure(WebSecurity web)：忽视拦截的请求，通常情况下静态资源不需要拦截。
 * 2:configure(AuthenticationManagerBuilder auth)：用户的认证；认证有三种方法1)内存认证2)JDBC认证3）通用认证
 * 这时候我们需要自定义一个类（CustomUserDetailsService）并且实现userDetailsService接口；然后注册customUserDetailsService
 * 3：configure(HttpSecurity http)：请求拦截
 *  authorizeRequests()：开始请求拦截的配置
 *  antMatchers():使用ant风格的路径匹配   
 *  permitAll()：用户任意访问 
 * "/", "/index.html",""+"/user/session/kicked" 这样的请求都可以访问
 * anyRequest().authenticated() ：其余的任何请求都需要认证
 *  
 *
 *
 * 
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


	 @Autowired
	 private CustomUserDetailsService customUserDetailsService;
	

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 静态资源不需要拦截
		web.ignoring().antMatchers("/static/**","/templates/**/","/**/*.html", "/**/*.js", "/**/*.css", "/**/*.json", "/**/*.ico",
				"/**/*.png", "/**/*.gif", "/**/*.exe");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 允许所有人访问首页以及退出登录页面
		http.authorizeRequests().antMatchers("/", "/index.html",""+"/user/session/kicked").permitAll()
				.anyRequest().authenticated();

		// 自定义登录页
		http.csrf().disable().formLogin().loginProcessingUrl(SysUrl.LOGIN_URL)
				.usernameParameter("username").passwordParameter("password").permitAll()
				.defaultSuccessUrl(SysUrl.LOGIN_SUCCESS_URL).failureUrl(SysUrl.LOGIN_FAILED_URL);

		// 自定义注销
		http.logout().logoutUrl(SysUrl.LOGOUT_URL).logoutSuccessUrl(SysUrl.SESSION_KICKED_URL)
				.invalidateHttpSession(true);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 自定义UserDetailsService
		auth.userDetailsService(customUserDetailsService);
	}

}
