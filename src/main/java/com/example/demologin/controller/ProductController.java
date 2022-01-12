package com.example.demologin.controller;

import com.example.demologin.model.dto.PostInfoDto;
import com.example.demologin.model.dto.ProductInfoDto;
import com.example.demologin.model.request.FilterProductReq;
import com.example.demologin.repository.ProductRepository;
import com.example.demologin.service.ProductService;

import com.example.demologin.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String indexPage(Model model){
        // Get 5 updated products
        List<ProductInfoDto> updated = productService.getListNewProducts();
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

    @GetMapping("/updated-products")
    public ResponseEntity<?> getListUpdatedProducts(){
        return ResponseEntity.ok(productService.getListNewProducts());
    }

    @GetMapping("/bestseller-products")
    public ResponseEntity<?> getListBestSellerProducts(){
        return ResponseEntity.ok(productService.getListBestSellerProduct());
    }

    @GetMapping("/obosuggest-products")
    public ResponseEntity<?> getListSuggestProducts(){
        List<ProductInfoDto> products = productService.getListSuggestProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getFilterListProduct(@RequestBody FilterProductReq req) {

        List<ProductInfoDto> products = productService.filterProduct(req);
        return ResponseEntity.ok(products);
    }
}
