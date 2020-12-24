package apap.ta.sipayroll.repository;

import apap.ta.sipayroll.model.LemburModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.model.GajiModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LemburDb extends JpaRepository<LemburModel, Long> {
    List<LemburModel> findByGaji(GajiModel gaji);
}