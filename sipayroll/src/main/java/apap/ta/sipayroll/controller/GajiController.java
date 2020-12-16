package apap.ta.sipayroll.controller;


import apap.ta.sipayroll.model.BonusModel;
import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.LemburModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.service.GajiService;
import apap.ta.sipayroll.service.LemburService;
import apap.ta.sipayroll.service.UserService;
import apap.ta.sipayroll.service.RoleService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GajiController {

    @Qualifier("gajiServiceImpl")
    @Autowired
    private GajiService gajiService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LemburService lemburService;

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
        List<GajiModel> gajiModelList = new ArrayList<>();
        List<Integer> totalPendapatanList = new ArrayList<>();
//        HashMap<GajiModel,Integer> test = new HashMap<GajiModel,Integer>();
//        for (int i = 0; i < listGaji.size() ; i++) {
//            GajiModel gaji = gajiService.getGajiById(i);
//            Integer totalPendapatan = gajiService.totalPendapatan(gaji);
//            test.put(gaji,totalPendapatan);
//            gajiModelList.add(gaji);
//            totalPendapatanList.add(totalPendapatan);
//        }
        List<Integer> listTotalPendapatan = gajiService.totalPendapatan();
        model.addAttribute("listTotalPendapatan", listTotalPendapatan);
        model.addAttribute( "listGaji",listGaji);
//        model.addAttribute( "test",test);
		model.addAttribute("role",roleService);
        return "viewall-gaji";
    }

    @GetMapping("/gaji/{id}")
    public String viewGaji(
            @PathVariable(value = "id") Integer id, Model model){
        GajiModel gaji = gajiService.getGajiById(id);
        Integer totalPendapatan = gajiService.totalPendapatan(gaji);
        model.addAttribute("gaji", gaji);
        model.addAttribute("totalPendapatan", totalPendapatan);
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
    @RequestMapping(value = "/gaji/ubah/{id}", method = RequestMethod.POST, params = {"gajiPokok"})
    public String ubahGajiPokokSubmit(
            @PathVariable Integer id,
            @ModelAttribute GajiModel gaji, Model model){
        GajiModel gajiPok = gajiService.getGajiById(id);
        UserModel userAktif = userService.getUserModelByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        UserModel userModel = gajiPok.getUser();

        String text = "Anda tidak dapat merubah gaji anda sendiri";
        if(userAktif == userModel){
            model.addAttribute("text" ,text);
            return "ubah-data-gaji";
        }
        gaji.setUserPengaju(userAktif);
        gajiPok.setUserPenyetuju(null);
        gaji.setUser(userModel);
        gajiService.changeGaji(gaji);
        text = "Gaji Pokok " + gajiPok.getUser().getUsername() + " berhasil diubah";
        model.addAttribute("text", text);
        return "ubah-data-gaji";
    }
    @GetMapping("/gaji/change/status/{idGaji}")
    public String changeStatusFormPage(@PathVariable Integer idGaji, Model model) {
        GajiModel gaji = gajiService.getGajiById(idGaji);
        model.addAttribute("gaji", gaji);
        return "form-change-status-gaji";
        
    }

    @PostMapping("/gaji/change/status/{id}")
    public String changeStatusGajiSubmit(
            @PathVariable Integer id,
            @ModelAttribute GajiModel gaji, Model model) {
        GajiModel gajiPok = gajiService.getGajiById(id);
        UserModel userAktif = userService.getUserModelByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        gajiPok.setUserPenyetuju(userAktif);
        gajiService.changeStatus(gaji);
        model.addAttribute("gaji", gaji);
        String alert = "Status Gaji Pokok " + gaji.getUser().getUsername() + " berhasil diubah";
        model.addAttribute("alert", alert);
        return "change-status-gaji";
    }

}
