package br.com.geovanejunior.endpoint;

import br.com.geovanejunior.error.CustomErrorType;
import br.com.geovanejunior.model.Student;
import br.com.geovanejunior.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    private StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<?> listAll() {

        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") long id) {

        Optional<Student> student = studentDAO.findById(id);

        if (!student.isPresent()) {
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable("name") String name) {

        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {

        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Student student) {

        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
