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

import javax.servlet.http.HttpServletRequest;
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
        List<GajiModel> listGaji = gajiService.getGajiList();
        UserModel userTarget = userService.getUserModelByUsername(gaji.getUser().getUsername());
        gaji.setUser(userTarget);
        gaji.setUserPengaju(user);
        String text = "Gaji Sudah Pernah Di tambahkan";
        for (int i = 0; i < listGaji.size(); i++) {
            if(listGaji.get(i).getUser() == userTarget){
                model.addAttribute("text",text);
                return "add-gaji";

            }
        }
        gajiService.addGaji(gaji);
        text = "Gaji Pegawai "+  userTarget.getUsername() + " berhasil ditambahkan";
        model.addAttribute("text",text);
        model.addAttribute("user",userTarget);
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
        model.addAttribute("user", user);
        return "delete-gaji";
    }
    @GetMapping("/gaji/ubah/{id}")
    public String changeGajiFormPage(
            @PathVariable Integer id,
            Model model){
        GajiModel gaji = gajiService.getGajiById(id);
        model.addAttribute("gaji",gaji);
        return "form-update-gaji";
    }

//    @PostMapping("/gaji/ubah/{id}")
//    public String changeGajiFormSubmit(
//            @PathVariable Integer id,
//            @ModelAttribute GajiModel gaji,
//            Model model){
//        UserModel user = userService.getUserModelByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        UserModel userTarget = userService.getUserModelByUsername(gaji.getUser().getUsername());
//        String text = "Anda tidak dapat merubah gaji anda sendiri";
//        if(userTarget == user){
//            model.addAttribute("text" ,text);
//            return "ubah-data-gaji";
//        }
//        gaji.setUser(userTarget);
//        gaji.setUserPengaju(user);
//        GajiModel newGaji = gajiService.changeGaji(gaji);
//        text = "Gaji " + userTarget.getUsername() + " berhasil di ubah";
//        model.addAttribute("text" ,text);
//        model.addAttribute("gaji", newGaji);
//        return "ubah-data-gaji";
//    }
//    @RequestMapping(value = "/gaji/ubah/{id}", method = RequestMethod.POST, params = {"gajiPokok"})
//    public String ubahKapasitasRuanganSubmit(@PathVariable Integer id, @ModelAttribute GajiModel gaji, Model model, HttpServletRequest request){
//        Integer gajiPokok =  Integer.valueOf(request.getParameter("gajiPokok"));
//        model.addAttribute("gajiPokok", gajiPokok);
//
//        GajiModel gaji = ruanganService.getRuanganByIdRuangan(id).get();
//
//        ruang.setKapasitas(kap);
//        ruanganService.saveRuangan(ruang);
//
//        String message = "Kapasitas ruangan " + ruang.getNama() + " berhasil diubah menjadi " + ruang.getKapasitas() +".";
//        model.addAttribute("message", message);
//        return "sukses-tambah-fasilitas";
//    }
}
