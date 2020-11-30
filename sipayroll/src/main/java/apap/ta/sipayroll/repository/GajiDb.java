package apap.ta.sipayroll.repository;

import apap.ta.sipayroll.model.GajiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GajiDb extends JpaRepository<GajiModel,Integer> {
    Optional<GajiModel> findById(Integer id);

}
