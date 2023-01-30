package service;

import repository.UsersRepository;

public class LoginService {
    //Tên hàm sẽ là tên chức năng
    public boolean checkLogin(String email, String password) {
        UsersRepository usersRepository = new UsersRepository();
        int count = usersRepository.countUsersByEmailAndPassword(email,password);

        return count>0;
    }

}
