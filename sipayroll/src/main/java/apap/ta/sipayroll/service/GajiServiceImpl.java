package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.repository.GajiDb;
import apap.ta.sipayroll.repository.LemburDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GajiServiceImpl implements GajiService  {

    @Autowired
    private GajiDb gajiDb;

    @Autowired
    private LemburDb lemburDb;

    @Override
    public void addGaji(GajiModel gaji) {
        gaji.setStatusPersetujuan(0);
        gajiDb.save(gaji);
    }

    @Override
    public List<GajiModel> findAllGaji() {
        return gajiDb.findAll();
    }

    @Override
    public List<GajiModel> getGajiList() {
        return gajiDb.findAll();
    }

    @Override
    public GajiModel getGajiById(Integer id) {
        return gajiDb.findById(id).get();
    }

    @Override
    public void deleteGaji(GajiModel gaji) {
        gajiDb.delete(gaji);
    }

    @Override
    public GajiModel changeGaji(GajiModel gaji) {
        GajiModel targetGaji = gajiDb.findById(gaji.getId()).get();
        try {
            targetGaji.setStatusPersetujuan(0);
            targetGaji.setGajiPokok(gaji.getGajiPokok());
            gajiDb.save(targetGaji);
            return targetGaji;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

   @Override
   public List<Integer> totalPendapatan(){
       List<GajiModel> listgaji = gajiDb.findAll();
       List<Integer> jumlahTotalPendapatan = new ArrayList<Integer>();

       for(GajiModel x: listgaji){
           Integer tempGaji = x.getGajiPokok();
           Integer tempKompensasi = x.getKompensasi().getKompensasiPerJam();

           Integer jamMulai = x.getKompensasi().getWaktuMulai().getHours();
           Integer jamAkhir = x.getKompensasi().getWaktuSelesai().getHours();
           Integer tempJam = jamAkhir-jamMulai;

           Integer kompensasi = tempKompensasi*tempJam;
           Integer total = tempGaji + kompensasi;
           jumlahTotalPendapatan.add(total);
       }
       return jumlahTotalPendapatan;
   }

    @Override
    public GajiModel getGajiModelByUser(UserModel userModel) {
        return gajiDb.findGajiModelByUser(userModel);
    }
}
