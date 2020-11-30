package apap.ta.sipayroll.controller;


import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.service.GajiService;
import apap.ta.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GajiController {

    @Qualifier("gajiServiceImpl")
    @Autowired
    private GajiService gajiService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping("/gaji/add")
    public String addGajiFormPage(Model model){
        List<UserModel> listUser = userService.findAllUser();
        model.addAttribute("gaji", new GajiModel());
        model.addAttribute("listUser",listUser);
        return "form-add-gaji";
    }

    @PostMapping("/gaji/add")
    public String addGajiSubmit(
            @ModelAttribute GajiModel gaji, Model model){
        UserModel user = userService.getUserModelByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        System.out.println(user );
//        String role = user.getRole().getNama();
        gaji.setUser(user);
//        List<GajiModel> listGaji = user.getGajiList();
//        listGaji.add(gaji);
//        user.setGajiList(listGaji);
        gajiService.addGaji(gaji);
        model.addAttribute("user",user);
        model.addAttribute("gaji", gaji);
        return "add-gaji";
    }


}
