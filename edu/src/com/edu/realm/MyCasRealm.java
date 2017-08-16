package com.edu.realm;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import com.edu.domain.User;
import com.edu.service.UserServiceI;

public class MyCasRealm extends CasRealm{
	@Resource
	private UserServiceI ts;
	
	/**
	 * 设置角色和权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = principals.getPrimaryPrincipal().toString();
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
	
	/**
	 * 1、CAS认证 ,验证用户身份
	 * 2、将用户基本信息设置到会话中
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

		AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
		String account = (String) authc.getPrincipals().getPrimaryPrincipal();
		User user = ts.getUserByName(account);
//		User user = userService.getUserByAccount(account);
		SecurityUtils.getSubject().getSession().setAttribute("user", user);
		//这里进行session 处理，缓存本地缓存
		
		return authc;
	}
}
