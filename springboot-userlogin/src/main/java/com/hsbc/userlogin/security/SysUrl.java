package com.hsbc.userlogin.security;

/**
 * 登录登出使用的各项系统url
 * 
 */
public class SysUrl {

	private static final String prefix = "";

	public static final String INDEX_URL = "/";

	public static final String LOGIN_URL = prefix + "/user/auth";

	public static final String LOGOUT_URL = prefix + "/user/logout";

	public static final String LOGIN_SUCCESS_URL = prefix
			+ "/user/face_auth_success";

	public static final String LOGIN_FAILED_URL = prefix
			+ "/user/face_auth_failed";

	public static final String SESSION_KICKED_URL = prefix
			+ "/user/session/kicked";
}
