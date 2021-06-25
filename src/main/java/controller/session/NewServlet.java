package controller.session;

import model.user.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sessions/new")
public class NewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //ログイン情報をリクエストスコープから受け取る
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email);
        System.out.println(password);

        User user = new User(
                null,
                null,
                email,
                password,
                null,
                null
        );

        if (user.authenticateUser(request)) {   //ログインが成功したら
            response.sendRedirect("/users");
        } else {
            //ログインが失敗したらフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/session/new.jsp");
            dispatcher.forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ログインしたいと選んだらsession/new.jspに遷移
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/session/new.jsp");
        dispatcher.forward(request, response);
    }
}