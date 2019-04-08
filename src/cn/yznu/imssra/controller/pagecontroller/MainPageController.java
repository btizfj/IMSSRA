package cn.yznu.imssra.controller.pagecontroller;

import cn.yznu.imssra.POI.POIUtil;
import cn.yznu.imssra.bean.*;
import cn.yznu.imssra.service.ImssraService;
import cn.yznu.imssra.util.MyUtil;
import cn.yznu.imssra.util.constants.Constant;
import cn.yznu.imssra.util.tag.PageModel;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String exportResult(Model model, HttpServletResponse response, HttpServletRequest request, Integer year, Integer collegename, Integer trialstate, Integer type_big, Integer type_small){
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
            url = POIUtil.generateDocx(results,imssraService,request,response);
//            MyUtil.download("info.xlsx",url,request,response);
//            try {
//                MyUtil.download("info.xlsx",url,request,response);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        } catch (IOException e) {
            System.out.println("异常");
            e.printStackTrace();
        }
        model.addAttribute("url",url);
        return "common/download";
//        return "admin/exportInfo";
    }

    @RequestMapping(value = "/downloadFile")
    public String download(Integer type, Integer rst_id, HttpServletRequest request, HttpServletResponse response) {
        String bashPath = request.getSession().getServletContext().getRealPath("/upload");
        String fileName = null;
        switch (type){
            case 0://管理员下载统计信息表info.xlsx
                fileName = "info.xlsx";
                break;
            case 1://下载成果文件
                Result result = imssraService.findResultById(rst_id);
                fileName = result.getFilename();
                break;
        }
        if (fileName != null) {
//            String realPath = request.getServletContext().getRealPath("WEB-INF/File/");
            File file = new File(bashPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
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

    @RequestMapping(value = "/resultSearch")
    public String resultSearch(Integer rst_number,String rst_name,Model model){
        List<Result> results = null;
        if (rst_number != null && rst_name != null){
            results = imssraService.findResultBySearch(rst_number,rst_name);
        }
        model.addAttribute("results",results);
        return "common/resultSearch";
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

    @RequestMapping(value = "/viewAllNotificationByPage")
    public String viewAllNotificationByPage(Integer pageIndex, Integer n_type, Model model){
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Notification> notifications = null;
        notifications = imssraService.findAllNotificationByPageAndType(n_type,pageModel);
        System.out.println(notifications.size());
        model.addAttribute("notifications",notifications);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("n_type", n_type);
        model.addAttribute("resultType", Constant.resultType);
        return "common/showAllNotification";
    }

    @RequestMapping(value = "/viewAllGoodResultByPage")
    public String viewAllGoodResultByPage(Integer pageIndex, Model model){
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Result> results = null;
        results = imssraService.findAllGoodResultByPage(pageModel);
        System.out.println(results.size());
        model.addAttribute("results",results);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);
        return "common/showAllGoodResult";
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

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Result result = imssraService.findResultById(result_id);
        Integer userid = result.getUserid();
        imssraService.saveMessage1(userid,result_id,java.sql.Date.valueOf(sdf.format(d)));
        return "redirect:/setGoodResult";
    }

    @RequestMapping(value = "/addUserToDB")
    public String addUserToDB(String url,Model model, String username,String password,Integer collegename,Integer role){
        imssraService.addUser(username,password,collegename,role);
        model.addAttribute("url",url);
        return "common/submitSuccess";
    }

    @RequestMapping(value = "/deliverManagement")
    public String deliverManagement(){
        return "admin/deliverManagement";
    }

    @RequestMapping(value = "/notificationManagement")
    public String notificationManagement(Integer pageIndex,Model model){
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Notification> notifications = null;
        notifications = imssraService.findAllNotification(pageModel);
        model.addAttribute("notifications",notifications);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("resultType", Constant.resultType);
        return "admin/managementNotification";
    }

    @RequestMapping(value = "/sessionTimeout")
    public String sessionTimeout(){
        return "common/sessionTimeout";
    }

    @RequestMapping(value = "/deleteNotificationById")
    public String deleteNotificationById(Integer pageIndex,Integer id,Model model){
        imssraService.removeNotificationById(id);
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Notification> notifications = null;
        notifications = imssraService.findAllNotification(pageModel);
        model.addAttribute("notifications",notifications);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("resultType", Constant.resultType);
        return "admin/managementNotification";
    }

}
