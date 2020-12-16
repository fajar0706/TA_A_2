package apap.ta.sipayroll.service;


import apap.ta.sipayroll.model.BonusModel;

import java.util.List;

public interface BonusService {
    void addBonus(BonusModel bonus);
    List<BonusModel> findAllBonus();
}
