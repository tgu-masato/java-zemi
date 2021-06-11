package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Webサーブレットアノテーション
@WebServlet("/sample")
public class SampleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

        //リクエストパラメータの取得
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");

        //リクエストスコープに保存
        request.setAttribute("human",name + ":" +gender);

        //フォワードでJSPに移動
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sample/sample.jsp");
        dispatcher.forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type ヘッダの設定
        // html を返す。文字コードは、UTF-8。
//        response.setContentType("text/html; charset=UTF-8");
//
//        // なんか処理してるっぽいようにしたいので、文字列を用意する。
//        String message = "Hello World!\n";
//
//        // 返す html を生成する。
//        PrintWriter out = response.getWriter();
//        out.println("<html>\n" + message + "</html>");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sample.jsp");
        dispatcher.forward(request, response);
    }

}