package apap.ta.sipayroll.restcontroller;

import apap.ta.sipayroll.model.BonusModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.rest.BaseResponse;
import apap.ta.sipayroll.rest.BonusDetail;
import apap.ta.sipayroll.service.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BonusRestController {

    @Autowired
    private GajiService gajiService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BonusService bonusService;

    @Autowired
    private BonusRestService bonusRestService;

    @PostMapping(value = "/bonus")
    private BaseResponse<BonusModel> addBonus(@Valid @RequestBody BonusDetail userPelatihan,
                                              BindingResult bindingResult){
        BaseResponse<BonusModel> response = new BaseResponse<>();
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }else{
            BonusModel bonus = new BonusModel();
            String username  = userPelatihan.getUsername();
            String jumlahPelatihan = userPelatihan.getJumlahPelatihan();
            if(userService.getUserModelByUsername(username) == null){
                response.setStatus(405);
                response.setMessage("failed, tidak ada data karyawan");
                response.setResult(bonus);
            }else {
                bonusRestService.sudahAdaUsername(username,jumlahPelatihan);
                bonus = bonusRestService.bonusPelatihan(username);
                response.setStatus(200);
                response.setMessage("success");
                response.setResult(bonus);
            }
            return response;
        }
    }
}
