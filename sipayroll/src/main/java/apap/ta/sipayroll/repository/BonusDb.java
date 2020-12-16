package apap.ta.sipayroll.repository;

import apap.ta.sipayroll.model.BonusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusDb extends JpaRepository<BonusModel,Integer> {
}
