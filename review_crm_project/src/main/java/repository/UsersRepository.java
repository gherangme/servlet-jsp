package repository;

import config.MysqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
