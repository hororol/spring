package com.example.kb1.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class BoardRepository {
    public BoardRepository(){ // 생성자 : 객체생성될때 반드시 실행
        try{
            Class.forName("com.mysql.jdbc.Driver"); //jar파일을 들고와 로드한다.
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/mydb", "root", "1234");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from people");
            while(rs.next()){
                System.out.println(rs.getString("name") + "" + rs.getString("age"));
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}
