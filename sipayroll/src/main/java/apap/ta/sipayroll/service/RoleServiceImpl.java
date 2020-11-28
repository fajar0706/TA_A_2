package apap.ta.sipayroll.service;

import apap.ta.sipayroll.model.RoleModel;
import apap.ta.sipayroll.repository.RoleDb;
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

    @Override
    public List<RoleModel> findAll(){
        return roleDb.findAll();
    }

}