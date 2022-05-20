package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {
    @RequestMapping(path = "/add100To/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOneHundred(@PathVariable int number){
        return number + " + 100 = " + (number + 100);
    }

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public int add(@PathVariable int num1, @PathVariable int num2){
        return num1 + num2;
    }

    @GetMapping("subtract/{num1}/and/{num2}")
    @ResponseBody
    public int subtract(@PathVariable int num1, @PathVariable int num2){
        return num1 - num2;
    }

    @GetMapping("multiply/{num1}/and/{num2}")
    @ResponseBody
    public double multiply(@PathVariable double num1, @PathVariable double num2){
        return num1 * num2;
    }

    @GetMapping("divide/{num1}/and/{num2}")
    @ResponseBody
    public double divide(@PathVariable double num1, @PathVariable double num2){
        return num1 / num2;
    }


}
