package apap.ta.sipayroll.service;
import apap.ta.sipayroll.service.RekruitmenRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import javax.transaction.Transactional;

@Service
@Transactional
public class RekruitmenRestServiceImpl implements RekruitmenRestService {

    private final WebClient webClient;
    public RekruitmenRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient= webClientBuilder.baseUrl("https://9fe1bd89-15a5-40ee-92c7-87836ca8152f.mock.pstmn.io").build();
    }

    @Override
    public Mono<String> postRekruitmen(MultiValueMap<String,String> data) {
        Mono<String> test = this.webClient.post().uri("/rest/rekruitmen").syncBody(data).retrieve().bodyToMono(String.class);
        System.out.println("ini dari postman" + test.block());
        return test;
    }}