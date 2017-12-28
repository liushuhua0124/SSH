package cn.et.jurisdiction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.jurisdiction.entity.Emp;
import cn.et.jurisdiction.entity.Result;
import cn.et.jurisdiction.service.EmpService;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	@ResponseBody
	@RequestMapping(value="/queryEmp",method = RequestMethod.GET)
	public List<Emp> queryEmp(Integer id){
		List<Emp> queryEmp = empService.queryEmp(id);
		return queryEmp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteEmp/{eid}", method = RequestMethod.DELETE)
	public Result deleteEmp(@PathVariable String eid){
		String[] split = eid.split(",");
		Result Result = new Result();
		Result.setCode(1);
		try {
			for(int i = 0;i<split.length;i++){
				empService.deleteEmp(Integer.parseInt(split[i]));
			}
		} catch (Exception e) {
			Result.setCode(0);
			Result.setMessage(e.getMessage());
		}
		return Result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateEmp/{eid}", method = RequestMethod.PUT)
	public Result updateEmp(@PathVariable Integer eid, Emp emp){
		emp.setEid(eid);
		Result Result = new Result();
		Result.setCode(1);
		try {
			empService.updateEmp(emp);
		} catch (Exception e) {
			Result.setCode(0);
			Result.setMessage(e);
		}
		return Result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveEmp", method = RequestMethod.POST)
	public Result saveStudent(Emp emp){
		Result Result = new Result();
		Result.setCode(1);
		try {
			empService.saveEmp(emp);
		} catch (Exception e) {
			Result.setCode(0);
			Result.setMessage(e);
		}
		return Result;
	}
}
