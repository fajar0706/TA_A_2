package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.GajiModel;

import java.util.List;

public interface GajiService {
    void addGaji(GajiModel gaji);
    List<GajiModel> findAllGaji();

    List<GajiModel> getGajiList();

    GajiModel getGajiById(Integer id);

    void deleteGaji(GajiModel gaji);
}
