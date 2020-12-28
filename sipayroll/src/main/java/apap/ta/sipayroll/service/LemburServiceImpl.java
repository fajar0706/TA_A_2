package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.LemburModel;
import apap.ta.sipayroll.repository.LemburDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class LemburServiceImpl implements LemburService {
    @Autowired
    private LemburDb lemburDb;

    @Override
    public void addLembur(LemburModel lembur) {
        lemburDb.save(lembur);
    }
    
    @Override
    public LemburModel  getLemburByIdLembur(Long idLembur){
        return lemburDb.findById(idLembur).get();
    }
    
    @Override
    public LemburModel updateLembur(LemburModel lembur){
        LemburModel targetLembur = lemburDb.findById(lembur.getId()).get();

        try{
            targetLembur.setStatusPersetujuan(lembur.getStatusPersetujuan());
            targetLembur.setWaktuMulai(lembur.getWaktuMulai());
            targetLembur.setWaktuSelesai(lembur.getWaktuSelesai());
            lemburDb.save(targetLembur);
            return targetLembur;
        } catch(NullPointerException nullException){
            return null;

        }
    }

    @Override
    public List<LemburModel> getLemburList() {
        return lemburDb.findAll();
    }

    @Override
    public void deleteLembur(LemburModel lembur) {
        lemburDb.delete(lembur);
    }

    @Override
    public List<LemburModel> findAllLembur() {
        return lemburDb.findAll();
    }

    @Override
    public Integer totalLembur(GajiModel gaji){
        List<LemburModel> daftarLembur= lemburDb.findByGaji(gaji);
        Integer total = 0;
        for(LemburModel lembur : daftarLembur){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lembur.getWaktuMulai());
            int hoursMulai = calendar.get(Calendar.HOUR_OF_DAY);
            calendar.setTime(lembur.getWaktuSelesai());
            int hoursSelesai = calendar.get(Calendar.HOUR_OF_DAY);
            int selisih = hoursSelesai-hoursMulai;

            total+= lembur.getKompensasiPerJam()*selisih;
        }
        return total;
    }

    @Override
    public List<LemburModel> getListLemburByGaji(GajiModel gajiModel) {
        return lemburDb.findByGaji(gajiModel);
    }

}