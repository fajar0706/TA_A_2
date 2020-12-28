package apap.ta.sipayroll.service;


import apap.ta.sipayroll.model.BonusModel;
import apap.ta.sipayroll.model.GajiModel;

import java.util.List;

public interface BonusService {
    void addBonus(BonusModel bonus);
    List<BonusModel> findAllBonus();
    Integer totalBonus(GajiModel gaji);
    List<BonusModel> findBonusByGaji(GajiModel gaji);
    void deleteBonus(BonusModel bonus);
}
