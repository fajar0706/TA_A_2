package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.BonusModel;
import apap.ta.sipayroll.model.UserModel;

public interface BonusRestService {
    void addUser(String username,String jumlahPelatihan);

    void sudahAdaUsername(String username, String jumlahPelatihan);
    //    String encrypt(String password);
    BonusModel bonusPelatihan(String username);
}
