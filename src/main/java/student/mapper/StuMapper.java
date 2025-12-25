package student.mapper;

import org.apache.ibatis.annotations.Mapper;
import student.pojo.Student;

import java.util.List;

@Mapper
public interface StuMapper {
    public List<Student> querystu();
    public Student findById(int id);
    public boolean addstu(Student stu);
    public boolean updstu(Student stu);
    public boolean delstu(int id);
}