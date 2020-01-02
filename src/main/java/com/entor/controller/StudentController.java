package com.entor.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entor.entity.Student;
import com.entor.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@RequestMapping("/queryById")
	public Student queryById(int id) {
		return studentService.queryById(id);
	}

	@RequestMapping("/queryByPage")
	public List<Student> queryByPage(int currentPage, int pageSize) {
		return studentService.queryByPage(currentPage, pageSize);
	}

	@RequestMapping("/queryByPage2")
	public List<Student> queryAll(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		return studentService.queryAll();
	}

	@RequestMapping("/queryByPage3")
	public PageInfo<Student> queryAll2(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		PageInfo<Student> pi = new PageInfo<Student>(studentService.queryAll());
		return pi;
	}

	@RequestMapping("/add")
	public void add() {
		Student student = new Student();
		student.setName("张三");
		student.setUsername("zhangsan");
		student.setPassword("123456");
		student.setSex(1);
		student.setAge(20);
		student.setBirthday(new Date());
		studentService.add(student);
	}

	@RequestMapping("/addMore")
	public void addMore() {
		List<Student> list = new ArrayList<Student>();
		for (int i = 1; i <= 100; i++) {
			Student student = new Student();
			student.setName("王多多" + i);
			student.setUsername("wangduoduo" + i);
			student.setPassword("123456");
			student.setSex(1);
			student.setAge(20);
			student.setBirthday(new Date());
			list.add(student);
		}
		studentService.addMore(list);
	}

	@RequestMapping("/deleteById")
	public void deleteById(int id) {
		studentService.deleteById(id);
	}

	@RequestMapping("/deleteMore")
	public void deleteMore(String ids) {
		studentService.deleteMore(ids);
	}

	@RequestMapping("/test")
	public void testTransaction(int id) {
		Student student = new Student();
		student.setName("邹业知3");
		student.setUsername("zouyezhi3");
		student.setPassword("123456");
		student.setSex(1);
		student.setAge(20);
		student.setBirthday(new Date());
		studentService.testTransaction(student, id);
	}
}
