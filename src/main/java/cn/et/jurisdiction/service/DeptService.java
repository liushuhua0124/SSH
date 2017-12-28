package cn.et.jurisdiction.service;

import java.util.List;

import cn.et.jurisdiction.entity.Dept;
import cn.et.jurisdiction.entity.TreeNode;

public interface DeptService {
	public List<TreeNode> queryTreeNode(Integer pid);

	public List<Dept> queryDept();
}

