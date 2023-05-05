package ttl.larku;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import ttl.larku.controller.StudentController;
import ttl.larku.controller.UriCreator;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author whynot
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @Mock
    private UriCreator uriCreator;

    @InjectMocks
    private StudentController studentController;

    public List<Student> students = List.of(
            new Student("Jeo", "383 9 393", LocalDate.of(2000, 10, 10), Student.Status.FULL_TIME),
            new Student("Rachna", "383 9 393", LocalDate.of(2000, 10, 10), Student.Status.FULL_TIME)
    );

    @Test
    public void testGetAllStudents() {
        Mockito.when(studentService.getAllStudents()).thenReturn(students);
        List<Student> result = studentController.getAllStudents();
        assertEquals(2, result.size());

        Mockito.verify(studentService).getAllStudents();
    }

    @Test
    public void testInsertStudent() throws URISyntaxException {
        Student s = students.get(0);
        URI uri = new URI("http://localhost:8080/student/2");

        Mockito.when(studentService.createStudent(s)).thenReturn(s);
        Mockito.when(uriCreator.getURI(0)).thenReturn(uri);

        ResponseEntity<?> result =
                studentController.addStudent(s);

        assertEquals(201, result.getStatusCode().value());

        Mockito.verify(studentService).createStudent(s);
    }

    @Test
    public void testDeleteNonExistentStudent() throws URISyntaxException {

        Mockito.when(studentService.deleteStudent(10)).thenReturn(false);

        ResponseEntity<?> result =
                studentController.deleteStudent(10);

        assertEquals(404, result.getStatusCode().value());

        Mockito.verify(studentService).deleteStudent(10);
    }

    @Test
    public void testDeleteExistentStudent() throws URISyntaxException {

        Mockito.when(studentService.deleteStudent(10)).thenReturn(true);

        ResponseEntity<?> result =
                studentController.deleteStudent(10);

        assertEquals(204, result.getStatusCode().value());

        Mockito.verify(studentService).deleteStudent(10);
    }
}
