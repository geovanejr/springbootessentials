package br.com.geovanejunior.endpoint;

import br.com.geovanejunior.error.CustomErrorType;
import br.com.geovanejunior.model.Student;
import br.com.geovanejunior.util.DateUtil;
import br.com.geovanejunior.util.GeraIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    private final DateUtil dateUtil;
    private final GeraIdUtil geraIdUtil;

    public StudentEndpoint(DateUtil dateUtil, GeraIdUtil geraIdUtil) {
        this.dateUtil = dateUtil;
        this.geraIdUtil = geraIdUtil;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<?> listAll() {

        System.out.println("Data: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {

        Student student = new Student();
        student.setId(id);
        int index = Student.studentList.indexOf(student);

        if (index == -1) {
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {

        student.setId(geraIdUtil.geraIdStudent());
        Student.studentList.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        Student student = new Student();
        student.setId(id);
        int index = Student.studentList.indexOf(student);

        if (index == -1) {
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Student.studentList.remove(index), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> delete(@RequestBody Student student) {

        Student studentTemp = new Student();
        studentTemp.setId(student.getId());
        int index = Student.studentList.indexOf(studentTemp);

        if (index == -1) {
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        }

        Student.studentList.remove(index);
        Student.studentList.add(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
