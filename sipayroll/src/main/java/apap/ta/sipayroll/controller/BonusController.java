package apap.ta.sipayroll.controller;

import apap.ta.sipayroll.model.BonusModel;
import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.JenisBonusModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.service.BonusService;
import apap.ta.sipayroll.service.GajiService;
import apap.ta.sipayroll.service.JenisBonusService;
import apap.ta.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BonusController {

    @Qualifier("bonusServiceImpl")
    @Autowired
    private BonusService bonusService;

    @Qualifier("jenisBonusServiceImpl")
    @Autowired
    private JenisBonusService jenisBonusService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("gajiServiceImpl")
    @Autowired
    private GajiService gajiService;

    @GetMapping("/bonus/add")
    public String addBonusFormPage(Model model){
        List<JenisBonusModel> listJenisBonus = jenisBonusService.findAllJenisBonus();
        List<GajiModel> gajiList = gajiService.findAllGaji();
        model.addAttribute("bonus", new BonusModel());
        model.addAttribute("gajiList",gajiList);
        model.addAttribute("listJenisBonus",listJenisBonus);
        return "form-add-bonus";
    }
    @PostMapping("/bonus/add")
    public String addBonusSubmit(
            @ModelAttribute BonusModel bonus,
            Model model){
        Integer gajiPokok = bonus.getGaji().getGajiPokok();
        if(bonus.getJenisBonus().getId() == 1){
            bonus.setJumlahBonus(1*gajiPokok);
        }else if(bonus.getJenisBonus().getId() == 2){
            bonus.setJumlahBonus(2*gajiPokok);
        }
        bonusService.addBonus(bonus);
        model.addAttribute("text","Bonus Berhasil ditambahkan sebesar " + bonus.getJumlahBonus());
        model.addAttribute("bonus",bonus);
        return "tambah-bonus";
    }
}
