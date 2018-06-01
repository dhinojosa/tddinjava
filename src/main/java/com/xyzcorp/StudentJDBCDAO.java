package com.xyzcorp;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

public class StudentJDBCDAO implements StudentDAO {
    private Supplier<Connection> supplier;

    public StudentJDBCDAO(Supplier<Connection> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Boolean persist(Student student) throws SQLException {
        try (Connection connection = supplier.get();
             PreparedStatement preparedStatement= connection.prepareStatement
                     ("INSERT INTO Student (firstName, lastName) values (?, ?);")){
            return preparedStatement.execute();
        }
    }

    @Override
    public List<Student> getRegisteredStudentsByDate(LocalDate date) {
        return null;
    }
}
