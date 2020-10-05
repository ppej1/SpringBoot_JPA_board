package com.mySlipp.web;

import javax.servlet.http.HttpSession;

import com.mySlipp.domain.userInfo;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY ="sessionUser";

	public static boolean isLoginUser(HttpSession session){
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		if(sessionedUser == null){
			return false;
		}
		return true;
	}
	
	public static userInfo getUserFromSession(HttpSession session){
		if(!isLoginUser(session)){
			return null;
		}
		return (userInfo)session.getAttribute(USER_SESSION_KEY);
			
	}
}
