package cn.et.jurisdiction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.jurisdiction.entity.Dept;
import cn.et.jurisdiction.entity.TreeNode;
import cn.et.jurisdiction.service.DeptService;

@Controller
public class DeptController {

	@Autowired
	DeptService deptService;

	@ResponseBody
	@RequestMapping("/queryDept")
	public List<TreeNode> queryDept(Integer id) {
		if (id == null) {
			id = 0;
		}
		List<TreeNode> queryTreeNode = deptService.queryTreeNode(id);
		return queryTreeNode;
	}
	
	@ResponseBody
	@RequestMapping("/queryDeptAll")
	public List<Dept> query(){
		List<Dept> queryDept = deptService.queryDept();
		return queryDept;
	}
}
