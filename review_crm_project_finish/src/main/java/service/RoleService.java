package service;

import model.RoleModel;
import model.UserModel;
import repository.RoleRepository;
import repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;


public class RoleService {

    public List<RoleModel> getAllRoles() {
        RoleRepository roleRepository = new RoleRepository();
        return roleRepository.getAllRoles();
    }

    public boolean deleteRoleById(int id) {
        RoleRepository roleRepository = new RoleRepository();
        return roleRepository.deleteRoleById(id) >= 1;
    }


    public boolean addNewRole(String name, String desc) {
        RoleRepository roleRepository = new RoleRepository();
        return  roleRepository.addNewRole(name,desc) >= 1;
    }

}
