package it.company.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import it.company.entity.Employee;
import it.company.pojo.EmployeePojo;
import it.company.repository.EmployeeRepository;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
		@Autowired
		private EmployeeRepository employeeRepository;
		
		@Override
		public EmployeePojo saveEmployee(EmployeePojo employeePojo) {
			
			Employee emp = new Employee();
			
			emp.setEmpName(employeePojo.getEmpName());
			emp.setSalary(employeePojo.getSalary());
			emp.setContactNo(employeePojo.getContactNo());
			emp.setEmpid(emp.getEmpid()); 
			employeeRepository.save(emp);
			employeePojo.setEmpid(emp.getEmpid());
			return employeePojo;

		}

		@Override
		public EmployeePojo getEmployee(long empid) {
			Employee employee = employeeRepository.getById(empid);

			EmployeePojo employeePojo = new EmployeePojo();
			employeePojo.setEmpid(employee.getEmpid());
			employeePojo.setEmpName(employee.getEmpName());
			employeePojo.setSalary(employee.getSalary());
			employeePojo.setContactNo(employee.getContactNo());

			return employeePojo;
		}

		@Override
		public List<EmployeePojo> listEmployee() {
			List<EmployeePojo> EmployeePojoList = new ArrayList<EmployeePojo>();

			List<Employee> emp = employeeRepository.findAll();

			for (Employee employee : emp) {
				EmployeePojo employeePojo = new EmployeePojo();
				employeePojo.setEmpid(employee.getEmpid());
				employeePojo.setEmpName(employee.getEmpName());
				employeePojo.setSalary(employee.getSalary());
				employeePojo.setContactNo(employee.getContactNo());

				EmployeePojoList.add(employeePojo);
			}

			return EmployeePojoList;
		}

		public List<EmployeePojo> listEmployeebasedonsalary(double amt) {
			List<EmployeePojo> EmployeePojoList = new ArrayList<EmployeePojo>();

			List<Employee> emp = employeeRepository.findAll();
			
			for (Employee employee : emp) {
				EmployeePojo employeePojo = new EmployeePojo();
				double salary = employee.getSalary();
				if ((salary > amt)) {
					employeePojo.setEmpid(employee.getEmpid());
					employeePojo.setEmpName(employee.getEmpName());
					employeePojo.setSalary(employee.getSalary());
					employeePojo.setContactNo(employee.getContactNo());
					EmployeePojoList.add(employeePojo);
				}
			}

			return EmployeePojoList;
		}

		@Override
		public void update(EmployeePojo employeePojo) {
			Employee employee = employeeRepository.getById(employeePojo.getEmpid());
			employeePojo.setEmpName(employee.getEmpName());
			employeePojo.setSalary(employee.getSalary());
			employeePojo.setContactNo(employee.getContactNo());

		}

		@Override
		public void delete(long empid) {
			employeeRepository.deleteById(Long.valueOf(empid));
		}
		
		@Override
		public List<EmployeePojo> searchEmployeeBasedOnLetter(String str){
			List<EmployeePojo> EmployeePojoList = new ArrayList<EmployeePojo>();

			List<Employee> emp = employeeRepository.searchEmployeeNameLike(str);

			for(EmployeePojo employee : emp) {
				EmployeePojo employeePojo = new EmployeePojo();
				employeePojo.setEmpid(employee.getEmpid());
				employeePojo.setEmpName(employee.getEmpName());
				employeePojo.setSalary(employee.getSalary());
				employeePojo.setContactNo(employee.getContactNo());

				EmployeePojoList.add(employeePojo);
			}

			return EmployeePojoList;
		}
		
		public String Welcome() {
			return null;
		}

	
}

