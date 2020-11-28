package apap.ta.sipayroll.controller;

import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.service.RoleService;
import apap.ta.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUserFormPage(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "signup";
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserSubmit(
            @ModelAttribute UserModel user, Model model) {
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }
}
