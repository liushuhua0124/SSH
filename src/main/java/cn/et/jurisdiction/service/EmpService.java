package cn.et.jurisdiction.service;

import java.util.List;

import cn.et.jurisdiction.entity.Emp;

public interface EmpService {

	public List<Emp> queryEmp(Integer eid);

	public void deleteEmp(Integer eid);

	public void updateEmp(Emp emp);

	public void saveEmp(Emp emp);
}
