package com.example.demologin.controller;

import com.example.demologin.entity.Product;
import com.example.demologin.exception.NotFoundException;
import com.example.demologin.model.dto.DetailProductInfoDto;
import com.example.demologin.model.dto.PostInfoDto;
import com.example.demologin.model.dto.ProductInfoDto;
import com.example.demologin.model.request.FilterProductReq;
import com.example.demologin.repository.ProductRepository;
import com.example.demologin.service.ProductService;

import com.example.demologin.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ProductController {

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
        List<ProductInfoDto> updatedProduct = productService.getListBestSellerProduct();
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/bestseller-products")
    public ResponseEntity<?> getListBestSellerProducts(){
        List<ProductInfoDto> bestsellerProducts = productService.getListBestSellerProduct();
        return ResponseEntity.ok(bestsellerProducts);
    }

    @GetMapping("/obosuggest-products")
    public ResponseEntity<?> getListSuggestProducts(){
        List<ProductInfoDto> suggestProduct = productService.getListSuggestProduct();
        return ResponseEntity.ok(suggestProduct);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getFilterListProduct(@RequestBody FilterProductReq req) {
        List<ProductInfoDto> filterProduct = productService.filterProduct(req);
        return ResponseEntity.ok(filterProduct);
    }

    @GetMapping("/product/{slug}/{id}")
    public ResponseEntity<?> getProductDetail(@RequestParam String id, Model model) {
        DetailProductInfoDto dto = productService.getProductDetailByID(id);
        if(dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            throw new NotFoundException("Invalid product id.");
        }
    }



//    @GetMapping("/product/search")
//    public ResponseEntity<?> search(@RequestParam(name = "") String category,@RequestParam(name = "") String size , @RequestParam(name ="") String brandName) {
//        List<Product> dto = productService.searchByCategory(category);
//        if(dto != null) {
//            return ResponseEntity.ok(dto);
//        } else {
//            throw new NotFoundException("Invalid product id.");
//        }
//    }


}
