package com.scm.controller;

import com.scm.entity.Product;
import com.scm.service.Impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showMarketPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getAllProducts());
        return "market";
    }

    @PostMapping("/upload")
    public String uploadProduct(@ModelAttribute Product product,
                                @RequestParam("image") MultipartFile file,
                                Model model) {
        try {
            productService.saveProduct(product, file);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("uploadError", "Failed to upload image");
            return "market";
        }
        return "redirect:/market";
    }
}
