package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    UserModel getUserModelByUsername(String username);
    Boolean passwordMatch(String pass1, String pass2);
}
