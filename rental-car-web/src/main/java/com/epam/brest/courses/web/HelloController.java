package com.epam.brest.courses.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/home")
    public String home(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
                       Model model) {

        LOGGER.debug("home(name:{})", name);
        model.addAttribute("name", name);
        return "hello";
    }

}
