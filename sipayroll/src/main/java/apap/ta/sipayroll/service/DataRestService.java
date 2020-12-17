package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;

import java.util.List;
import java.util.Map;

public interface DataRestService {
    List<Map<String,Object>> findDataKaryawanLama();

}
