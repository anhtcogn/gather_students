package com.gather_students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
//@RequestMapping("/import")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String viewHomePage() {
        return "home";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "search";
    }

    @PostMapping("/search")
    public String searchStudent(@RequestParam(name = "studentCode", defaultValue = "") String studentCode,
                                @RequestParam(name = "studentName", defaultValue = "") String studentName,
                                Model model) {
        model.addAttribute("students", studentService.searchStudent(studentCode, studentName));
        return "search";
    }

    @PostMapping("/import")
    public String importToDB(@RequestParam("file") MultipartFile files) throws IOException {
        studentService.importToDB(files);
        return "redirect:search";
    }
}

