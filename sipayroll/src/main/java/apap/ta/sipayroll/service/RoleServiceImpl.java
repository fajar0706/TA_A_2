package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.RoleModel;
import apap.ta.sipayroll.model.UserModel;
import apap.ta.sipayroll.repository.RoleDb;
import apap.ta.sipayroll.repository.UserDb;
import apap.ta.sipayroll.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDb roleDb;

    @Autowired
    UserDb userDb;

    @Override
    public List<RoleModel> findAll(){
        return roleDb.findAll();
    }
    @Override
    public String determineRole(String username){
        UserModel user = userDb.findByUsername(username);
        RoleModel role = user.getRole();
        return role.getNama();
    }

}