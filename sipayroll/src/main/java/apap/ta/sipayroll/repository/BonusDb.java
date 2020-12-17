package apap.ta.sipayroll.repository;

import apap.ta.sipayroll.model.BonusModel;
import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusDb extends JpaRepository<BonusModel,Integer> {
    BonusModel findBonusModelByGaji(GajiModel gajiModel);
}
