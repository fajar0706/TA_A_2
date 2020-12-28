package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.GajiModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.repository.GajiDb;
import apap.ta.sipayroll.repository.LemburDb;
import reactor.core.publisher.Mono;
import apap.ta.sipayroll.rest.BaseResponseGaji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GajiRestServiceImpl implements GajiRestService  {
    private final WebClient webClient;


    public GajiRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://sipelatihan-a1.herokuapp.com").build();
    }

    public WebClient getWebClient() {
        return webClient;
    }

    @Override
    public Mono<BaseResponseGaji> getListPesertaPelatihan(String username){
        return this.webClient.get().uri("/api/v1/pelatihan/" + username).retrieve().bodyToMono(BaseResponseGaji.class);
    }
}
