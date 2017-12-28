package cn.et.jurisdiction.conf;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.et.jurisdiction.entity.UserInfo;
import cn.et.jurisdiction.mapper.UserMapper;

@Component
public class MyDbRealm extends AuthorizingRealm {

	@Autowired
	UserMapper userMapper;

	/**
	 * 认证 将登录输入的用户名和密码和数据库中的用户名和密码对比,是否相等 返回值为null表示认证失败,非null认证通过
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 页面传入的token
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		UserInfo queryUser = userMapper.queryUser(upt.getPrincipal().toString());
		if (queryUser != null && queryUser.getPassword().equals(new String(upt.getPassword()))) {
			SimpleAccount SimpleAccount = new SimpleAccount(upt.getUsername(), upt.getPassword(), "MyDbRealm");
			return SimpleAccount;
		}
		return null;
	}

	/**
	 * 获取当前用户的授权数据 将当前用户在数据库的角色和权限加载到AuthorizationInfo 默认在进行授权是认证之后再调用 检查权限调用
	 * checkRole checkPerm
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = principals.getPrimaryPrincipal().toString();
		Set<String> queryRoleByName = userMapper.queryRoleByName(userName);
		Set<String> queryPemsByName = userMapper.queryPemsByName(userName);
		// 角色和权限集合对象
		SimpleAuthorizationInfo SimpleAuthorizationInfo = new SimpleAuthorizationInfo();
		SimpleAuthorizationInfo.setRoles(queryRoleByName);
		SimpleAuthorizationInfo.setStringPermissions(queryPemsByName);
		return SimpleAuthorizationInfo;
	}

}
