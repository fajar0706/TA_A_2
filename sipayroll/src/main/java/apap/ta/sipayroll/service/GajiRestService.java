package apap.ta.sipayroll.service;

import reactor.core.publisher.Mono;
import apap.ta.sipayroll.rest.BaseResponse;

public interface GajiRestService {
    Mono<BaseResponse> getListPesertaPelatihan(String username);
    
}
