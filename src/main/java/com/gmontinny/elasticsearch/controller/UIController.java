package com.gmontinny.elasticsearch.controller;

import com.gmontinny.elasticsearch.models.Product;
import com.gmontinny.elasticsearch.services.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@Slf4j
public class UIController {
    private  SearchService searchService;

    @Autowired
    public UIController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String home(Model model) {
        List<Product> products = searchService.fetchProductNamesContaining("Hornby");

        List<String> names = products.stream().flatMap(prod->{
            return Stream.of(prod.getName());
        }).collect(Collectors.toList());
        log.info("product names {}", names);
        model.addAttribute("names", names);
        return "search";
    }
}
