package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.BonusModel;
import apap.ta.sipayroll.repository.BonusDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
