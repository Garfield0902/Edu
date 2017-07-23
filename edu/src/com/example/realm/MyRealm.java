package com.example.realm;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.example.domain.TUser;
import com.example.service.TUserService;
/**
 * 现在就需要创建自定义的MyRealm类，这个还是比较重要的。继承至Shiro的AuthorizingRealm类，用于处理自己的验证逻辑
 * @author zhangwc
 *
 */
public class MyRealm extends AuthorizingRealm{
	
	@Resource
	private TUserService ts;
	/**
	 * 用于权限认证的
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String userName = principalCollection.getPrimaryPrincipal().toString();
		SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
		Set<String> roleName = ts.findRoles(userName);
		Set<String> permissions = ts.findPermissions(userName);
		sai.setRoles(roleName);
		sai.setStringPermissions(permissions);
		return sai;
	}
	/**
	 * 用于登陆验证：在登录的时候需要将数据封装到Shiro的一个token中，执行shiro的login()方法，之后只要我们将MyRealm这个类配置到Spring中，登录的时候Shiro就会自动的调用doGetAuthenticationInfo()方法进行验证。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken taken) throws AuthenticationException {
		//获取用户账号
		String userName = taken.getPrincipal().toString();
		TUser user = ts.findUserByUserName(userName);
		if(user!=null){
			//将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数传入realName。
			AuthenticationInfo ai = new SimpleAuthenticationInfo(user.getUserName(), user.getPassWord(), getName());
			return ai;
		}
		return null;
	}
	
	@Override
	public String getName() {
		return getClass().getName();
	}
}
