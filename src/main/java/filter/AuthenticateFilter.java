package filter;

import model.user.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;

@WebFilter("/*")

public class AuthenticateFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        // ex) "/users/sessions/new"
        String servletPath = httpServletRequest.getServletPath();
        HttpSession session = httpServletRequest.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // ログイン画面にアクセスしたとき
        if (servletPath.equals("/sessions/new")) {
            if (currentUser == null) {
                chain.doFilter(req, resp);
            } else {
                // リダイレクト
                httpServletResponse.sendRedirect("/users");
            }
        }

        // 新規登録画面にアクセスしたとき
        else if (servletPath.equals("/user/signup")) {
            if (currentUser == null) {
                chain.doFilter(req, resp);
            } else {
                // リダイレクト
                httpServletResponse.sendRedirect("/users");
            }
        }

        // 初期画面にアクセスしたとき
        else if (servletPath.equals("/index")) {
            if (currentUser == null) {
                chain.doFilter(req, resp);
            } else {
                // リダイレクト
                httpServletResponse.sendRedirect("/users");
            }
        }

        // ログアウト（画面）にアクセスしたとき
        else if (servletPath.equals("/sessions/delete")) {
            if (currentUser == null) {
                // リダイレクト
                httpServletResponse.sendRedirect("/sessions/new");
            } else {
                chain.doFilter(req, resp);
            }
        }

        //入退室管理画面にアクセスしたとき
        else if (servletPath.equals("/status/*")) {
            if (currentUser == null) {
                chain.doFilter(req, resp);
            } else {
                httpServletResponse.sendRedirect("/users");
            }
        }

        // ログインしないとダメな画面にアクセスしたとき
        else {
            if (currentUser == null) {
                // リダイレクト
                httpServletResponse.sendRedirect("/sessions/new");
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
