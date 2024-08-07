package com.student.information.Students;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class Data {

    private static final List<Information.Information1> data;

    static {
        data= new ArrayList<>();
        Information.Information1 logesh = new Information.Information1(1, "Logesh", 20, "Tirunelveli");
        Information.Information1 kanna = new Information.Information1(2, "kanna", 21, "Palayamkottai");
        data.add(logesh);
        data.add(kanna);
    }

    @GetMapping ("/api/students")
    public List<Information.Information1> getStudents() throws Exception {
        return data;
    }

    @GetMapping("/api/students/{Id}")
    public Information.Information1 getStudent(@PathVariable int Id) throws NullPointerException{
//       return data.stream().filter(data -> data.getId().equals(Id)).findFirst();

       int n = data.size();
       Object obj = new Object();
       for (Information.Information1 d : data) {
            if (Objects.equals(d.getId(), Id)) {
                return new Information.Information1(d.getId(),
                        d.getName(),
                        d.getAge(),
                        d.getAddress());
            }
        }
       return null;
    }
}
