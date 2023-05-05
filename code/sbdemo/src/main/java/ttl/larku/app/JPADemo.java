package ttl.larku.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import ttl.larku.domain.Student;

import java.time.LocalDate;
import java.util.List;

/**
 * @author whynot
 */
public class JPADemo {

    private EntityManagerFactory factory;

    public JPADemo() {
        factory = Persistence.createEntityManagerFactory("LarkUPU_SE");
    }

    public static void main(String[] args) {
        JPADemo jpaDemo = new JPADemo();
        jpaDemo.updateStudent();
        jpaDemo.dumpStudents();
    }

    public void dumpStudents() {
        EntityManager manager = factory.createEntityManager();

        TypedQuery<Student> query = manager.createQuery("select s from Student s", Student.class);
        List<Student> students = query.getResultList();

        students.forEach(System.out::println);
    }

    public void insertStudent() {
        EntityManager manager = factory.createEntityManager();

        Student newStudent = new Student("Carl", "3838 93 3", LocalDate.of(1956, 3, 6), Student.Status.HIBERNATING);

        manager.getTransaction().begin();

        manager.persist(newStudent);

        manager.getTransaction().commit();
    }

    public void updateStudent() {
        EntityManager manager = factory.createEntityManager();

        Student newStudent = manager.find(Student.class, 26);

        manager.getTransaction().begin();

        newStudent.setName("Carlson");

        manager.getTransaction().commit();
    }
}
