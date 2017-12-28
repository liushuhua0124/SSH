package cn.et.jurisdiction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.jurisdiction.entity.Result;
import cn.et.jurisdiction.entity.Student;
import cn.et.jurisdiction.service.StudentService;
import cn.et.jurisdiction.utils.PageTools;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;

	@ResponseBody
	@RequestMapping(value = "/queryStudent", method = RequestMethod.GET)
	public PageTools queryStudent(String stuname, Integer page, Integer rows) {
		PageTools queryStudent = studentService.queryStudent(stuname, page, rows);
		return queryStudent;
	}

	@ResponseBody
	@RequestMapping(value = "/student/{stuid}", method = RequestMethod.DELETE)
	public Result deleteStudent(@PathVariable String stuid) {
		String[] split = stuid.split(",");
		Result Result = new Result();
		Result.setCode(1);
		try {
			for (int i = 0; i < split.length; i++) {
				studentService.deleteStudent(Integer.parseInt(split[i]));
			}
		} catch (Exception e) {
			Result.setCode(0);
			Result.setMessage(e.getMessage());
		}
		return Result;
	}

	@ResponseBody
	@RequestMapping(value = "/student/{stuid}", method = RequestMethod.PUT)
	public Result updateStudent(@PathVariable Integer stuid, Student student) {
		student.setStuid(stuid);
		Result Result = new Result();
		Result.setCode(1);
		try {
			studentService.updateStudent(student);
		} catch (Exception e) {
			Result.setCode(0);
			Result.setMessage(e);
		}
		return Result;
	}

	@ResponseBody
	@RequestMapping(value = "/savestudent", method = RequestMethod.POST)
	public Result saveStudent(Student student) {
		Result Result = new Result();
		Result.setCode(1);
		try {
			studentService.saveStudent(student);
		} catch (Exception e) {
			Result.setCode(0);
			Result.setMessage(e);
		}
		return Result;
	}
}
