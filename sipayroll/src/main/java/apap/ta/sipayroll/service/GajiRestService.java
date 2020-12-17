package apap.ta.sipayroll.service;

import reactor.core.publisher.Mono;
import apap.ta.sipayroll.rest.BaseResponseGaji;

public interface GajiRestService {
    Mono<BaseResponseGaji> getListPesertaPelatihan(String username);
    
}
