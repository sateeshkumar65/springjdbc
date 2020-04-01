package com.example.springjdbc.springjdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.springjdbc.springjdbc.dao.EmpDao;
import com.example.springjdbc.springjdbc.model.EmployeeModel;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmpDao empDao;
	
	@PostMapping("/createEmp")
	public int createEmp(@RequestBody EmployeeModel model) {
		
		//return empDao.addEmployeeAgain(model);
		
		return empDao.addEmployeeWithNamedTemplate(model);
		
	}
	
	@GetMapping("/createEmp")
	public int getEmpInfoById(@RequestParam("empId") int id) {
		
		EmployeeModel empModel= empDao.getEmpInfoById(id);
		if(empModel == null) {
			return -1;
		}else {
		System.out.println("emp ID: " +empModel.getEmpID() + " emp Name:" + empModel.getEmpName() +" address : "+ empModel.getAddress()
		+" city : "+empModel.getCity()+ " Country : "+ empModel.getCountry());
		 return 0;
		}
		
	}
	
	@GetMapping("/selectRows")
	public List<EmployeeModel> getAllRows(@RequestParam("empId") int id) {
		return empDao.findALl();
	}
	
	@GetMapping("/createEmp/{empId}")
	public int getEmpInfoByIdByPath(@PathVariable("empId") int id) {
		
		EmployeeModel empModel= empDao.getEmpInfoById(id);
		if(empModel == null) {
			return -1;
		}else {
		System.out.println("Path Variable emp ID: " +empModel.getEmpID() + " emp Name:" + empModel.getEmpName() +" address : "+ empModel.getAddress()
		+" city : "+empModel.getCity()+ " Country : "+ empModel.getCountry());
		 return 0;
		}
		
	}
	
	@PutMapping("/createEmp/{empId}")
	public int updateEmpDataById(@PathVariable("empId") int empId,@RequestBody EmployeeModel model) {
		
		return empDao.updateEmpInfoById(empId, model); 
	}
	
	
	
	@DeleteMapping("/createEmp/{empId}")
	public int deleteEmpDataById(@PathVariable("empId") int empId,@RequestBody EmployeeModel model) {
		
		return empDao.updateEmpInfoById(empId, model); 
	}
	
	

}
