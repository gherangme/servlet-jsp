package api;

import com.google.gson.Gson;
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

@WebServlet(name = "TasksApi", urlPatterns = {"/api/tasks","/api/tasks/add"})
public class TasksApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        switch (url) {
            case "/api/tasks":
                basicResponse = getAllTasks();
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
            case "/api/tasks/add":
                String name = req.getParameter("name");
                Date start_date = Date.valueOf(req.getParameter("start_date"));
                Date end_date = Date.valueOf(req.getParameter("end_date"));
                int user_id = Integer.parseInt(req.getParameter("user_id"));
                int job_id = Integer.parseInt(req.getParameter("job_id"));

                basicResponse = addNewTask(name,start_date,end_date,user_id,job_id);
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

    private BasicResponse getAllTasks() {
        BasicResponse response = new BasicResponse();
        TaskService taskService = new TaskService();

        response.setData(taskService.getAllTasks());
        response.setStatusCode(200);

        return response;
    }

    private BasicResponse addNewTask(String name, Date start_date, Date end_date,int user_id,int job_id) {
        BasicResponse response = new BasicResponse();
        TaskService taskService = new TaskService();
        response.setData(taskService.addNewTask(name,start_date,end_date,user_id,job_id));
        response.setStatusCode(200);

        return response;
    }

}
