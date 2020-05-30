package com.springmvc.interceptor;

import com.springmvc.entity.Users;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger = Logger.getLogger(LoginInterceptor.class);

    // 身份认证、身份授权
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 判断session
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        String path = request.getRequestURI();
        boolean flag = false;
        if(path.indexOf("/user")>-1){
            System.out.println("-------");
        }else{
            if (user != null) {
                logger.info("检验合格");
            } else {
                // 获取请求的url
                String redirectUrl = request.getRequestURI();
                session.setAttribute("redirectUrl", redirectUrl);
                String bikeNo = request.getParameter("bikeNo");
                session.setAttribute("bikeNo", bikeNo);
                logger.info("请求的URL" + redirectUrl + bikeNo);
                // 执行这里表示用户身份需要认证，跳转登陆页面
                logger.info("跳转到登录页面:login.jsp");
                request.getRequestDispatcher("/WEB-INF/bike/user/login.jsp").forward(request, response);
            }
        }

        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("拦截器postHandle方法执行");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("拦截器afterCompletion方法执行");
    }
}
