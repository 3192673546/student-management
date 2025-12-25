package student.service;

import student.pojo.Student;

import java.util.List;

public interface StuService {
    List<Student> querystu();
    Student findById(int id);
    boolean addstu(Student stu);
    boolean updstu(Student stu);
    boolean delstu(int id);
}