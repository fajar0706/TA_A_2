package apap.ta.sipayroll.repository;

import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.JenisBonusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JenisBonusDb extends JpaRepository<JenisBonusModel,Integer> {
}
