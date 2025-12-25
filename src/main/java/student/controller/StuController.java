package student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import student.pojo.Student;
import student.service.StuService;

import java.util.List;

@RestController
public class StuController {
    @Autowired
    StuService s;
    @RequestMapping("querystu")
    public List<Student> querystu()
    {
        return s.querystu();
    }
    @RequestMapping("addstu")
    public boolean addstu(Student stu)
    {
        return s.addstu(stu);
    }
    @RequestMapping("updstu")
    public boolean updstu(Student stu)
    {
        return s.updstu(stu);
    }
    @RequestMapping("delstu")
    public boolean delstu(int id)
    {
        return s.delstu(id);
    }
}
