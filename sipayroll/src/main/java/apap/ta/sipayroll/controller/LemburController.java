package apap.ta.sipayroll.controller;

import apap.ta.sipayroll.model.LemburModel;
import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.service.LemburService;
import apap.ta.sipayroll.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LemburController {
    @Qualifier("lemburServiceImpl")
    @Autowired
    private LemburService lemburService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/lembur/add")
    public String addLemburFormPage(Model model) {
        model.addAttribute("lembur", new LemburModel());
        return "form-add-lembur";
    }

    @PostMapping("/lembur/add")
    public String addLemburSubmit(@ModelAttribute LemburModel lembur, Model model) {
        lemburService.addLembur(lembur);
        model.addAttribute("id", lembur.getId());
        return "add-lembur";
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
}