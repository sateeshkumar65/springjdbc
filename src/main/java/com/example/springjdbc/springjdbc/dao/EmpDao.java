package com.example.springjdbc.springjdbc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springjdbc.springjdbc.model.EmployeeModel;
import com.example.springjdbc.springjdbc.model.EmployeeModelRowMapper;

@Repository
public class EmpDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	
	public int addEmployee(EmployeeModel employeeModel) {
		
		String empInsertQuery= "insert into empInfo values (?,?,?,?,?)" ;
		return jdbcTemplate.update(empInsertQuery, employeeModel.getEmpID(),employeeModel.getEmpName(),employeeModel.getAddress(),
				employeeModel.getCity(),employeeModel.getCountry());
		
	}
	
	public int addEmployeeAgain(EmployeeModel employeeModel) {
		
		String empInsertQuery= "insert into empInfo values (?,?,?,?,?)" ;
		return jdbcTemplate.update(empInsertQuery, new Object[] {employeeModel.getEmpID(),employeeModel.getEmpName(),employeeModel.getAddress(),
				employeeModel.getCity(),employeeModel.getCountry()});
		
	}
	
	public int addEmployeeWithNamedTemplate(EmployeeModel employeeModel) {
		
		String empInsertQuery= "insert into empInfo values (:empID,:empName,:address,:city,:country)" ;

		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("empID",employeeModel.getEmpID());
		paramMap.put("empName",employeeModel.getEmpName());
		paramMap.put("address", employeeModel.getAddress());
		paramMap.put("city",employeeModel.getCity());
		paramMap.put("country",employeeModel.getCountry());
		
		return namedJdbcTemplate.update(empInsertQuery, paramMap);
		
	}
	
	public EmployeeModel getEmpInfoById(int id) {
		
		String selectQuery="select * from empInfo where empID=?";
		try {
			return jdbcTemplate.queryForObject(selectQuery, new Object[] {id}, new EmployeeModelRowMapper());
		} catch(EmptyResultDataAccessException edr) {
			return null;
		}
		
	}
	
	public int updateEmpInfoById(int id, EmployeeModel empModel) {
		
		String selectQuery="update empInfo set empName=:empName, address=:address, city=:city, country=:country where empID=:empID";
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("empName",empModel.getEmpName());
		paramMap.put("address", empModel.getAddress());
		paramMap.put("city",empModel.getCity());
		paramMap.put("country",empModel.getCountry());
		paramMap.put("empID",id);
		
		return namedJdbcTemplate.update(selectQuery, paramMap);
		
	}
	
	public int deleteEmpDataById(int id) {
		String deleteQuery= "Delete from empInfo where empID=:empID";
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("empID", id);
		
		return namedJdbcTemplate.update(deleteQuery, paramMap);
		
	}
	
	public List<EmployeeModel> findALl(){
		String sql = "SELECT * FROM EMPINFO";
		
		
		List<EmployeeModel> employeeModelList=jdbcTemplate.query(sql,new BeanPropertyRowMapper<EmployeeModel>(EmployeeModel.class));
		
		return employeeModelList;
		
	}

}
