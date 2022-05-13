package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    @GetMapping
    public String coffeeInfo(){
        return "views-lecture/coffee";
    }

    @PostMapping
    public String newsLetterSignup(@RequestParam(name="email") String email, Model model){
        model.addAttribute("email", email);
        return "views-lecture/coffee";
    }

    @GetMapping("/{roast}")
    public String coffeeInfo(@PathVariable String roast, Model model){
//        model.addAttribute("roast", roast);
//        return "views-lecture/coffee";

//        Coffee selection = new Coffee();
//        selection.setRoast(roast);
//        if (roast.equals("dark")){
//            selection.setOrigin("Colombia");
//        } else if (roast.equals("medium")){
//            selection.setOrigin("New Guinea");
//        } else {
//            selection.setOrigin("Kenya");
//        }
//        model.addAttribute("selection", selection);
//        return "views-lecture/coffee";

        Coffee selection = new Coffee(roast, "Cool Beans");
        Coffee selection2 = new Coffee(roast, "Jolting Joe");
        if (roast.equals("dark")){
            selection.setOrigin("Colombia");
            selection2.setOrigin("Brazil");
        } else if (roast.equals("medium")){
            selection.setOrigin("New Guinea");
            selection2.setOrigin("Sumatra");
        } else {
            selection.setOrigin("Kenya");
            selection2.setOrigin("Ethiopia");
        }
        List<Coffee> selections = new ArrayList<>();
        selections.add(selection);
        selections.add(selection2);
        model.addAttribute("roast", roast);
        model.addAttribute("selections", selections);
        return "views-lecture/coffee";
    }
}
