package com.example.jdbcmysql;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
public class ConnectionTest {
    @Test
    void test(){
        Connection connection=null;
        PreparedStatement statement=null;
        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB", "root", "example");
             statement=connection.prepareStatement("CREATE TABLE Persons ( PersonID int, name varchar(255) );");
             statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(statement!=null){
                try {
                    statement.close();
                }
                catch (SQLException e) {
                    System.out.println("e = " + e);
                }
            }

            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("e = " + e);
                }
            }
        }
    }
}
