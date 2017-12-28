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
	 * ��֤ ����¼������û�������������ݿ��е��û���������Ա�,�Ƿ���� ����ֵΪnull��ʾ��֤ʧ��,��null��֤ͨ��
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// ҳ�洫���token
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		UserInfo queryUser = userMapper.queryUser(upt.getPrincipal().toString());
		if (queryUser != null && queryUser.getPassword().equals(new String(upt.getPassword()))) {
			SimpleAccount SimpleAccount = new SimpleAccount(upt.getUsername(), upt.getPassword(), "MyDbRealm");
			return SimpleAccount;
		}
		return null;
	}

	/**
	 * ��ȡ��ǰ�û�����Ȩ���� ����ǰ�û������ݿ�Ľ�ɫ��Ȩ�޼��ص�AuthorizationInfo Ĭ���ڽ�����Ȩ����֤֮���ٵ��� ���Ȩ�޵���
	 * checkRole checkPerm
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = principals.getPrimaryPrincipal().toString();
		Set<String> queryRoleByName = userMapper.queryRoleByName(userName);
		Set<String> queryPemsByName = userMapper.queryPemsByName(userName);
		// ��ɫ��Ȩ�޼��϶���
		SimpleAuthorizationInfo SimpleAuthorizationInfo = new SimpleAuthorizationInfo();
		SimpleAuthorizationInfo.setRoles(queryRoleByName);
		SimpleAuthorizationInfo.setStringPermissions(queryPemsByName);
		return SimpleAuthorizationInfo;
	}

}
