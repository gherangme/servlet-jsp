package api;

import com.google.gson.Gson;
import model.ProjectModel;
import payload.BasicResponse;
import service.ProjectService;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ProjectsApi", urlPatterns = {"/api/projects","/api/projects/add","/api/projects/delete"})
public class ProjectsApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        switch (url) {
            case "/api/projects":
                basicResponse = getAllProject();
                break;
            case "/api/projects/delete":
                int id = Integer.parseInt(req.getParameter("id"));
                basicResponse = deleteProjectById(id);
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Đường dẫn không tồn tại !");
                break;
        }
        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        //convert đối tượng hoặc mảng về json tương ứng
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        switch (url) {
            case "/api/projects/add":
                String name = req.getParameter("name-project");
                Date startDate = Date.valueOf(req.getParameter("start-date"));
                Date endDate = Date.valueOf(req.getParameter("end-date"));
                basicResponse = addNewProject(name,startDate,endDate);
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Đường dẫn không tồn tại !");
                break;
        }
        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        //convert đối tượng hoặc mảng về json tương ứng
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }

    private BasicResponse getAllProject() {
        BasicResponse response = new BasicResponse();
        ProjectService projectService = new ProjectService();

        response.setData(projectService.getAllProjects());
        response.setStatusCode(200);

        return response;
    }

    private BasicResponse addNewProject(String name, Date startDate, Date endDate) {
        BasicResponse response = new BasicResponse();
        ProjectService projectService = new ProjectService();

        response.setData(projectService.addNewProject(name,startDate,endDate));
        response.setMessage("Thêm dự án thành công");
        response.setStatusCode(200);

        return response;
    }

    private BasicResponse deleteProjectById(int id) {
        BasicResponse response = new BasicResponse();
        ProjectService projectService = new ProjectService();

        response.setData(projectService.deleteProjectByid(id));
        response.setMessage("Xóa thành công");
        response.setStatusCode(200);

        return response;
    }
}
