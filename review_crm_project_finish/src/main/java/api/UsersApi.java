package api;

import com.google.gson.Gson;
import model.RoleModel;
import payload.BasicResponse;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UsersApi",urlPatterns = {"/api/users/add","/api/users","/api/users/delete"})
public class UsersApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath(); //trả ra urlPattern mà user đang gọi
        BasicResponse basicResponse = new BasicResponse();
        switch (url) {
            case "/api/users":
                basicResponse = getAllUsers();
                break;
            case "/api/users/delete":
                int id = Integer.parseInt(req.getParameter("id"));
                basicResponse = deleteUserById(id);
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Đường dẫn không tồn tại !");
                break;
        }

        //Nếu kiểu dữ liệu là List hoặc mảng -> JSON ARRAY
        //Nếu kiểu dữ liệu là đối tượng -> JSON OBJECT
        Gson gson = new Gson();
        //toJson: biến về kiểu String
        //fromJson: biến về kiểu object
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
        String url = req.getServletPath(); //trả ra urlPattern mà user đang gọi
        BasicResponse basicResponse = new BasicResponse();
        switch (url) {
            case "/api/users/add":
                String fullName = req.getParameter("fullname");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                int role_id = Integer.parseInt(req.getParameter("role_id"));
                basicResponse = addUser(fullName,email,password,role_id);
                break;
//            case "/api/roles/delete":
//                int id = Integer.parseInt(req.getParameter("id"));
//                basicResponse = deleteRoleById(id);
//                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Đường dẫn không tồn tại !");
                break;
        }

        //Nếu kiểu dữ liệu là List hoặc mảng -> JSON ARRAY
        //Nếu kiểu dữ liệu là đối tượng -> JSON OBJECT
        Gson gson = new Gson();
        //toJson: biến về kiểu String
        //fromJson: biến về kiểu object
        String dataJson = gson.toJson(basicResponse);

        //lỗi CORS policy: khác domain (localhost) nên dùng addheader để cho phép localhost khác
        //call đến api này
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        //convert đối tượng hoặc mảng về json tương ứng
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }

    private BasicResponse addUser(String fullName, String email, String password, int role_id) {
        BasicResponse response = new BasicResponse();
        UserService userService = new UserService();
        boolean isSuccess = userService.addUser(fullName,email,password,role_id);
        if(isSuccess) {
            response.setStatusCode(200);
            response.setMessage("Thêm user thành công");
            response.setData(isSuccess);
        }else {
            response.setStatusCode(200);
            response.setMessage("Thêm user không thành công");
            response.setData(isSuccess);
        }
        return response;
    }

    private BasicResponse getAllUsers() {
        BasicResponse response = new BasicResponse();
        UserService userService = new UserService();
        response.setData(userService.getAllUsers());
        response.setStatusCode(200);
        return response;
    }

    private BasicResponse deleteUserById(int id) {
        BasicResponse response = new BasicResponse();
        UserService userService = new UserService();
        response.setData(userService.deleteUserById(id));
        response.setMessage("Xóa user thành công");
        response.setStatusCode(200);
        return response;
    }
}
