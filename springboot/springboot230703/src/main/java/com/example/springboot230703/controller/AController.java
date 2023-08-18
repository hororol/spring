package com.example.springboot230703.controller;

import com.example.springboot230703.dao.PeopleRepository;
import com.example.springboot230703.dao.PostsRepository;
import com.example.springboot230703.dao.ProductsRepository;
import com.example.springboot230703.dto.People;
import com.example.springboot230703.dto.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


@Controller
public class AController {
    @Autowired
    DataSource ds;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    SqlSession sqlSession;

    @GetMapping("/")
    public String index(Model model) throws IOException {
//        ArrayList<People> al = new ArrayList<>();
//        ArrayList<Product> products = new ArrayList<>();
//        Connection conn = null;
//        try{
//            conn = ds.getConnection();
//            PreparedStatement pstmt = conn.prepareStatement("select * from people");
//            ResultSet rs = pstmt.executeQuery();
//            while(rs.next()){
//                System.out.println("이름: " + rs.getString("name") + "\n나이: " + rs.getString("age"));
//                String name = rs.getString("name");
//                String age = rs.getString("age");
//                People p = new People(name, age);
////                p.setName(name);
////                p.setAge(age);
//                al.add(p);
//            }
//            pstmt = conn.prepareStatement("select * from products");
//            rs = pstmt.executeQuery();
//            while(rs.next()){
//                String name = rs.getString("name");
//                int price = rs.getInt("price");
//                int quantity = rs.getInt("quantity");
//                int idx = rs.getInt("idx");
//                Product p = new Product(idx, name, price, quantity);
//                products.add(p);
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//            if(conn != null){
//                try{
//                    conn.close();
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//        }
        model.addAttribute("al", peopleRepository.doselect());
        model.addAttribute("products", productsRepository.doselect());
        model.addAttribute("posts", postsRepository.doSelect()); // mybatis 사용X
        return "index";
    }

    @PostMapping("/post")
    public String post(String content, HttpServletRequest request){ // String content를 매개변수로 넘겨주면 String request.getParameter("content")를 실행하는 것과 같은 결과가 나온다.
        System.out.println("일로온나");
        System.out.println(content);
        postsRepository.doInsert(content);
        return "redirect:/";
    }
}
