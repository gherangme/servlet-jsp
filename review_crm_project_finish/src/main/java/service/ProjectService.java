package service;

import model.ProjectModel;
import repository.JobsRepository;

import java.sql.Date;
import java.util.List;

public class ProjectService {
    public List<ProjectModel> getAllProjects() {
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.getAllProjects();
    }

    public boolean addNewProject(String name, Date startDate, Date endDate) {
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.addNewProject(name,startDate,endDate)>=1;
    }

    public boolean deleteProjectByid(int id) {
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.deleteProjectById(id)>=1;
    }
}
