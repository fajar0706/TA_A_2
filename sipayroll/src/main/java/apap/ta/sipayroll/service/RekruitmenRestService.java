package apap.ta.sipayroll.service;

import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

public interface RekruitmenRestService {
    public Mono<String> postRekruitmen(MultiValueMap<String,String> data);
}
