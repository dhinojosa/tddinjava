package com.xyzcorp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface StudentDAO {
    public Boolean persist(Student student) throws SQLException;
    public List<Student> getRegisteredStudentsByDate(LocalDate date) throws SQLException;
}
