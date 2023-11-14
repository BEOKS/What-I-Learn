package com.example.jdbc.repository;

import com.example.jdbc.connection.DBConnectionUtil;
import com.example.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executor;

import static com.example.jdbc.connection.DBConnectionUtil.*;

@Slf4j
public class MemberReposditoryV0 {
    public Member save(Member member){
        String sql="insert into member(member_id,money) values (?,?)";
        Connection con=null;
        PreparedStatement statement=null;
        con= getConnection();
        try {
            statement=con.prepareStatement(sql);
            statement.setString(1,member.getId());
            statement.setInt(2,member.getMoney());
            statement.executeUpdate()
            return member;
        } catch (SQLException e) {
            log.error("db error",e);
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                close(con,statement,null)
            }
        }
    }

    private void close(Connection con, PreparedStatement statement, ResultSet resultSet) {
        if(statement!=null){
            try {
                handleSQLException(()->con.);
            }
        }
    }

    private void handleSQLException(Executor executor) {

    }

}
