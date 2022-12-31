package br.com.geovanejunior.endpoint;

import br.com.geovanejunior.model.Student;
import br.com.geovanejunior.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("student")
public class StudentEndpoint {

    @Autowired
    private DateUtil dateUtil;

    @GetMapping(path = "/list")
    public List<Student> listAll() {

//        System.out.println("Data: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return asList(new Student("Geovane"), new Student("Lilian"));
    }
}
