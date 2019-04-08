package cn.yznu.imssra.controller.actioncontroller;

import cn.yznu.imssra.bean.Notification;
import cn.yznu.imssra.bean.Result;
import cn.yznu.imssra.bean.User;
import cn.yznu.imssra.service.ImssraService;
import cn.yznu.imssra.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class LoginController {

    /**
     * 自动注入imssraService
     */
    @Autowired
    @Qualifier("imssraService")
    private ImssraService imssraService;

    /**
     * 账号密码验证
     * @param request
     * @param response
     * @param session
     * @throws Exception
     */
    @RequestMapping(value="/usernameAndPasswordCheck",method= RequestMethod.POST)
    public void judgeyouxiang(HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        response.setContentType("text/html;charset=UTF-8");
        User user = imssraService.findUserByUserNameAndPasswordAndType(username, password,usertype);;
        //通过response 获取pw
        PrintWriter pw = response.getWriter();
        if(user == null){
            //用pw对象传递json
            pw.print("failed");
        }else{//账号密码都正确，跳转到主页
            session.setAttribute("user",user);
            String url = MyUtil.getPage(user);
            pw.print(url);
        }
        pw.flush();
        pw.close();
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, Model model){
        session.invalidate();
        List<Result> goodResult = imssraService.find5GoodResultFromDB();//只查询最多5条优秀成果出来
        List<Notification> infos = imssraService.find5InfoFromDB();//只查询最多5条信息出来
        List<Notification> notes = imssraService.find5NoteFromDB();//只查询最多5条通知出来
        model.addAttribute("infos",infos);
        model.addAttribute("notes",notes);
        model.addAttribute("goodResult",goodResult);
        return "common/login";
    }

}
