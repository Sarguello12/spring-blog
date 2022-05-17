//package com.codeup.springblog.controllers;
//
//import com.codeup.springblog.models.Coffee;
//import com.codeup.springblog.repositories.CoffeeRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/coffee")
//public class CoffeeController {
//
//    private final CoffeeRepository coffeeDao;
//
//    public CoffeeController(CoffeeRepository coffeeDao){
//        this.coffeeDao = coffeeDao;
//    }
//
//    @GetMapping
//    public String allCoffees(Model model){
//        List<Coffee> coffees = coffeeDao.findAll();
//        model.addAttribute("coffees", coffees);
//        return "coffees/index";
//    }
//
////    @GetMapping
////    public String coffeeInfo(){
////        return "views-lecture/coffee";
////    }
//
//    @PostMapping
//    public String newsLetterSignup(@RequestParam(name="email") String email, Model model){
//        model.addAttribute("email", email);
//        return "views-lecture/coffee";
//    }
//
//    @GetMapping("/{roast}")
//    public String coffeeInfo(@PathVariable String roast, Model model){
////        model.addAttribute("roast", roast);
////        return "views-lecture/coffee";
//
////        Coffee selection = new Coffee();
////        selection.setRoast(roast);
////        if (roast.equals("dark")){
////            selection.setOrigin("Colombia");
////        } else if (roast.equals("medium")){
////            selection.setOrigin("New Guinea");
////        } else {
////            selection.setOrigin("Kenya");
////        }
////        model.addAttribute("selection", selection);
////        return "views-lecture/coffee";
//
//        Coffee selection = new Coffee(roast, "Cool Beans");
//        Coffee selection2 = new Coffee(roast, "Jolting Joe");
//        if (roast.equals("dark")){
//            selection.setOrigin("Colombia");
//            selection2.setOrigin("Brazil");
//        } else if (roast.equals("medium")){
//            selection.setOrigin("New Guinea");
//            selection2.setOrigin("Sumatra");
//        } else {
//            selection.setOrigin("Kenya");
//            selection2.setOrigin("Ethiopia");
//        }
//        List<Coffee> selections = new ArrayList<>();
//        selections.add(selection);
//        selections.add(selection2);
//        model.addAttribute("roast", roast);
//        model.addAttribute("selections", selections);
//        return "views-lecture/coffee";
//    }
//
//    @GetMapping("/create")
//    public String addCoffeeForm(){
//        return "coffees/create";
//    }
//
//    @PostMapping("/create")
//    public String addCoffee(@RequestParam(name = "roast") String roast, @RequestParam(name = "origin") String origin, @RequestParam(name = "brand") String brand){
//        Coffee coffee = new Coffee(roast, origin, brand);
//        coffeeDao.save(coffee);
//        return "coffees/create";
//    }
//}
