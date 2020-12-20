package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.BonusModel;
import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.LemburModel;
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
            targetGaji.setStatusPersetujuan(gaji.getGajiPokok());
            targetGaji.setGajiPokok(gaji.getGajiPokok());
            gajiDb.save(targetGaji);
            return targetGaji;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

   @Override
   public Integer totalPendapatan(GajiModel gaji){

       List<LemburModel> listLembur = gaji.getLemburList();
       List<BonusModel> listBonus = gaji.getBonusList();
       int jumlahTotalPendapatan = 0;

       int gajiPokok = 0;
       int kompensasi = 0;
       int bonus = 0;

       if(gaji.getStatusPersetujuan() == 2){
           gajiPokok += gaji.getGajiPokok();
       }else{
           return gajiPokok = 0;
       }

       for (int i = 0; i < listLembur.size() ; i++) {
           if(listLembur.get(i).getStatusPersetujuan()==2){
               int tempKompensasi = listLembur.get(i).getKompensasiPerJam();
               int jamMulai = listLembur.get(i).getWaktuMulai().getHours();
               int jamAkhir = listLembur.get(i).getWaktuSelesai().getHours();
               int tempJam = jamAkhir - jamMulai;
               kompensasi += tempKompensasi*tempJam;
           }else {
                return kompensasi = 0;
           }
       }
       for (int i = 0; i < listBonus.size(); i++) {
           bonus += listBonus.get(i).getJumlahBonus();
       }
       return gajiPokok+kompensasi+bonus;
   }

    @Override
    public List<Integer> totalPendapatan() {
        List<GajiModel> listgaji = gajiDb.findAll();
        List<Integer> jumlahTotalPendapatan = new ArrayList<Integer>();

        for (int i = 0; i < listgaji.size() ; i++) {
            //Gaji
            int tempGaji=0;
            if(listgaji.get(i).getStatusPersetujuan() == 2){
                tempGaji += listgaji.get(i).getGajiPokok();
            }else if(listgaji.get(i).getStatusPersetujuan() == 1 || listgaji.get(i).getStatusPersetujuan() == 0){
                tempGaji=0;
            }

            //Kompensasi
            int kompensasi = 0;
            if(listgaji.get(i).getStatusPersetujuan() == 2){
                for (int j = 0; j < listgaji.get(i).getLemburList().size(); j++) {
                    int tempKompensasi = listgaji.get(i).getLemburList().get(j).getKompensasiPerJam();
                    int jamMulai = listgaji.get(i).getLemburList().get(j).getWaktuMulai().getHours();
                    int jamAkhir = listgaji.get(i).getLemburList().get(j).getWaktuSelesai().getHours();
                    int tempJam = jamAkhir-jamMulai;
                    kompensasi += tempKompensasi*tempJam;
                }
            }else if(listgaji.get(i).getStatusPersetujuan() == 1 || listgaji.get(i).getStatusPersetujuan() == 0){
                kompensasi = 0;
            }
            //Bonus
            int bonus = 0;
            if(listgaji.get(i).getStatusPersetujuan()==2){
                for (int j = 0; j < listgaji.get(i).getBonusList().size(); j++) {
                    int tempBonus = listgaji.get(i).getBonusList().get(j).getJumlahBonus();
                    bonus += tempBonus;
                }
            }else if (listgaji.get(i).getStatusPersetujuan() == 1 || listgaji.get(i).getStatusPersetujuan() == 0){
                bonus = 0;
            }

            int total = tempGaji + kompensasi +bonus;
            jumlahTotalPendapatan.add(total);
        }
        return jumlahTotalPendapatan;
    }

    @Override
    public GajiModel getGajiModelByUser(UserModel userModel) {
        return gajiDb.findGajiModelByUser(userModel);
    }

    @Override
    public GajiModel changeStatus(GajiModel gaji) {
        GajiModel targetGaji = gajiDb.findById(gaji.getId()).get();
        try {
                targetGaji.setStatusPersetujuan(gaji.getStatusPersetujuan());
                gajiDb.save(targetGaji);
                return targetGaji;
        } catch (NullPointerException nullException) {
            return null;
        }
    }
}
