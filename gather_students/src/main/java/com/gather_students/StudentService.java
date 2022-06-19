package com.gather_students;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    private final DataFormatter formatter = new DataFormatter(Locale.US);

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public List<Student> searchStudent(String studentCode, String studentName) {
        studentCode = studentCode.replace(" ", "%");
        if (studentCode.equals("")) {
            return studentRepository.findAllByName(studentName);
        } else {
            return studentRepository.findOneById(studentCode);
        }
    }

    public String importToDB(@RequestParam("file") MultipartFile files) throws IOException {
        List<Student> students = new ArrayList<>();
        XSSFWorkbook wb = new XSSFWorkbook(files.getInputStream());
        XSSFSheet ws = wb.getSheetAt(0);

        for (int i = 5; i < ws.getPhysicalNumberOfRows(); i++) {
            Student student = new Student();
            XSSFRow row = ws.getRow(i);
            student.setSchool(row.getCell(1).getStringCellValue());
            student.setDistrict(row.getCell(2).getStringCellValue());
            student.setCode(row.getCell(3).getStringCellValue());
            student.setClassName(row.getCell(4).getStringCellValue());
            student.setName(row.getCell(5).getStringCellValue());
            student.setDob(row.getCell(6).getStringCellValue() + "/"
                    + row.getCell(7).getStringCellValue() + "/" + row.getCell(8).getStringCellValue());
            student.setSex(row.getCell(9).getStringCellValue());
            student.setBirthplace(row.getCell(10).getStringCellValue());
            student.setEthnic(row.getCell(11).getStringCellValue());
            student.setAddress(row.getCell(12).getStringCellValue());
            student.setTelephone(row.getCell(13).getStringCellValue());
            student.setScore1(Double.parseDouble(formatter.formatCellValue(row.getCell(14))));
            student.setScore2(Double.parseDouble(formatter.formatCellValue(row.getCell(15))));
            student.setScore3(Double.parseDouble(formatter.formatCellValue(row.getCell(16))));
            student.setScore4(Double.parseDouble(formatter.formatCellValue(row.getCell(17))));
            student.setScore5(Double.parseDouble(formatter.formatCellValue(row.getCell(18))));
            student.setTotalScore5(student.getScore1()+student.getScore2()+student.getScore3()
                    +student.getScore4()+student.getScore5());
            if(!formatter.formatCellValue(row.getCell(20)).isEmpty()){
                student.setPriorityScore(Double.parseDouble(formatter.formatCellValue(row.getCell(20))));
            }else{
                student.setPriorityScore((double) 0);
            }
            student.setTotalScore(student.getTotalScore5()+student.getPriorityScore());
            student.setNote(row.getCell(22).getStringCellValue());
            students.add(student);

        }
        wb.close();
        studentRepository.saveAll(students);
        return "search";
    }
}
