package com.ems.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.entity.Employee;
import com.ems.model.EmployeeModel;
import com.ems.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeRepository    repo;
	
	@GetMapping("/index")
	public   String  getIndexPage() {
		return  "index";
	}
	
	@GetMapping("/loggedOut")
	public  String  logoutHandler() {
		return  "userLoggedOut";
	}
		
	@GetMapping("/addEmployee")
	public  String  addEmployeePage(Model   model) {
		EmployeeModel   em=new  EmployeeModel();
		model.addAttribute("emodel", em);
		return  "addEmployee";
	}
	
	@PostMapping("/saveEmployee")
	public   String   saveEmployee(@ModelAttribute("emodel") EmployeeModel  employeeModel, Model  model) {
		Employee  employee =new  Employee();
		BeanUtils.copyProperties(employeeModel, employee);
		boolean  flag = repo.existsById(employee.getEmpno());
		if(flag==true) {
			model.addAttribute("message", "Sorry, Employee already exists with the given empno");
			return  "index";
		}
		else {
			repo.save(employee);
			model.addAttribute("message", "Employee  added  to  Database");
			return  "index";
		}
		
	}
	
	@GetMapping("/listEmployees")
	public   String   listEmployees(Model  model) {
		
		List<Employee>  empList = repo.findAll();
		
		List<EmployeeModel>   empModelList = new  ArrayList<EmployeeModel>();
		
		for(Employee  e :  empList) {
			EmployeeModel  em =new  EmployeeModel();
			BeanUtils.copyProperties(e, em);
			empModelList.add(em);
		}
		
		model.addAttribute("employees", empModelList);
		return  "employeesList";
	}
	
	@GetMapping("/editEmployee")
	/*
	 * @PreAuthorize: allows  a user with a specific username and a role to access a method
	 */
	@PreAuthorize(value="hasRole('ROLE_ADMIN') && authentication.name=='Tanish' ")
	public  String   editEmployeePage(@RequestParam("id") Integer  empno, Model model) {
		Optional<Employee>  opt = repo.findById(empno);
		Employee  e = opt.get();
		EmployeeModel   em =new  EmployeeModel();
		BeanUtils.copyProperties(e, em);
		model.addAttribute("emp", em);
		return  "editEmployee";		
	}
	
	@PostMapping("/updateEmployee")
	public   String    updateEmployee(@ModelAttribute("emp")  EmployeeModel  emodel) {
		Employee   e =new  Employee();
		BeanUtils.copyProperties(emodel, e);
		repo.saveAndFlush(e);
		return   "redirect:listEmployees";
	}
	
	@GetMapping("/deleteEmployee")
	public   String   deleteEmployee(@RequestParam("id")Integer  empno) {
		repo.deleteById(empno);
		return "redirect:listEmployees";
	}
	
}
