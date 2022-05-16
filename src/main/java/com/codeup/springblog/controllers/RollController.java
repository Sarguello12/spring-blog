package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/roll")
public class RollController {

    @GetMapping
    public String coffeeInfo(){
        return "roll-dice";
    }

    @GetMapping("/{num}")
    public String rollDice(@PathVariable int num, Model model){
        Random random = new Random();
        int randomNum = random.nextInt(7);

        model.addAttribute("num", num);
        model.addAttribute("randomNum", randomNum);
        return "roll-dice";

    }
}
