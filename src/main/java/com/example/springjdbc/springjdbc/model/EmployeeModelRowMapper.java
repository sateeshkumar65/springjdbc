package com.example.springjdbc.springjdbc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeModelRowMapper implements RowMapper<EmployeeModel> {

	@Override
	public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmployeeModel employeeModel = new EmployeeModel();
		
			employeeModel.setEmpID(rs.getInt("empID"));
			employeeModel.setEmpName(rs.getString("empName"));
			employeeModel.setAddress(rs.getString("address"));
			employeeModel.setCity(rs.getString("city"));
			employeeModel.setCountry(rs.getString("country"));
		
		return employeeModel;
		
	}

}
