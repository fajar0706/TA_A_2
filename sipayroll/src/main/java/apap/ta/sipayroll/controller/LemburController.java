package apap.ta.sipayroll.controller;

import apap.ta.sipayroll.model.LemburModel;
import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.service.GajiService;
import apap.ta.sipayroll.service.LemburService;
import apap.ta.sipayroll.service.RoleService;
import apap.ta.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class LemburController {
    @Qualifier("lemburServiceImpl")
    @Autowired
    private LemburService lemburService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private GajiService gajiService;

    @GetMapping("/lembur/add")
    public String addLemburFormPage(Model model) {
        model.addAttribute("lembur", new LemburModel());
        return "form-add-lembur";
    }

    @PostMapping("/lembur/add")
    public String addLemburSubmit(@ModelAttribute LemburModel lembur, Model model) {
        UserModel user = userService.getUserModelByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        GajiModel gaji = gajiService.getGajiModelByUser(user);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String start = dateFormat.format(lembur.getWaktuMulai());
        String finish = dateFormat.format(lembur.getWaktuSelesai());
        String notMatch;
        if (!start.equals(finish)){
            notMatch = "Waktu lembur harus dalam tanggal yang sama!";
            model.addAttribute("notMatch", notMatch);
            model.addAttribute("lembur", new LemburModel());
            return "form-add-lembur";
        }else {
            if (gaji!=null){
                lembur.setGaji(gaji);
                lemburService.addLembur(lembur);
                model.addAttribute("id", lembur.getId());
                return "add-lembur";}
            else{
                notMatch = "Belum ada gaji!";
                model.addAttribute("notMatch", notMatch);
                model.addAttribute("lembur", new LemburModel());
                return "form-add-lembur";
            }}
    }

    @GetMapping("/lembur/change/{idLembur}")
    public String changeLemburFormPage(@PathVariable Long idLembur, Model model) {
        LemburModel lembur = lemburService.getLemburByIdLembur(idLembur);
        model.addAttribute("lembur", lembur);
        model.addAttribute("role",roleService);
        return "form-change-lembur";
    }

    @PostMapping("/lembur/change")
    public String changeLemburSubmit(@ModelAttribute LemburModel lembur, Model model) {
        LemburModel lemburUpdated = lemburService.updateLembur(lembur);
        GajiModel gaji = lemburUpdated.getGaji();
        model.addAttribute("lemburUpdated",lemburUpdated);
        model.addAttribute("gaji", gaji);
        return "change-lembur";
    }

    @RequestMapping("/lembur/viewall")
    public String listLembur(Model model) {
        List<LemburModel> listLembur = lemburService.getLemburList();
        model.addAttribute("listLembur", listLembur);
        return "viewall-lembur";
    }
}