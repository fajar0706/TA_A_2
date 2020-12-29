package apap.ta.sipayroll.controller;

import apap.ta.sipayroll.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
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