package apap.ta.sipayroll.controller;


import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.service.GajiService;
import apap.ta.sipayroll.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        List<UserModel> userTarget = userService.findAllUser();
        gaji.setUser(user);
        List<GajiModel> listGaji = gajiService.getGajiList();
        String text = "Gaji Sudah Pernah Di tambahkan";
        for (int i = 0; i < userTarget.size(); i++) {
            for (int j = 0; j < listGaji.size(); j++) {
                if(listGaji.get(j).getUser() == userTarget.get(i).getGaji().getUser()){
                    model.addAttribute("text",text);
                    return "add-gaji";

                }
            }
        }

        gajiService.addGaji(gaji);
        text = "Gaji Pegawai "+  user.getUsername() + " berhasil ditambahkan";
        model.addAttribute("text",text);
        model.addAttribute("user",user);
        model.addAttribute("gaji", gaji);
        return "add-gaji";
    }

    @RequestMapping("/gaji/viewall")
    public String listGaji(Model model){
        List<GajiModel> listGaji = gajiService.getGajiList();
        model.addAttribute( "listGaji",listGaji);
        return "viewall-gaji";
    }

    @GetMapping("/gaji/{id}")
    public String viewGaji(
            @PathVariable(value = "id") Integer id, Model model){
        GajiModel gaji = gajiService.getGajiById(id);
        model.addAttribute("gaji", gaji);
        return "view-gaji";
    }

    @RequestMapping(value={"/gaji/delete/", "/gaji/delete/id/{id}"})
    public String delete(
            @PathVariable(value="id", required = false) Integer id, Model model) {
        GajiModel gaji = gajiService.getGajiById(id);
        gajiService.deleteGaji(gaji);
        UserModel user = userService.getUserModelByUsername(gaji.getUser().getUsername());

        List<GajiModel> listGaji = gajiService.getGajiList();
        for (int i = 0; i < listGaji.size(); i++) {
            if(listGaji.get(i).getUser() == user){
            }
        }
        model.addAttribute("user", user);
        return "delete-gaji";
    }


}
