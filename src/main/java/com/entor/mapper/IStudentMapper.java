package com.entor.mapper;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entor.entity.Student;

public interface IStudentMapper {

	@Select("select * from student where id = #{id}")
	public Student queryById(int id);

	@Select("select * from student limit #{currentPage},#{pageSize}")
	public List<Student> queryByPage(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

	@Insert("insert into student(name,username,password,sex,age,birthday,createTime) values(#{name},#{username},#{password},#{sex},#{age},#{birthday},now())")
	public void add(Student student);

	@Delete("delete from student where id = #{id}")
	public void deleteById(int id);

	@DeleteProvider(type = Provider.class, method = "deleteMore")
	public void deleteMore(@Param("ids") String ids);

	@InsertProvider(type = Provider.class, method = "addMore")
	public void addMore(@Param("list") List<Student> list);

	@Select("select * from student")
	public List<Student> queryAll();

	public void addMore();

	class Provider {
		public String deleteMore(String ids) {
			return "delete from student where id in (" + ids + ")";
		}

		public String addMore(List<Student> list) {
			StringBuffer sb = new StringBuffer();
			sb.append("insert into student(name,username,password,sex,age,birthday,createTime) values");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (Student s : list) {
				sb.append("('" + s.getName() + "','" + s.getUsername() + "','" + s.getPassword() + "'," + s.getSex()
						+ "," + s.getAge() + ",'" + sdf.format(s.getBirthday()) + "',now()),");
			}
			return sb.substring(0, sb.length() - 1);
		}
	}
}
