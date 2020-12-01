package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.LemburModel;
import apap.ta.sipayroll.repository.LemburDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}