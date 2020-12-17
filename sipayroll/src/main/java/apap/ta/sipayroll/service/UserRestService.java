package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.rest.UserDetail;
import reactor.core.publisher.Mono;

public interface UserRestService {
//    Mono<String> postStatus(UserModel user);
    String postUser(String username, String nama,
                    String noTelp, String tempatLahir, String tglLahir, String alamat, int role);
    UserDetail getUserInfo(String username);
}
