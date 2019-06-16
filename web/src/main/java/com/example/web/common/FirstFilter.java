//package com.example.web.common;
//
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Pattern;
//
//@Order(1)
//@WebFilter(filterName = "testFilter1", urlPatterns = "/*")
//public class FirstFilter implements Filter {
//
//    private String Urls [] = {"/login","/image/","/css","/register","/checkUser","/getUserList"};
//    protected static List<Pattern> patterns = new ArrayList<Pattern>();
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        patterns.add(Pattern.compile("/login"));
//
//    }
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession();
//        boolean isExclude = false;
//
//        String ip = request.getRemoteAddr();
//        String url = request.getRequestURI().toString();
//        System.out.println(ip+" 访问了 "+ url);
//
//        for (int i=0;i<Urls.length;i++){
//            System.out.println(Urls[i]);
//            if(url.startsWith(Urls[i])){
//                isExclude = true;
//            }
//        }
//
//        if(isExclude){
//            System.out.println(session.getId());
//            System.out.println(session.getAttribute("phoneNum"));
//            filterChain.doFilter(servletRequest, servletResponse);
//        }else{
//            if(session.getAttribute("phoneNum")!=null){
//
//                filterChain.doFilter(servletRequest, servletResponse);
//            }else{
//                response.sendRedirect("login");
//            }
//        }
//
//    }
//}
