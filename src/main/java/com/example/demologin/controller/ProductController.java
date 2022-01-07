package com.example.demologin.controller;

import com.example.demologin.model.dto.PostInfoDto;
import com.example.demologin.model.dto.ProductInfoDto;
import com.example.demologin.repository.ProductRepository;
import com.example.demologin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String indexPage(Model model){
        // Get 5 updated products
        List<ProductInfoDto> updated = productService.getListNewProduct();
        model.addAttribute("updated", updated);

        // Get 5 bestseller products
        List<ProductInfoDto> bestseller = productService.getListBestSellerProduct();
        model.addAttribute("bestseller", bestseller);

        // Get 5 suggest products
        List<ProductInfoDto> suggest = productService.getListSuggestProduct();
        model.addAttribute("suggest", suggest);

        //Newest Post
        List<PostInfoDto> posts;
        model.addAttribute("suggest", suggest);
        return "index";
    }
 }
