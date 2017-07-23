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
 * ���ھ���Ҫ�����Զ����MyRealm�࣬������ǱȽ���Ҫ�ġ��̳���Shiro��AuthorizingRealm�࣬���ڴ����Լ�����֤�߼�
 * @author zhangwc
 *
 */
public class MyRealm extends AuthorizingRealm{
	
	@Resource
	private TUserService ts;
	/**
	 * ����Ȩ����֤��
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
	 * ���ڵ�½��֤���ڵ�¼��ʱ����Ҫ�����ݷ�װ��Shiro��һ��token�У�ִ��shiro��login()������֮��ֻҪ���ǽ�MyRealm��������õ�Spring�У���¼��ʱ��Shiro�ͻ��Զ��ĵ���doGetAuthenticationInfo()����������֤��
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken taken) throws AuthenticationException {
		//��ȡ�û��˺�
		String userName = taken.getPrincipal().toString();
		TUser user = ts.findUserByUserName(userName);
		if(user!=null){
			//����ѯ�����û��˺ź������ŵ� authenticationInfo���ں����Ȩ���жϡ���������������realName��
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
