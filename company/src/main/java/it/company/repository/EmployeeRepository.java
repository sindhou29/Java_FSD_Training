package it.company.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.company.entity.Employee;
import it.company.pojo.EmployeePojo;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Employee>{
	
	@Query("FROM Employee_details e WHERE lower(e.Name) LIKE lower(CONCAT(:EmpName, '%'))")
	 List<EmployeePojo> searchEmployeeNameLike(@Param("EmpName") String name);
}
