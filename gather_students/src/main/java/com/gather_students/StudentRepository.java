package com.gather_students;

import com.gather_students.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("select s from Student s where s.code = :code")
    List<Student> findOneById(@Param("code") String code);


    @Query("select s from Student s where s.name = :name")
    List<Student> findAllByName(@Param("name") String name);
}
