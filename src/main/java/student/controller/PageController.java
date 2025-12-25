package student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import student.pojo.Student;
import student.service.StuService;

/**
 * 页面控制器 - 使用 Thymeleaf 模板引擎渲染视图
 *
 * @Controller 注解表示这是一个控制器，返回的是视图名称而不是数据
 * 与 @RestController 不同，@Controller 配合 Thymeleaf 使用
 */
@Controller
public class PageController {

    @Autowired
    private StuService stuService;

    /**
     * 首页 - 学生列表
     *
     * @param model Spring MVC 的 Model 对象，用于向视图传递数据
     * @return 返回视图名称 "list"，对应 templates/list.html
     */
    @GetMapping("/")
    public String index(Model model) {
        // 查询所有学生，放入 model 中
        model.addAttribute("students", stuService.querystu());
        return "list";  // 返回 templates/list.html
    }

    /**
     * 跳转到添加页面
     *
     * @return 返回视图名称 "add"，对应 templates/add.html
     */
    @GetMapping("/add")
    public String addPage() {
        return "add";  // 返回 templates/add.html
    }

    /**
     * 处理添加学生表单提交
     *
     * @param student 通过表单自动绑定的 Student 对象
     * @param redirectAttributes 用于重定向时传递消息
     * @return 重定向到首页
     */
    @PostMapping("/add")
    public String addStudent(Student student, RedirectAttributes redirectAttributes) {
        boolean success = stuService.addstu(student);
        if (success) {
            redirectAttributes.addFlashAttribute("msg", "添加学生成功！");
        }
        return "redirect:/";  // 重定向到首页
    }

    /**
     * 跳转到编辑页面
     *
     * @param id 从 URL 路径中获取的学生ID
     * @param model 用于向视图传递数据
     * @return 返回视图名称 "edit"，对应 templates/edit.html
     */
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") int id, Model model) {
        // 根据 ID 查询学生信息
        Student student = stuService.findById(id);
        model.addAttribute("student", student);
        return "edit";  // 返回 templates/edit.html
    }

    /**
     * 处理编辑学生表单提交
     *
     * @param student 通过表单自动绑定的 Student 对象
     * @param redirectAttributes 用于重定向时传递消息
     * @return 重定向到首页
     */
    @PostMapping("/update")
    public String updateStudent(Student student, RedirectAttributes redirectAttributes) {
        boolean success = stuService.updstu(student);
        if (success) {
            redirectAttributes.addFlashAttribute("msg", "修改学生信息成功！");
        }
        return "redirect:/";  // 重定向到首页
    }

    /**
     * 删除学生
     *
     * @param id 从 URL 路径中获取的学生ID
     * @param redirectAttributes 用于重定向时传递消息
     * @return 重定向到首页
     */
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        boolean success = stuService.delstu(id);
        if (success) {
            redirectAttributes.addFlashAttribute("msg", "删除学生成功！");
        }
        return "redirect:/";  // 重定向到首页
    }
}
