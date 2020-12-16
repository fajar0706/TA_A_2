package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.JenisBonusModel;
import apap.ta.sipayroll.repository.BonusDb;
import apap.ta.sipayroll.repository.JenisBonusDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class JenisBonusServiceImpl implements JenisBonusService {
    @Autowired
    private JenisBonusDb jenisBonusDb;

    @Override
    public List<JenisBonusModel> findAllJenisBonus() {
        return jenisBonusDb.findAll();
    }
}
