package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.rest.BaseResponse;
import apap.ta.sipayroll.rest.Setting;
import apap.ta.sipayroll.rest.UserDetail;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.JSONObject;
import java.lang.String;


import javax.transaction.Transactional;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {

    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.siPegawaiUrl).build();
    }

//    @Override
//    public Mono<String> postStatus(UserModel user) {
//        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
//        data.add("id", user.getId());
//        data.add("username", user.getUsername());
//        data.add("password", user.getPassword());
//        return this.webClient.post().uri("/rest/user/add")
//                .syncBody(data)
//                .retrieve()
//                .bodyToMono(String.class);
//    }

//    @Override
//    public Mono<UserDetail> postUser(UserDetail pegawai) {
////        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
////        data.add("nama", pegawai.getNama());
////        data.add("username", pegawai.getUsername());
////        data.add("noTelepon", pegawai.getNoTelepon());
////        data.add("tempatLahir", pegawai.getTempatLahir());
////        data.add("tanggalLahir", pegawai.getTanggalLahir().toString());
////        data.add("alamat", pegawai.getAlamat());
////        data.add("idRole", pegawai.getIdRole().toString());
////        JSONObject obj = new JSONObject();
////        obj.put("username", pegawai.getUsername());
////        obj.put("nama", pegawai.getNama());
////        obj.put("noTelepon",  pegawai.getNoTelepon());
////        obj.put("tempatLahir", pegawai.getTempatLahir());
////        obj.put("tanggalLahir", pegawai.getTanggalLahir().toString());
////        obj.put("alamat", pegawai.getAlamat());
////        obj.put("roleId", pegawai.getIdRole().toString());
////        String jsonFinal = obj.toString();
//        return this.webClient.post().uri("/api/v1/pegawai")
//                .body(BodyInserters.fromValue(pegawai))
//                .retrieve()
//                .bodyToMono(UserDetail.class);
//    }
    @Override
    public String postUser(String username, String nama,
                           String noTelp, String tempatLahir, String tglLahir, String alamat, int role){
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("nama", nama);
        obj.put("noTelepon", noTelp);
        obj.put("tempatLahir", tempatLahir);
        obj.put("tanggalLahir", tglLahir);
        obj.put("alamat", alamat);
        obj.put("roleId", role);
        String jsonFinal = obj.toString();
        return this.webClient.post().uri(Setting.siPegawaiUrl).header("Content-Type", "application/json").syncBody(jsonFinal).retrieve().bodyToMono(String.class).block();
    }

    @Override
    public UserDetail getUserInfo(String username) {
        try {
            BaseResponse response = this.webClient.get()
                    .uri(Setting.siPegawaiUrl + "/" + username)
                    .retrieve()
                    .bodyToMono(BaseResponse.class).block();
            return response.getUserDetail();
        } catch(Exception exc)  {
            return null;
        }
    }
}
