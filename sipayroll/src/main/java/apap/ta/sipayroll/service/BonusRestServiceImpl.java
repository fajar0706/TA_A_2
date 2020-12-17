package apap.ta.sipayroll.service;


import apap.ta.sipayroll.model.*;
import apap.ta.sipayroll.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class BonusRestServiceImpl implements BonusRestService{

    @Autowired
    private UserDb userDb;

    @Autowired
    private RoleDb roleDb;

    @Autowired
    private GajiDb gajiDb;

    @Autowired
    private JenisBonusDb jenisBonusDb;

    @Autowired
    private BonusDb bonusDb;


    //    @Override
//    public String encrypt(String password) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(password);
//        return hashedPassword;
//    }

    @Override
    public void addUser(String username, String jumlahPelatihan) {
        UserModel newUser = new UserModel();
        newUser.setUsername(username);
        newUser.setPassword("karyawan123");
        RoleModel newRole = roleDb.findById(Long.valueOf(6)).get();
        newUser.setRole(newRole);
        userDb.save(newUser);

        LocalDate localDate = LocalDate.now();
        GajiModel newGaji = new GajiModel();
        newGaji.setGajiPokok(0);
        newGaji.setStatusPersetujuan(0);
        newGaji.setTanggalMasuk(localDate);
        newGaji.setUser(newUser);
        newGaji.setUserPengaju(newUser);
        gajiDb.save(newGaji);


        BonusModel newBonus = new BonusModel();
        Integer jmlPelatihan = Integer.parseInt(jumlahPelatihan);
        JenisBonusModel newJenisBonus = jenisBonusDb.findById(3).get();
        newBonus.setJenisBonus(newJenisBonus);
        newBonus.setGaji(newGaji);
        newBonus.setTanggalDiberikan(localDate);
        newBonus.setJumlahBonus(jmlPelatihan*150000);
        bonusDb.save(newBonus);
    }

    @Override
    public void sudahAdaUsername(String username, String jumlahPelatihan) {
        UserModel userTarget = userDb.findByUsername(username);
        GajiModel gajiTarget = gajiDb.findGajiModelByUser(userTarget);
        BonusModel bonusTarget = bonusDb.findBonusModelByGaji(gajiTarget);

        LocalDate localDate = LocalDate.now();
        Integer jmlPelatihan = Integer.parseInt(jumlahPelatihan);
        JenisBonusModel newJenisBonus = jenisBonusDb.findById(3).get();
        bonusTarget.setJenisBonus(newJenisBonus);
        bonusTarget.setGaji(gajiTarget);
        bonusTarget.setTanggalDiberikan(localDate);
        bonusTarget.setJumlahBonus(bonusTarget.getJumlahBonus()+(jmlPelatihan*150000));
        bonusDb.save(bonusTarget);
    }

    @Override
    public BonusModel bonusPelatihan(String username) {
        UserModel userTarget = userDb.findByUsername(username);
        GajiModel gajiTarget = gajiDb.findGajiModelByUser(userTarget);
        BonusModel bonusTarget = bonusDb.findBonusModelByGaji(gajiTarget);
        return bonusTarget;
    }
}
