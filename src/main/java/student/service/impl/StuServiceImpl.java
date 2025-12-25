package student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.mapper.StuMapper;
import student.pojo.Student;
import student.service.StuService;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    StuMapper m;
    @Override
    public List<Student> querystu()
    {
        return m.querystu();
    }
    @Override
    public Student findById(int id) {
        return m.findById(id);
    }
    @Override
    public boolean addstu(Student stu){
        return m.addstu(stu);
    }
    @Override
    public boolean updstu(Student stu)
    {
        return m.updstu(stu);
    }
    @Override
    public boolean delstu(int id)
    {
        return m.delstu(id);
    }
}