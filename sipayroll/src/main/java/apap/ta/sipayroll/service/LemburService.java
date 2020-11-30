package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.LemburModel;
import apap.ta.sipayroll.model.GajiModel;

public interface LemburService {
    void addLembur(LemburModel lembur);
    LemburModel getLemburByIdLembur(Long idLembur);
    LemburModel updateLembur(LemburModel lembur);
}