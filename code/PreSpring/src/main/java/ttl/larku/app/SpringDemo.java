package ttl.larku.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.jconfig.LarkUConfig;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.util.List;

/**
 * @author whynot
 */
public class SpringDemo {

    public static void main(String[] args) {
        SpringDemo sd = new SpringDemo();
//        sd.go();
        sd.goCourse();
    }

    public void go() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);

        StudentService studentService = context.getBean("studentService", StudentService.class);

        StudentService studentService2 = context.getBean("studentService", StudentService.class);

        List<Student> students = studentService.getAllStudents();
        System.out.println("student: " + students);
    }

    public void goCourse() {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);

        CourseService studentService = context.getBean("courseService", CourseService.class);


        List<Course> result = studentService.getAllCourses();
        System.out.println("courses: " + result);
    }
}
