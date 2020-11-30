package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.repository.GajiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GajiServiceImpl implements GajiService  {

    @Autowired
    private GajiDb gajiDb;

    @Override
    public void addGaji(GajiModel gaji) {
        gaji.setStatusPersetujuan(0);
        gaji.setUserPengaju(gaji.getUser());
        gajiDb.save(gaji);
    }


}
