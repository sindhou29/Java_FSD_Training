package it.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.company.entity.Employee;
import it.company.pojo.EmployeePojo;
import it.company.services.EmployeeService;


@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;


	@GetMapping(value = "/")
	public String Welcome() {
		return "CRUD Operation";
	}
	
	@PostMapping(value = "/save")
	public EmployeePojo saveEmployee(@RequestBody EmployeePojo employeePojo) {

		employeePojo = employeeService.saveEmployee(employeePojo);

		return employeePojo;
	}

	@GetMapping("api/get/{EmpId}")
	public EmployeePojo getEmployee(@PathVariable("Empid") long employeeId) {

		EmployeePojo employeePojo = employeeService.getEmployee(employeeId);

		return employeePojo;
	}

	@GetMapping("api/list")
	public List<EmployeePojo> listEmployee() {

		List<EmployeePojo> listEmployee = employeeService.listEmployee();
		
		return listEmployee;
	}
	
	//list for salary>amount
	@GetMapping("api/list/{Amount}")
	public List<EmployeePojo> listEmployeeBasedOnAge(@PathVariable("Amount") double amt) {

		List<EmployeePojo> listEmployeeBasedOnAge = employeeService.listEmployeebasedonsalary(amt);
		
		return listEmployeeBasedOnAge;
	}
	
	//list for name starting with given string
	@GetMapping("api/list/{Str}")
	public List<EmployeePojo> searchEmployeeBasedOnLetter(@PathVariable("Str") String str){
		List<EmployeePojo> list = employeeService.searchEmployeeBasedOnLetter(str);
		
		return list;
	}

	@PostMapping(value = "/update")
	public EmployeePojo updateEmployee(@RequestBody EmployeePojo employeePojo) {

		employeeService.update(employeePojo);
		employeePojo = employeeService.getEmployee(employeePojo.getEmpid());
		return employeePojo;
	}

	@DeleteMapping(value = "/delete/{Empid}")
	public String deleteEmployee(@PathVariable("Empid") long empId) {
		employeeService.delete(empId);
		return "Employee deleted with id: " + empId;
	}

	
}
