package ttl.larku.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.net.URI;
import java.util.List;

/**
 * @author whynot
 */
@RestController
@RequestMapping("/students")
public class StudentController {

//    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudent() {
        List<Student> students = studentService.getAllStudents();
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneStudent(@PathVariable("id") int id) {
        Student s = studentService.getStudent(id);
        if(s == null) {
            return ResponseEntity.status(404).body("No Student found with id: " + id);
        }
        return ResponseEntity.ok(s);
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);

        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();

        //return ResponseEntity.created(newResource).body(newStudent);
        return ResponseEntity.created(newResource).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        boolean result = studentService.deleteStudent(id);
        if(!result) {
            return ResponseEntity.status(404).body("No Student found with id: " + id);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id,
                                           @RequestBody Student student) {
        boolean result = studentService.updateStudent(student);
        if(!result) {
            return ResponseEntity.status(404).body("No Student found with id: " + student.getId());
        }
        return ResponseEntity.noContent().build();
    }
}
