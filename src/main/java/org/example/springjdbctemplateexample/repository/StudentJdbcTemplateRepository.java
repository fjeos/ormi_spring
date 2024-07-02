package org.example.springjdbctemplateexample.repository;

import org.example.springjdbctemplateexample.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentJdbcTemplateRepository implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public StudentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select * from students", (rs, rowNum) ->
                new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("address")));
    }

    @Override
    public int insertStudent(Student student) {
        return jdbcTemplate.update("INSERT INTO students (name, age, address) VALUES (?, ?, ?)",
                student.getName(), student.getAge(), student.getAddress());
    }
}
