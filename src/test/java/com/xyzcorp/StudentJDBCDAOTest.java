package com.xyzcorp;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentJDBCDAOTest {

    @Test
    void testPersistOfAStudent() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connection
                .prepareStatement("INSERT INTO Student (firstName, lastName) values (?, ?);"))
                .thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenReturn(true);

        Student student = new Student("Bobby", "Sanchez");
        StudentJDBCDAO dao = new StudentJDBCDAO(() -> connection);

        Boolean status = dao.persist(student);
        assertThat(status).isEqualTo(true);
        verify(preparedStatement).close();
        verify(connection).close();
    }


    @Test
    void testPersistIntegration() throws SQLException {

        DataSource dataSource = null; //Plug in your own DataSource
        Student student = new Student("Bobby", "Sanchez");
        StudentJDBCDAO dao = new StudentJDBCDAO(() -> {
            try {
                return dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });

        Boolean status = dao.persist(student);
        assertThat(status).isEqualTo(true);

    }
    //test null datasource
    //test a bunch sql problems

    @Test
    public void testGetRegisteredStudentsByDate() throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection
                .prepareStatement("SELECT * FROM Student WHERE registrationDate = ?;"))
                .thenReturn(preparedStatement);
        LocalDate date = LocalDate.of(2017, 10, 11);
        preparedStatement.setDate(1, java.sql.Date.valueOf(date));
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        StudentJDBCDAO dao = new StudentJDBCDAO(() -> connection);
        List<Student> students = dao.getRegisteredStudentsByDate(date);
    }
}
