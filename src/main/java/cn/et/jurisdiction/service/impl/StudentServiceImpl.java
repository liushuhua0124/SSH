package cn.et.jurisdiction.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.jurisdiction.entity.Student;
import cn.et.jurisdiction.entity.StudentExample;
import cn.et.jurisdiction.entity.StudentExample.Criteria;
import cn.et.jurisdiction.mapper.StudentMapper;
import cn.et.jurisdiction.service.StudentService;
import cn.et.jurisdiction.utils.PageTools;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentMapper studentMapper;

	public PageTools queryStudent(String stuname, Integer page, Integer rows) {
		if (stuname == null) {
			stuname = "";
		}
		StudentExample studentExample = new StudentExample();
		Criteria createCriteria = studentExample.createCriteria();
		createCriteria.andStunameLike("%" + stuname + "%");
		Integer queryStudentCount = queryStudentCount(studentExample);
		PageTools PageTools = new PageTools(page, queryStudentCount, rows);
		RowBounds RowBounds = new RowBounds(PageTools.getStartIndex() - 1, rows);
		List<Student> selectByExampleWithRowbounds = studentMapper.selectByExampleWithRowbounds(studentExample,
				RowBounds);
		PageTools.setRows(selectByExampleWithRowbounds);
		return PageTools;
	}

	public Integer queryStudentCount(StudentExample studentExample) {
		Integer countByExample = studentMapper.countByExample(studentExample);
		return countByExample;
	}

	public void deleteStudent(Integer stuid) {
		studentMapper.deleteByPrimaryKey(stuid);
	}

	public void updateStudent(Student student) {
		studentMapper.updateByPrimaryKey(student);
	}

	public void saveStudent(Student student) {
		studentMapper.insert(student);
	}
}
