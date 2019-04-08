package cn.yznu.imssra.controller.pagecontroller;

import cn.yznu.imssra.POI.POIUtil;
import cn.yznu.imssra.bean.Notification;
import cn.yznu.imssra.bean.Result;
import cn.yznu.imssra.bean.User;
import cn.yznu.imssra.bean.WebSatet;
import cn.yznu.imssra.service.ImssraService;
import cn.yznu.imssra.util.MyUtil;
import cn.yznu.imssra.util.constants.Constant;
import cn.yznu.imssra.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 主界面页面跳转控制
 * 控制页面跳转逻辑（因为WEB-INF下不能直接使用路劲符号“/”来相互访问jsp文件）
 */

@Controller
public class MainPageController {

    /**
     * 自动注入imssraService
     */
    @Autowired
    @Qualifier("imssraService")
    private ImssraService imssraService;

    /**
     * 进入登录界面
     * @return
     */
    @RequestMapping(value = "/main")
    public String main(Model model){
        List<Result> goodResult = imssraService.find5GoodResultFromDB();//只查询最多5条优秀成果出来
        List<Notification> infos = imssraService.find5InfoFromDB();//只查询最多5条信息出来
        List<Notification> notes = imssraService.find5NoteFromDB();//只查询最多5条通知出来
        model.addAttribute("infos",infos);
        model.addAttribute("notes",notes);
        model.addAttribute("goodResult",goodResult);
        System.out.println(infos.size());
        System.out.println(notes.size());
        System.out.println(goodResult.size());
        return "common/login";
    }

    @RequestMapping(value = "/userMainByType")
    public String studentMain(Integer type, HttpSession session, Integer pageIndex, Model model,
                              Integer year,Integer collegename,Integer trialstate,Integer type_big,Integer type_small){
        User user = (User) session.getAttribute("user");
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        Integer userid = user.getId();
        List<Result> results = null;
        if (year == null && collegename == null && trialstate == null && type_big == null && type_small == null){//默认条件下查询
            results = imssraService.findResultListByUserid(userid,pageModel);
        }else {//按条件分页查询
            results = imssraService.findResultListByCondition(userid,year,collegename,trialstate,type_big,type_small,pageModel);
        }
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("results", results);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);

        String page = null;
        switch (type){
            case 0://我的成果页面
                page = "user/main";
                break;
            case 1://成果审核页面
                page = "user/resultTrail";
                break;
            case 2://成果统计页面
                page = "user/resultCount";
                break;
        }
        return page;
    }

    @RequestMapping(value = "/adminMain")
    public String adminMain(Integer pageIndex, Model model,Integer year,Integer collegename,Integer trialstate,Integer type_big,Integer type_small){
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Result> results = null;
        if (year == null && collegename == null && trialstate == null && type_big == null && type_small == null){//默认条件下查询
            results = imssraService.findAllResultList(pageModel);
        }else {//按条件分页查询
            results = imssraService.findResultListByCondition2(year,collegename,trialstate,type_big,type_small,pageModel);
        }
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("results", results);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);
        return "admin/main";
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo(){
        return "user/personInfo";
    }

    @RequestMapping(value = "/viewNotificationDetail")
    public String viewNotificationDetail(Integer type,Integer id,Model model){
        Notification notification = imssraService.findNotificationByIdAndType(type,id);
        model.addAttribute("notification",notification);
        model.addAttribute("resultType",Constant.resultType);
        return "common/viewNotificationDetail";
    }

    @RequestMapping(value = "/submitResult")
    public String submitResult(){
        return "user/submitResult";
    }

    @RequestMapping(value = "/resultTrial")
    public String resultTrial(Integer pageIndex, Model model,Integer year,Integer collegename,Integer trialstate,Integer type_big,Integer type_small){
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Result> results = null;
        if (year == null && collegename == null && trialstate == null && type_big == null && type_small == null){//默认条件下查询
            results = imssraService.findAllResultList(pageModel);
        }else {//按条件分页查询
            results = imssraService.findResultListByCondition2(year,collegename,trialstate,type_big,type_small,pageModel);
        }
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("results", results);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);
        return "admin/resultTrial";
    }

    @RequestMapping(value = "/setGoodResult")
    public String setGoodResult(Integer pageIndex, Model model,Integer year,Integer collegename,Integer trialstate,Integer type_big,Integer type_small){
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Result> results = null;
        if (year == null && collegename == null && trialstate == null && type_big == null && type_small == null){//默认条件下查询
            results = imssraService.findAllResultList(pageModel);
        }else {//按条件分页查询
            results = imssraService.findResultListByCondition2(year,collegename,trialstate,type_big,type_small,pageModel);
        }
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("results", results);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);
        model.addAttribute("isGoodResule", Constant.isGoodResule);
        return "admin/setGoodResult";
    }

    @RequestMapping(value = "/deleteResult")
    public String deleteResult(Integer pageIndex, Model model,Integer year,Integer collegename,Integer trialstate,Integer type_big,Integer type_small){
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Result> results = null;
        if (year == null && collegename == null && trialstate == null && type_big == null && type_small == null){//默认条件下查询
            results = imssraService.findAllResultList(pageModel);
        }else {//按条件分页查询
            results = imssraService.findResultListByCondition2(year,collegename,trialstate,type_big,type_small,pageModel);
        }
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("results", results);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);
        model.addAttribute("isGoodResule", Constant.isGoodResule);
        return "admin/deleteResult";
    }

    @RequestMapping(value = "/exportResult")
    public String exportResult(Model model,HttpServletRequest request,Integer year, Integer collegename, Integer trialstate, Integer type_big, Integer type_small){
        List<Result> results = null;
        if (year == null && collegename == null && trialstate == null && type_big == null && type_small == null){//默认条件下查询
            results = imssraService.findAllResultList();
        }else {//按条件分页查询
            results = imssraService.findResultListByCondition3(year,collegename,trialstate,type_big,type_small);
        }
        System.out.println(results.size());
//        imssraService.findUserById();
        String url = null;
        try {
            url = POIUtil.generateDocx(results,imssraService,request);
        } catch (IOException e) {
            System.out.println("异常");
            e.printStackTrace();
        }
        model.addAttribute("url",url);
        return "common/download";
    }

    @RequestMapping(value = "/accountAssignment")
    public String accountAssignment(Integer pageIndex, Model model,Integer role,Integer collegename){
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<User> users = null;
        if (role == null && collegename == null){//默认条件下查询
            users = imssraService.findAllUserList(pageModel);
        }else {//按条件分页查询
            users = imssraService.findUserListByCondition(role,collegename,pageModel);
        }
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("users", users);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);
        model.addAttribute("isGoodResule", Constant.isGoodResule);
        model.addAttribute("userType", Constant.userType);
        return "admin/accountAssignment";
    }

    @RequestMapping(value = "/addUser")
    public String addUser(){
        return "admin/addUser";
    }

    @RequestMapping(value = "/exportInfo")
    public String exportInfo(){
        return "admin/exportInfo";
    }

    @RequestMapping(value = "/closeWebsite")
    public String closeWebsite(Model model){
        WebSatet webSatet = imssraService.findWebSatet();
        model.addAttribute("webSatet",webSatet);
        return "admin/closeWebsite";
    }

    @RequestMapping(value = "/modifyWebsiteState")
    public String modifyWebsiteState(String url,Integer state,Model model){
        imssraService.updateWebSatet(state);
        model.addAttribute("url",url);
        return "common/submitSuccess";
    }

    @RequestMapping(value = "/setGoodResultById")
    public String setGoodResultById(Integer result_id, HttpServletResponse response) throws IOException {
        imssraService.updateGoodStateById(result_id);
        return "redirect:/setGoodResult";
    }

    @RequestMapping(value = "/addUserToDB")
    public String addUserToDB(String url,Model model,
                              String username,String password,Integer collegename,Integer role){
        imssraService.addUser(username,password,collegename,role);
        model.addAttribute("url",url);
        return "common/submitSuccess";
    }

    @RequestMapping(value = "/notificationManagement")
    public String notificationManagement(){
        return "admin/notificationManagement";
    }

}
