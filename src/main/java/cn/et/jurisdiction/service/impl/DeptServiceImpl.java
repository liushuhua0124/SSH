package cn.et.jurisdiction.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.jurisdiction.entity.Dept;
import cn.et.jurisdiction.entity.DeptExample;
import cn.et.jurisdiction.entity.DeptExample.Criteria;
import cn.et.jurisdiction.entity.TreeNode;
import cn.et.jurisdiction.mapper.DeptMapper;
import cn.et.jurisdiction.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	DeptMapper deptMapper;

	public List<TreeNode> queryTreeNode(Integer pid) {
		DeptExample DeptExample = new DeptExample();
		Criteria createCriteria = DeptExample.createCriteria();
		createCriteria.andPidEqualTo(pid);
		List<Dept> selectByExample = deptMapper.selectByExample(DeptExample);
		List<TreeNode> deptList = new ArrayList<TreeNode>();
		for (Dept dept : selectByExample) {
			TreeNode TreeNode = new TreeNode();
			TreeNode.setId(dept.getDid());
			TreeNode.setText(dept.getDname());
			if (queryTreeNode(dept.getDid()).size() == 0) {
				TreeNode.setState("open");
			}
			deptList.add(TreeNode);
		}
		return deptList;
	}

	public List<Dept> queryDept() {
		List<Dept> queryDept = deptMapper.queryDept();
		return queryDept;
	}
}
