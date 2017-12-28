package cn.et.jurisdiction.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.et.jurisdiction.entity.Menu;
import cn.et.jurisdiction.mapper.UserMapper;

@Controller
public class SpringLoginController {
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping("/loginShiro")
	public String login(String userName,String password,Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		//�û�������û���������
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		try {
			currentUser.login(token);
			List<Menu> queryMenuByUser = userMapper.queryMenuByUser(userName);
			model.addAttribute("queryMenuByUser",queryMenuByUser);
			return "/layout.jsp";
		} catch ( UnknownAccountException uae ) {
			System.out.println("�˺��������");
		} catch ( IncorrectCredentialsException ice ) {
			System.out.println("�����������");
		} catch ( LockedAccountException lae ) {
			System.out.println("�˺ű�����");
		} catch ( AuthenticationException ae ) {
			System.out.println("δ֪�쳣");
		}
		return "/error.html";
	}
}
