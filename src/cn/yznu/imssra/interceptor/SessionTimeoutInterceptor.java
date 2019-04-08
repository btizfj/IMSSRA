package cn.yznu.imssra.interceptor;

import cn.yznu.imssra.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionTimeoutInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        System.out.println("进入拦截");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){    //未登录
            System.out.println("会话已经过期，请重新登录！");
            response.sendRedirect(request.getContextPath()+"/sessionTimeout");
            return false;
        }else{    //已经登录
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
