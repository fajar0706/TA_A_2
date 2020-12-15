package apap.ta.sipayroll.repository;

import apap.ta.sipayroll.model.LemburModel;
import apap.ta.sipayroll.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LemburDb extends JpaRepository<LemburModel, Long> {
    // Optional<LemburModel> findById(Long idLembur);
    // LemburModel findLemburModelByUser(UserModel userModel);
}