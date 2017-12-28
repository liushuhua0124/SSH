package cn.et.jurisdiction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.jurisdiction.entity.Emp;
import cn.et.jurisdiction.entity.EmpExample;
import cn.et.jurisdiction.entity.EmpExample.Criteria;
import cn.et.jurisdiction.mapper.EmpMapper;
import cn.et.jurisdiction.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpMapper empMapper;

	public List<Emp> queryEmp(Integer did) {
		EmpExample EmpExample = new EmpExample();
		Criteria createCriteria = EmpExample.createCriteria();
		if (did != null) {
			createCriteria.andDidEqualTo(did);
		}
		List<Emp> selectByExample = empMapper.selectByExample(EmpExample);
		return selectByExample;
	}

	public void deleteEmp(Integer eid) {
		empMapper.deleteByPrimaryKey(eid);
	}

	public void saveEmp(Emp emp) {
		empMapper.insert(emp);
	}

	public void updateEmp(Emp emp) {
		empMapper.updateByPrimaryKey(emp);
	}

}
