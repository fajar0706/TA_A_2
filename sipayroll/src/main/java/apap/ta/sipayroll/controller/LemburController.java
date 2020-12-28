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
                notMatch = "User ini belum ada gaji!";
                model.addAttribute("notMatch", notMatch);
                model.addAttribute("lembur", new LemburModel());
                return "form-add-lembur";
            }}
    }

    @GetMapping("/lembur/change/{idLembur}")
    public String changeLemburFormPage(@PathVariable Long idLembur, Model model) {
        LemburModel lembur = lemburService.getLemburByIdLembur(idLembur);
        boolean checkDisetujuiDitolak;
        String notChange;
        if(lembur.getStatusPersetujuan()==2){
            checkDisetujuiDitolak = true;
            notChange = "Status Persetujuan Sudah Disetujui Tidak Dapat Diubah";
            model.addAttribute("notChange",notChange);
            model.addAttribute("checkDisetujuiDitolak", checkDisetujuiDitolak);
            model.addAttribute("lembur", lembur);
            model.addAttribute("role",roleService);
            return "form-change-lembur";
        }
        else if(lembur.getStatusPersetujuan()==1){
            checkDisetujuiDitolak = true;
            notChange = "Status Persetujuan Sudah Ditolak Tidak Dapat Diubah";
            model.addAttribute("notChange",notChange);
            model.addAttribute("checkDisetujuiDitolak", checkDisetujuiDitolak);
            model.addAttribute("lembur", lembur);
            model.addAttribute("role",roleService);
            return "form-change-lembur";
        }
        
        else{
            model.addAttribute("lembur", lembur);
            model.addAttribute("role",roleService);
            return "form-change-lembur";
        }
        
    }

    @PostMapping("/lembur/change")
    public String changeLemburSubmit(@ModelAttribute LemburModel lembur, Model model) {
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String start = dateFormat.format(lembur.getWaktuMulai());
        String finish = dateFormat.format(lembur.getWaktuSelesai());
        String notMatch;
            if (!start.equals(finish)){
                notMatch = "Waktu lembur harus dalam tanggal yang sama!";
                model.addAttribute("notMatch", notMatch);
                model.addAttribute("lembur", new LemburModel());
                model.addAttribute("role",roleService);
                return "form-change-lembur";
            }else{
                LemburModel lemburUpdated = lemburService.updateLembur(lembur);
                GajiModel gaji = lemburUpdated.getGaji();
                model.addAttribute("lemburUpdated",lemburUpdated);
                model.addAttribute("gaji", gaji);
            return "change-lembur";
            }
        
    }

    @RequestMapping("/lembur/viewall")
    public String listLembur(Model model) {
        List<LemburModel> listLembur = lemburService.getLemburList();
        UserModel user = userService.getUserModelByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        GajiModel gajiModel = gajiService.getGajiModelByUser(user);
        List<LemburModel> lemburModel = lemburService.getListLemburByGaji(gajiModel);
        String role = user.getRole().getNama();
        String userName = user.getUsername();

        Boolean cek = true;
        for (int i = 0; i < listLembur.size() ; i++) {
            if(role.equals("Karyawan") && (listLembur.get(i).getGaji().getUser().getUsername().equals(userName)) && lemburModel.size() != 0){
                cek = true;
                model.addAttribute("check",cek);
                model.addAttribute("listLembur", lemburModel);
                model.addAttribute("role",roleService);
                return "viewall-lembur";
            }else if(role.equals("Karyawan") && lemburModel.size() == 0){
                cek = false;
                String text = "User ini belum memiliki lembur";
                model.addAttribute("check",cek);
                model.addAttribute("text",text);
                model.addAttribute("listLembur", lemburModel);
                model.addAttribute("role",roleService);

                return "viewall-lembur";
            }
        }
        cek = true;
        model.addAttribute("check",cek);
        model.addAttribute("listLembur", listLembur);
        model.addAttribute("role",roleService);
        return "viewall-lembur";
    }

    @RequestMapping(value={"/lembur/delete/", "/lembur/delete/id/{idLembur}"})
    public String delete(
            @PathVariable(value="idLembur", required = false) Long idLembur, Model model) {
        LemburModel lembur = lemburService.getLemburByIdLembur(idLembur);
        lemburService.deleteLembur(lembur);
        // UserModel user = userService.getUserModelByUsername(lembur.getUser().getUsername());
        // model.addAttribute("user", user);
        return "delete-lembur";
    }
}