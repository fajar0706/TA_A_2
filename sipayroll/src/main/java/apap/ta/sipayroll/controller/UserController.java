package apap.ta.sipayroll.controller;

import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.rest.UserDetail;
import apap.ta.sipayroll.service.RoleService;
import apap.ta.sipayroll.service.UserRestService;
import apap.ta.sipayroll.service.UserService;
import apap.ta.sipayroll.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRestService userRestService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUserFormPage(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "signup";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserSubmit(
            @ModelAttribute UserModel user,
            @RequestParam(value = "nama") String nama,
            @RequestParam(value = "noTelepon") String noTelepon,
            @RequestParam(value = "tempatLahir") String tempatLahir,
            @RequestParam(value = "tanggalLahir") String tanggalLahir,
            @RequestParam(value = "alamat") String alamat,
            Model model) {

        UserModel userExisted = userService.getUserModelByUsername(user.getUsername());
        if(userExisted == null){
            userService.addUser(user);
            UserDetail pegawai = new UserDetail();
            pegawai.setNama(nama);
            pegawai.setUsername(user.getUsername());
            pegawai.setNoTelepon(noTelepon);
            pegawai.setTempatLahir(tempatLahir);
            try{
                Date dateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(tanggalLahir);
                pegawai.setTanggalLahir(dateParsed);
            }catch (Exception e) {}
            pegawai.setAlamat(alamat);
            pegawai.setRoleId(user.getRole().getId());
            userRestService.postUser(user.getUsername(), nama, noTelepon, tempatLahir, tanggalLahir, alamat, Integer.parseInt(user.getRole().getId().toString()));
            model.addAttribute("username", user.getUsername());
            model.addAttribute("user", user);

            return "success-signup";
        }
        else{
            model.addAttribute("alert", "username");
        }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("user", user);
        return "redirect:/user/addUser";
    }

    @GetMapping("/info")
    private String getUserInfoPage(Model model){
        UserModel user = userService.getUserModelByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        UserDetail pegawai = userRestService.getUserInfo(user.getUsername());
        model.addAttribute("pegawai", pegawai);
        model.addAttribute("user", user);
        return "view-profile";
    }
}
