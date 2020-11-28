package apap.ta.sipayroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String home(Model model)
    {
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}