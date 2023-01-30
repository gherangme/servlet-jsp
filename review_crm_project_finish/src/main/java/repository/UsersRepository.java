package repository;

import config.MysqlConfig;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepository {

    public int countUsersByEmailAndPassword(String email, String password) {
        //Mở kết nối tới database
        Connection connection = MysqlConfig.getConnection();
        int count = 0;
        String query = "select count(*) as count from users u where u.email=? and u.password=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int addUser(String fullName, String email, String password, int role_id) {
        int count = 0;
        Connection connection = MysqlConfig.getConnection();
        String queryCheck = "select count(*) as count from users u where u.email = ?";
        String query = "insert into users(email,password,fullname,role_id) value(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(queryCheck);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt("count");
            }
        }catch (Exception e) {
            System.out.println("Error addUser "+e.getMessage());
        }
        if(count == 0) {
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,email);
                statement.setString(2,password);
                statement.setString(3,fullName);
                statement.setInt(4,role_id);
                count = statement.executeUpdate();
            }catch (Exception e) {
                System.out.println();
            }finally {
                try {
                    connection.close();
                }catch (Exception e) {

                }
            }
        }else {
            count = 0;
        }

        return count;
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> listUsers = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select * from users";

        try {
            ResultSet resultSet= connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                UserModel userModel = new UserModel();
                RoleRepository roleRepository = new RoleRepository();
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setFullName(resultSet.getString("fullname"));
                userModel.setRole_id(resultSet.getInt("role_id"));
                userModel.setRole(roleRepository.getRoleById(userModel.getRole_id()));

                listUsers.add(userModel);
            }
        }catch (Exception e) {
            System.out.println("Lỗi câu query getAllUsers "+e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e) {

            }
        }

        return listUsers;
    }

    public int deleteUserById(int id) {
        int isDeleteSuccess = 0;
        Connection connection = MysqlConfig.getConnection();
        String query = "delete from users where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);

            isDeleteSuccess = statement.executeUpdate();
        }catch (Exception e) {
            System.out.println("Error delete user by id "+e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e) {

            }
        }

        return isDeleteSuccess;
    }
}
