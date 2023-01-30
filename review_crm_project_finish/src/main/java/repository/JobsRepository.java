package repository;

import config.MysqlConfig;
import model.ProjectModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobsRepository {

    public List<ProjectModel> getAllProjects() {
        List<ProjectModel> listProjects = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select * from jobs";

        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();

            while (resultSet.next()) {
                ProjectModel projectModel = new ProjectModel();
                projectModel.setId(resultSet.getInt("id"));
                projectModel.setName(resultSet.getString("name"));
                projectModel.setStartDate(resultSet.getDate("start_date"));
                projectModel.setEndDate(resultSet.getDate("end_date"));

                listProjects.add(projectModel);
            }
        }catch (Exception e) {
            System.out.println("Lỗi câu query getAllProjects "+e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e) {

            }
        }

        return listProjects;
    }

    public int addNewProject(String name, Date startDate, Date endDate) {
        int isSuccess = 0;
        Connection connection = MysqlConfig.getConnection();
        String query = "insert into jobs(name,start_date,end_date) value(?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setDate(2,startDate);
            statement.setDate(3,endDate);

            isSuccess = statement.executeUpdate();
        }catch (Exception e) {
            System.out.println("Lỗi câu query addNewProject "+e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e) {

            }
        }

        return isSuccess;
    }

    public int deleteProjectById(int id) {
        int isSuccess = 0;
        Connection connection = MysqlConfig.getConnection();
        String query = "delete from jobs u where u.id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);

            isSuccess = statement.executeUpdate();
        }catch (Exception e) {
            System.out.println("Lỗi câu query deleteProjectById "+e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e) {

            }
        }

        return isSuccess;
    }

}
