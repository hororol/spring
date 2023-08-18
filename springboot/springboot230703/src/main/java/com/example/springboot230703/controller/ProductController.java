package com.example.springboot230703.controller;

import com.example.springboot230703.dao.ProductsReplyRepository;
import com.example.springboot230703.dao.ProductsRepository;
import com.example.springboot230703.dto.Product;
import com.example.springboot230703.dto.Product_reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ProductsReplyRepository productsReplyRepository;

    @PostMapping("insert")
    public String insert(Product product){
        System.out.println(product);
        productsRepository.doInsert(product);
        return "redirect:/";
    }

    @GetMapping("view")
    public String view(Model model, Product product){
        Product dbProduct = productsRepository.doSelectRow(product);
        Product_reply product_reply = Product_reply.builder()
                .idx_products(product.getIdx())
                .build();
        List<Product_reply> product_reply_list = productsReplyRepository.doselect(product_reply);
        model.addAttribute("product", dbProduct);
        model.addAttribute("product_reply_list", product_reply_list);
        return "product/view";
    }
}
