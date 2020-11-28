package apap.ta.sipayroll.repository;

import apap.ta.sipayroll.model.GajiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GajiDb extends JpaRepository<GajiModel,Long> {

}
