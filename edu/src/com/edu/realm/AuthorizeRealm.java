package com.edu.realm;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.edu.domain.User;
import com.edu.service.UserServiceI;

@Component
public class AuthorizeRealm extends AuthorizingRealm{
	@Resource
	private UserServiceI ts;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		String userName = pc.getPrimaryPrincipal().toString();
		if(!StringUtils.isEmpty(userName)){
			Set<String> perSet = ts.findPermissions(userName);
			Set<String> roleSet = ts.findRoles(userName);
			SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
			sai.setStringPermissions(perSet);
			sai.setRoles(roleSet);
			return sai;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken at) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) at;
		String username = token.getUsername();
		if(!StringUtils.isEmpty(username)){
			User user = ts.getUserByName(username);
			if(user!=null){
				SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
				return sai;
			}else{
				return null;
			}
		}
		
		return null;
	}

}
