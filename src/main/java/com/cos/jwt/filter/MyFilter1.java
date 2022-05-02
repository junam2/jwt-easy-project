package com.cos.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        /*
        1. 토큰을 만들어 줘야 한다. id,pw 넘어와서 정상적으로 로그인이 되면 토큰 발급 후 전달
        2. 이후 클라이언트에서 요청 시 header 에 Authorization 에 value 값을 넣어서 전달
        3. 토큰이 넘어오면 해당 토큰이 처음에 전달해준 토큰과 일치하는지 확인 ( RSA, HS256 )
        */

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletRequest.setCharacterEncoding("UTF-8");

        if(httpServletRequest.getMethod().equals("POST")) {
            String headerAuth = httpServletRequest.getHeader("Authorization");

            if(headerAuth.equals("cos")) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                PrintWriter out = httpServletResponse.getWriter();
                out.println("not allowed");
            }
        }
    }
}
