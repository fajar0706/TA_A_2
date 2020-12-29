package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    UserModel getUserModelByUsername(String username);
    Boolean passwordMatch(String pass1, String pass2);
    List<UserModel> findAllUser();
}
