package com.hotnerds.badgeroad;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.fail;

@SpringBootTest
public class JDBCTests {

    @Value("${spring.datasource.url}")
    String url_;

    @Value("${spring.datasource.username}")
    String user_;

    @Value("${spring.datasource.password}")
    String password_;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {

        String url = "jdbc:mysql://localhost:3306/badgeroad?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "jeong1998";
        try(Connection con =
                    DriverManager.getConnection(
                            url,
                            user,
                            password)){
            System.out.println(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}
