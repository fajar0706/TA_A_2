package apap.ta.sipayroll.repository;

import apap.ta.sipayroll.model.LemburModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LemburDb extends JpaRepository<LemburModel, Long> {

}
