package servlet;

import config.MysqlConfig;
import filerac.User;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Annotation (tên định danh)
//Ký hiệu: @

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    //Truyền và nhận tham số bẳng phương thức GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Phản hồi đoạn print ra cho người dùng
//        resp.getWriter().println("Đây là trang login");

        //Lấy tham số người ta truyền ở trên trình duyệt
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        System.out.println(username+" - "+password);
//
//        if(username.equals("cybersoft") && password.equals("admin@123")) {
//            req.setAttribute("username",username);
//            System.out.println("Đăng nhập thành công");
//            req.getRequestDispatcher("detail.jsp").forward(req,resp);
//        }else {
//            req.getRequestDispatcher("login_tet.jsp").forward(req,resp);
//        }

//        String username = req.getParameter("username");
//        req.setAttribute("username",username);
//        String password = req.getParameter("password");
//        req.setAttribute("password",password);
//        System.out.println(username);
//        System.out.println(password);

        //Khởi tạo cookie
        //Tạo ra lần đầu, cmt và chạy lại thì cookie cũ vẫn còn lưu ở phía client nếu thời gian vẫn chưa hết
        //Tên Cookie là duy nhất, nếu cùng tên và tạo ra 1 giá trị mới thì sẽ cập nhật giá trị mới vào tên cookie đó
//        Cookie cookie = new Cookie("username", URLEncoder.encode("Nguyen Van B","UTF-8"));
//        cookie.setMaxAge(8 * 60 * 60); //set thời gian hết hạn
//        resp.addCookie(cookie); //Yêu cầu trình duyệt phía client tạo cookie

        //Lấy toàn bộ cookie ở browser người dùng
        //Chuẩn mã hóa RSA
//        Cookie[] cookies = req.getCookies();
//        for(Cookie cookie: cookies) {
//            String name = cookie.getName();
//            if(name.equals("username")) {
//                System.out.println("Giá trị: "+ URLDecoder.decode(cookie.getValue(),"UTF-8"));
//            }
//        }

        //Khi một client kết nối trang web thì tự sinh ra session rồi
        //Khi này lấy session từ server
        HttpSession session = req.getSession();
        session.setAttribute("username","Trần Văn A");
        session.setMaxInactiveInterval(8 * 60 * 60); //set thời gian hết hạn của session

        String data = (String) session.getAttribute("username");

        System.out.println("Giá trị của session: "+data);

        Connection connection = MysqlConfig.getConnection();
        String query = "select * from users";
        try {
            //Chuẩn bị câu truy vấn cho prepare statement
            PreparedStatement statement = connection.prepareStatement(query);
            //kết quả câu truy vấn sẽ lưu vào resultset
            ResultSet resultSet = statement.executeQuery();
            //duyệt giá trị trong resultset và lấy ra thông tin cột mong muốn
            while(resultSet.next()) {
                String email = resultSet.getString("email");
                int roleId = resultSet.getInt("role_id");

                System.out.println("Email là "+email);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi thực thi câu truy vấn login: "+e.getMessage());
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        req.getRequestDispatcher("login.jsp").forward(req,resp);
        //Yêu cầu hiển thị nội dung trong file login
//        req.getRequestDispatcher("login_tet.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<User> userList = new ArrayList<>();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println("Email: "+email+" - "+"Password: "+password);

        LoginService loginService = new LoginService();
        boolean isSuccess = loginService.checkLogin(email,password);

        if(isSuccess) {
            //Tiến hành lưu cookie ở đây
            Cookie cookie = new Cookie("username",email);
            cookie.setMaxAge(8*60*60);
            resp.addCookie(cookie);

            resp.sendRedirect(req.getContextPath()+"/roles");
        }else {
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        System.out.println(isSuccess);

        //khi đăng nhập thành công và chuyền sang page role
        //lấy danh sách role từ database và hiển thị lên giao diện

        /*User user = new User();
        user.setFullName("cybersoft user");
        user.setAge(18);
        user.setGender(true);
        userList.add(user);

        User user1 = new User();
        user1.setFullName("cybersoft user1");
        user1.setAge(20);
        user1.setGender(false);
        userList.add(user1);

        System.out.println(username+" - "+password);
//        if(username.equals("cybersoft") && password.equals("admin@123")) {
            //setAttribute(Tenthamso,giatri): gửi tham số ra giao diện
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("users",userList);
            System.out.println("Đăng nhập thành công");
            req.getRequestDispatcher("detail.jsp").forward(req,resp);
//        }else {
//            req.getRequestDispatcher("login_tet.jsp").forward(req,resp);
//        }
         */
    }
}
//Get
//Không bảo mật, giới hạn 2083 ký tự
//Post
//Tham số đc truyền ngầm, không giới hạn về mặt ký tự

//Controller -> Service -> Repository -> Database
//Đn URL    Xử lý logic code
//Controller <- Service <- Repository <- Database
//                       Query database

/*
    JSON có 2 loại:
    JSON Object: {key: value, key: value...}
    JSON Array: [Tất cả dữ liệu cơ sở, object]

    Backend: viết API và trả về chuỗi JSON
    -> Front end, winform sẽ lấy nó xử lý tiếp

    Thư viện Gson: giúp biến 1 đối tượng thành json object hoặc
    json array
 */