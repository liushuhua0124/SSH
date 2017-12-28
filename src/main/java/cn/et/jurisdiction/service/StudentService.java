package cn.et.jurisdiction.service;

import cn.et.jurisdiction.entity.Student;
import cn.et.jurisdiction.entity.StudentExample;
import cn.et.jurisdiction.utils.PageTools;

public interface StudentService {
	public PageTools queryStudent(String stuname, Integer page, Integer rows);

	public Integer queryStudentCount(StudentExample studentExample);

	public void deleteStudent(Integer stuid);

	public void updateStudent(Student student);

	public void saveStudent(Student student);
}
