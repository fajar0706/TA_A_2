package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;

import java.util.List;

public interface GajiService {
    void addGaji(GajiModel gaji);

    List<GajiModel> findAllGaji();

    List<GajiModel> getGajiList();

    GajiModel getGajiById(Integer id);

    void deleteGaji(GajiModel gaji);

    GajiModel changeGaji(GajiModel gaji);

    Integer totalPendapatan(GajiModel gaji);

    List<Integer> totalPendapatan();

    GajiModel getGajiModelByUser(UserModel userModel);

    GajiModel changeStatus(GajiModel gaji);
}
