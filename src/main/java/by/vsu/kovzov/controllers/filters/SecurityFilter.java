package by.vsu.kovzov.controllers.filters;

import by.vsu.kovzov.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

public class SecurityFilter implements Filter {
    private final Map<String, Set<User.Role>> permissions = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Set<User.Role> all = new HashSet<>(Arrays.asList(User.Role.values()));


        Set<User.Role> admin = new HashSet<>();
        admin.add(User.Role.ADMIN);

        Set<User.Role> registrators = new HashSet<>();
        registrators.add(User.Role.REGISTRATOR);

        permissions.put("/user/list", admin);
        permissions.put("/user/edit", admin);
        permissions.put("/user/save", admin);
        permissions.put("/user/delete", admin);

        permissions.put("/doctor/edit", registrators);
        permissions.put("/doctor/save", registrators);
        permissions.put("/doctor/delete", registrators);

        permissions.put("/specialization/edit", registrators);
        permissions.put("/specialization/save", registrators);
        permissions.put("/specialization/delete", registrators);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = getUrl(req);
        Set<User.Role> roles = permissions.get(url);
        if (roles != null) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("session_user");
                if (user != null && roles.contains(user.getRole())) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        } else {
            chain.doFilter(request, response);
            return;
        }

        String msg = "Доступ запрещен";
        resp.sendRedirect(req.getContextPath() + "/login.html?message=" + URLEncoder.encode(msg, "UTF-8"));
    }

    private String getUrl(HttpServletRequest httpReq) {
        String uri = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = uri.lastIndexOf(".html");
        String url;
        if (postfixIndex != -1) {
            url = uri.substring(context.length(), postfixIndex);
        } else {
            url = uri.substring(context.length());
        }
        return url;
    }
}
