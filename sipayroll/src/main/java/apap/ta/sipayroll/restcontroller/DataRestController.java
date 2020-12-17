package apap.ta.sipayroll.restcontroller;

import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.rest.BaseResponse;
import apap.ta.sipayroll.service.DataRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DataRestController {
    @Autowired
    private DataRestService dataRestService;

    @GetMapping(value = "/data-karyawan-lama")
    private List<Map<String,Object>> retrieveListKaryawanLama(){
        return dataRestService.findDataKaryawanLama();
    }
    
}
