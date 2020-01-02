package com.entor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entor.entity.Student;
import com.entor.mapper.IStudentMapper;
import com.entor.service.IStudentService;

@Service
@CacheConfig(cacheNames = "student")
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentMapper studentMapper;

	@Override
	@Cacheable(key = "'student_'+#p0") // @Cacheable作用:先从缓存中查询是否有数据,如果有则返回,否则查询数据并存入缓存,如果传递id是23,则保存到缓存的key是student_23
	public Student queryById(int id) {
		return studentMapper.queryById(id);
	}

	@Override
	@Cacheable(key = "'student_'+#p0+'_'+#p1")
	public List<Student> queryByPage(int currentPage, int pageSize) {
		return studentMapper.queryByPage(currentPage, pageSize);
	}

	@Override
	public void add(Student student) {
		studentMapper.add(student);
	}

	@Override
	@CacheEvict(allEntries = true)
	public void deleteById(int id) {
		studentMapper.deleteById(id);
	}

	@Override
	public void deleteMore(String ids) {
		studentMapper.deleteMore(ids);
	}

	@Override
	public void addMore(List<Student> list) {
		studentMapper.addMore(list);
	}

	@Override
	@Transactional
	public void testTransaction(Student student, int id) {
		studentMapper.add(student);
//		int a = 1/0;
		studentMapper.deleteById(id);
	}

	@Override
	public List<Student> queryAll() {
		return studentMapper.queryAll();
	}

}
