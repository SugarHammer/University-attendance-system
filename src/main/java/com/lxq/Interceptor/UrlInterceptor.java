package com.lxq.Interceptor;

import com.lxq.model.object.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UrlInterceptor implements HandlerInterceptor {
    //在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//        System.out.println("Intercepor:preHandle被执行！！");
//        System.out.println("preHandle:session1  "+request.getSession().getAttribute("users"));
        //从Session中获取在线的用户
        Object user = request.getSession().getAttribute("users");

//        System.out.println("preHandle:session2  "+request.getSession().getAttribute("users"));
        String requestURI = request.getRequestURI();
        System.out.println("requestURI:"+requestURI);

        //访问以下url直接放行
        if("/user/login".equals(requestURI)
                || "/user/userRegister".equals(requestURI)
                || "/user/outSignIn".equals(requestURI)
                || "/error".equals(requestURI)
        ){
//            System.out.println("请求放行！！");
            return true;
        }

        //访问其他url需要判断是否登录
        if (user == null) {
            //请求转发
//            request.getRequestDispatcher("/").forward(request, response);
            //重定向
            response.sendRedirect("/");
            return false;
        } else {
            String userLv = ((User)user).getUserLv();
            //管理员访问其他URL跳转errorPage,防止管理员通过url进行非法访问
            if ("0".equals(userLv) && (("/index/systemIndex".equals(requestURI))
                                    || ("/index/recordIndex".equals(requestURI))
                                    || ("/index/courseApplicationIndex".equals(requestURI))
                                    || ("/index/leaveApplicationIndex".equals(requestURI)))
                                    && (!"/index/userIndex".equals(requestURI))){

                response.sendRedirect("/error");
            }
            return true;
        }
    }
}
