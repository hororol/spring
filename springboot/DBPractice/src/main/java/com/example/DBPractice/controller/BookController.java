package com.example.DBPractice.controller;

import com.example.DBPractice.dao.BookRepository;
import com.example.DBPractice.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/search")
    public String search(Model model, BookDTO bookDTO){
        List<BookDTO> bookList = bookRepository.doSelect(bookDTO);
        model.addAttribute("bookList", bookList);
        return "book/search";
    }

    @GetMapping("/view")
    public String view(Model model, BookDTO bookDTO){
        bookDTO = bookRepository.doSelectOne(bookDTO);
        System.out.println(bookDTO);
        model.addAttribute("bookDetail", bookDTO);
        return "book/view";
    }

}

// @RequestMapping은 클래스 레벨과 메소드 둘다 맵핑할 수 있고 @GetMapping은 메소드에만 맵핑할 수 있다. 그러니깐 @GetMapping이 더 세분화되어있다고 볼수있다.
// 두 어노테이션들은 각각의 http메소드들에 맵핑되는데 @GetMapping은 Get으로 @RequestMapping한다는 뜻.

/* ModelAndView 객체는 데이터와 뷰를 동시에 설정이 가능하다. Model객체와 크게 차이가 없다.
*  뷰의 이름을 설정하는 메소드 -> setViewName("뷰의 경로")
*  데이터를 보낼때 사용하는 메소드 -> addObject("변수 이름","데이터 값")
*/
