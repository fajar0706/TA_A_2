package apap.ta.sipayroll.controller;


import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.service.GajiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GajiController {

    @Qualifier("gajiServiceImpl")
    @Autowired
    private GajiService gajiService;

    @GetMapping("/gaji/add")
    public String addGajiFormPage(Model model){
        model.addAttribute("gaji", new GajiModel());
        return "form-add-gaji";
    }

    @PostMapping("/gaji/add")
    public String addHotelSubmit(
            @ModelAttribute GajiModel gaji, Model model){
        gajiService.addGaji(gaji);
        model.addAttribute("idGaji", gaji.getId());
        return "add-gaji";
    }


}
