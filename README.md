# 🎓 学生管理系统 / Student Management System

> 基于 Spring Boot + MyBatis + Thymeleaf 的学生信息管理系统

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![MyBatis](https://img.shields.io/badge/MyBatis-3.0.3-blue)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple)

## 项目简介

这是一个完整的学生信息管理系统，实现了学生信息的增删改查（CRUD）功能。项目采用经典的三层架构设计，使用 Spring Boot 作为基础框架，MyBatis 作为持久层框架，Thymeleaf 作为模板引擎进行服务端渲染。

## 技术栈详解

### 后端技术

| 技术 | 版本 | 说明 |
|------|------|------|
| **Spring Boot** | 3.2.0 | 基础框架，提供自动配置、依赖注入、AOP等核心功能 |
| **Spring MVC** | 6.1.1 | Web层框架，处理HTTP请求和响应 |
| **MyBatis** | 3.0.3 | ORM框架，简化数据库操作，支持XML配置SQL |
| **MySQL Connector** | 8.x | MySQL数据库驱动 |
| **Lombok** | 1.18.30 | 简化Java代码，自动生成getter/setter等方法 |

### 前端技术

| 技术 | 版本 | 说明 |
|------|------|------|
| **Thymeleaf** | 3.1 | 服务端模板引擎，支持HTML5，可直接在浏览器预览 |
| **Bootstrap** | 5.3 | CSS框架，提供响应式布局和美观的UI组件 |
| **Bootstrap Icons** | 1.10 | 图标库，提供丰富的矢量图标 |

### 开发工具

| 工具 | 说明 |
|------|------|
| **Maven** | 项目构建和依赖管理 |
| **Docker** | 容器化部署 |
| **Docker Compose** | 多容器编排 |

## 项目架构

### 目录结构

```
student/
├── src/
│   ├── main/
│   │   ├── java/student/
│   │   │   ├── StudentApplication.java    # Spring Boot 启动类
│   │   │   ├── controller/
│   │   │   │   ├── PageController.java    # 页面控制器（Thymeleaf视图）
│   │   │   │   └── StuController.java     # REST API控制器
│   │   │   ├── service/
│   │   │   │   ├── StuService.java        # 服务层接口
│   │   │   │   └── impl/
│   │   │   │       └── StuServiceImpl.java # 服务层实现
│   │   │   ├── mapper/
│   │   │   │   └── StuMapper.java         # MyBatis Mapper接口
│   │   │   └── pojo/
│   │   │       └── Student.java           # 实体类
│   │   └── resources/
│   │       ├── application.properties     # 应用配置文件
│   │       ├── mapper/
│   │       │   └── StuMapper.xml          # MyBatis SQL映射文件
│   │       ├── templates/                 # Thymeleaf模板目录
│   │       │   ├── list.html              # 学生列表页
│   │       │   ├── add.html               # 添加学生页
│   │       │   └── edit.html              # 编辑学生页
│   │       └── static/                    # 静态资源目录
│   └── test/                              # 测试代码
├── pom.xml                                # Maven配置
├── Dockerfile                             # Docker构建文件
├── docker-compose.yml                     # Docker编排文件
└── init.sql                               # 数据库初始化脚本
```

### 三层架构设计

```
┌─────────────────────────────────────────────────────────────┐
│                      Presentation Layer                      │
│  ┌─────────────────┐    ┌─────────────────────────────────┐ │
│  │  Thymeleaf      │    │  PageController                 │ │
│  │  Templates      │◄───│  @Controller                    │ │
│  │  (list.html)    │    │  返回视图名称                    │ │
│  └─────────────────┘    └─────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                       Business Layer                         │
│  ┌─────────────────────────────────────────────────────────┐│
│  │  StuService (Interface)                                 ││
│  │  └── StuServiceImpl (@Service)                          ││
│  │      - 业务逻辑处理                                      ││
│  │      - 事务管理                                          ││
│  └─────────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                      Persistence Layer                       │
│  ┌─────────────────────────────────────────────────────────┐│
│  │  StuMapper (@Mapper)                                    ││
│  │  └── StuMapper.xml                                      ││
│  │      - SQL语句定义                                       ││
│  │      - 结果映射                                          ││
│  └─────────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                        Database Layer                        │
│  ┌─────────────────────────────────────────────────────────┐│
│  │  MySQL 8.0                                              ││
│  │  └── student表 (id, name)                               ││
│  └─────────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────────┘
```

## 核心技术实现

### 1. Spring Boot 自动配置

```java
@SpringBootApplication  // 组合注解，包含：
// @Configuration - 标记为配置类
// @EnableAutoConfiguration - 启用自动配置
// @ComponentScan - 组件扫描
public class StudentApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }
}
```

### 2. MyBatis 数据持久化

**Mapper接口定义：**
```java
@Mapper  // 标记为MyBatis的Mapper接口
public interface StuMapper {
    List<Student> querystu();    // 查询所有
    Student findById(int id);    // 根据ID查询
    boolean addstu(Student stu); // 添加
    boolean updstu(Student stu); // 更新
    boolean delstu(int id);      // 删除
}
```

**XML映射文件：**
```xml
<mapper namespace="student.mapper.StuMapper">
    <select id="querystu" resultType="student.pojo.Student">
        SELECT * FROM student
    </select>
    <insert id="addstu" parameterType="student.pojo.Student">
        INSERT INTO student(id, name) VALUES (#{id}, #{name})
    </insert>
</mapper>
```

### 3. Thymeleaf 模板渲染

**控制器返回视图：**
```java
@Controller  // 注意：不是@RestController
public class PageController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", stuService.querystu());
        return "list";  // 返回视图名，对应templates/list.html
    }
}
```

**Thymeleaf模板语法：**
```html
<!-- 循环渲染 -->
<tr th:each="student : ${students}">
    <td th:text="${student.id}"></td>
    <td th:text="${student.name}"></td>
</tr>

<!-- URL表达式 -->
<a th:href="@{/edit/{id}(id=${student.id})}">编辑</a>

<!-- 条件判断 -->
<div th:if="${msg}" th:text="${msg}"></div>
```

### 4. 依赖注入 (DI)

```java
@Service
public class StuServiceImpl implements StuService {
    @Autowired  // 自动注入Mapper
    private StuMapper mapper;

    @Override
    public List<Student> querystu() {
        return mapper.querystu();
    }
}
```

## 功能特性

- [x] **查询** - 显示所有学生列表
- [x] **添加** - 添加新学生
- [x] **编辑** - 修改学生信息
- [x] **删除** - 删除学生（带确认提示）
- [x] **响应式布局** - 支持PC和移动端
- [x] **操作反馈** - 成功提示消息
- [x] **Docker部署** - 一键启动

## 快速开始

### 方式一：Docker部署（推荐）

```bash
# 克隆项目
git clone https://github.com/YOUR_USERNAME/student-management.git
cd student-management

# 启动服务
docker-compose up -d

# 访问
open http://localhost:8082
```

### 方式二：本地开发

**1. 环境要求**
- JDK 21+
- Maven 3.8+
- MySQL 8.0+

**2. 创建数据库**
```sql
CREATE DATABASE manager DEFAULT CHARACTER SET utf8mb4;
USE manager;
CREATE TABLE student (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

**3. 修改配置**

编辑 `application.properties`：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/manager
spring.datasource.username=root
spring.datasource.password=your_password
```

**4. 运行项目**
```bash
mvn spring-boot:run
```

**5. 访问系统**

打开浏览器访问：http://localhost:8082

## API接口（REST）

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/` | 学生列表页面 |
| GET | `/add` | 添加学生页面 |
| POST | `/add` | 提交添加表单 |
| GET | `/edit/{id}` | 编辑学生页面 |
| POST | `/update` | 提交编辑表单 |
| GET | `/delete/{id}` | 删除学生 |

## 项目截图

*待添加*

## 学习要点

1. **Spring Boot 核心概念**
   - 自动配置原理
   - 依赖注入（DI）
   - 面向切面编程（AOP）

2. **MyBatis 使用**
   - Mapper接口与XML映射
   - 参数绑定 `#{}`
   - 结果映射

3. **Thymeleaf 模板**
   - 表达式语法：`${}`、`@{}`、`*{}`
   - 迭代：`th:each`
   - 条件：`th:if`、`th:unless`

4. **三层架构设计**
   - Controller → Service → Mapper
   - 各层职责分离
   - 接口与实现分离

## License

MIT License

## 作者

[Your Name]
