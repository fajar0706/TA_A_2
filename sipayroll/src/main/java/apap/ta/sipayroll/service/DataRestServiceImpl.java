package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.repository.GajiDb;
import apap.ta.sipayroll.repository.RoleDb;
import apap.ta.sipayroll.repository.UserDb;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DataRestServiceImpl implements DataRestService {
    @Autowired
    private UserDb userDb;

    @Autowired
    private GajiDb gajiDb;

    @Override
    public List<Map<String,Object>> findDataKaryawanLama() {

        List<Map<String,Object>> listKaryawanLama = new ArrayList<>();
        List<GajiModel> allUser = gajiDb.findAll();

        for (int i = 0; i < allUser.size(); i++) {
            LocalDate tanggalMasuk = allUser.get(i).getTanggalMasuk();
            String [] tanggalString = String.valueOf(tanggalMasuk).split("-");
            Integer tglMasukInt = Integer.parseInt(tanggalString[0]);

            LocalDate time = LocalDate.now();
            String [] timeNow = String.valueOf(time).split("-");
            Integer timeNowInt = Integer.parseInt(timeNow[0]);
            String lamaKaryawanKerja = String.valueOf(timeNowInt-tglMasukInt);

            if(Integer.parseInt(lamaKaryawanKerja) >= 2){
                Map<String,Object> data = new HashMap<>();
                GajiModel targetUser = allUser.get(i);
                data.put("tahun", lamaKaryawanKerja);
                data.put("gaji",String.valueOf(targetUser.getGajiPokok()));
                data.put("username",targetUser.getUser().getUsername());
                listKaryawanLama.add(data);
            }
        }
        return listKaryawanLama;
    }
}
