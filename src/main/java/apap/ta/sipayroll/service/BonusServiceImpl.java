package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.BonusModel;
import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.repository.BonusDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class BonusServiceImpl implements BonusService{
    @Autowired
    private BonusDb bonusDb;

    @Override
    public void addBonus(BonusModel bonus) {
        bonusDb.save(bonus);
    }

    @Override
    public List<BonusModel> findAllBonus() {
        return bonusDb.findAll();
    }
    @Override
    public Integer totalBonus(GajiModel gaji){
        List<BonusModel> daftarBonus= bonusDb.findByGaji(gaji);
        Integer total = 0;
        for(BonusModel bonus : daftarBonus){
    
            total+=bonus.getJumlahBonus();
        }
        return total;
    }

    @Override
    public List<BonusModel> findBonusByGaji(GajiModel gaji) {
        return bonusDb.findByGaji(gaji);
    }

    @Override
    public void deleteBonus(BonusModel bonus) {
        bonusDb.delete(bonus);
    }
}
