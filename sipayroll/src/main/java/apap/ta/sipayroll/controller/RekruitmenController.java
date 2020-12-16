package apap.ta.sipayroll.controller;


import apap.ta.sipayroll.service.RekruitmenRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RekruitmenController{
    @Autowired
    private RekruitmenRestService rekruitmenRestService;

    @GetMapping("/addLowongan")
    private String addLowongan(){
        return "form-nambah-rekruitmen";
    }
    @RequestMapping(value="/add-rekruitmen", method = RequestMethod.POST)
    private String addLowonganSubmit(String divisi, String posisi, String jumlah_karyawan, String jenis){
        MultiValueMap<String,String> data = new LinkedMultiValueMap<>();
        data.add("divisi", divisi);
        data.add("posisi", posisi);
        data.add("jumlah", jumlah_karyawan);
        data.add("jenis", jenis);
        if(divisi != null && posisi != null && jumlah_karyawan != null) {
            rekruitmenRestService.postRekruitmen(data);
        }
        return "redirect:/";
    }


}
