package service;

import model.UserModel;
import repository.UsersRepository;

import java.util.List;

public class UserService {
    public boolean addUser(String fullName, String email, String password, int role_id) {
        UsersRepository usersRepository = new UsersRepository();
        return usersRepository.addUser(fullName,email,password,role_id)>0;
    }

    public List<UserModel> getAllUsers() {
        UsersRepository usersRepository = new UsersRepository();
        return usersRepository.getAllUsers();
    }

    public boolean deleteUserById(int id) {
        UsersRepository usersRepository = new UsersRepository();
        return usersRepository.deleteUserById(id)>=1;
    }
}
