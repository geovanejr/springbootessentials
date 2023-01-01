package br.com.geovanejunior.util;


import br.com.geovanejunior.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeraIdUtil {

    public Integer geraIdStudent() {

        return Student.studentList.size() + 1;
    }
}
