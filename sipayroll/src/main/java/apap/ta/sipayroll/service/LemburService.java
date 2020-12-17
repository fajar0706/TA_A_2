package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.LemburModel;
import apap.ta.sipayroll.model.GajiModel;

import java.util.List;

public interface LemburService {
    void addLembur(LemburModel lembur);
    LemburModel getLemburByIdLembur(Long idLembur);
    LemburModel updateLembur(LemburModel lembur);
    List<LemburModel> getLemburList();
    void deleteLembur(LemburModel lembur);
    List<LemburModel> findAllLembur();
    Integer totalLembur(GajiModel gaji);
}