package cn.yznu.imssra.controller.actioncontroller;

import cn.yznu.imssra.POI.POIUtil;
import cn.yznu.imssra.bean.*;
import cn.yznu.imssra.service.ImssraService;
import cn.yznu.imssra.util.MyUtil;
import cn.yznu.imssra.util.constants.Constant;
import cn.yznu.imssra.util.tag.PageModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    /**
     * 自动注入imssraService
     */
    @Autowired
    @Qualifier("imssraService")
    private ImssraService imssraService;

    /**
     * 修改用户个人信息
     */
    @RequestMapping(value="/modifyPersonalInfo",method= RequestMethod.POST)
    public String submitGuoJiaJiProjectCommitment(HttpSession session,String url, String username, String realname, String sex, String phonenumber, String email, Model model) {
        User user = (User) session.getAttribute("user");
        imssraService.modifyUserInfo(user.getId(),username,realname,sex,phonenumber,email);
        User new_user = imssraService.findUserById(user.getId());
        session.setAttribute("user",new_user);
        model.addAttribute("url", url);
        return "common/submitSuccess";
    }

    /**
     * 提交成果审核评论
     */
    @RequestMapping(value="/submitResultComment")
    public String submitResultComment(String url, Integer result_trialstate, String result_comment, Integer result_id, Model model) {
        Result result = imssraService.findResultById(result_id);
        Integer userid = result.getUserid();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        imssraService.saveMessage2(userid,result_id,result_trialstate,java.sql.Date.valueOf(sdf.format(d)));
        imssraService.updateResultTrialState(result_id,result_trialstate);
        Comment comment = imssraService.findCommontByResultId(result_id);
        if (comment == null){
            imssraService.saveComment(result_comment,result_id);
        }else {
            imssraService.updateComment(result_comment,result_id);
        }
        model.addAttribute("url", url);
        return "common/submitSuccess";
    }

    /**
     * 查看信息
     */
    @RequestMapping(value="/myMessage")
    public String myMessage(Integer pageIndex,HttpSession session,Model model) {
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
        User user = (User) session.getAttribute("user");
        Integer userid = user.getId();
        Integer usertype = user.getUsertype();
        List<Message> messages = null;
        if (usertype == 2){//管理员类型
            messages = imssraService.findAdminMessage(pageModel);
        }else {
            messages = imssraService.findMessageByUserId(userid,pageModel);
        }
        model.addAttribute("messages",messages);
        model.addAttribute("pageModel", pageModel);
        String url = null;
        switch (user.getUsertype()){
            case 0:
                url = "common/userMessage";
                break;
            case 1:
                url = "common/userMessage";
                break;
            case 2:
                url = "common/adminMessage";
                break;
        }
        return url;
    }

    /**
     * 删除信息
     */
    @RequestMapping(value="/deleteMessage")
    public String deleteMessage(Integer pageIndex,HttpSession session,Integer message_id,Model model) {
        imssraService.removeMessageByMessageId(message_id);
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
        User user = (User) session.getAttribute("user");
        Integer userid = user.getId();
        List<Message> messages = imssraService.findMessageByUserId(userid,pageModel);
        model.addAttribute("messages",messages);
        model.addAttribute("pageModel", pageModel);
        return "common/userMessage";
    }

    /**
     * 提交项目
     */
    @RequestMapping(value="/submitResultParam",method= RequestMethod.POST)
    public String submitResultParam(String url,String msg,HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //给管理员发信息（用户提交项目）
        imssraService.saveMessageForSubmit(java.sql.Date.valueOf(sdf.format(d)));
        System.out.println(msg);
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonObject jsonObject = parser.parse(msg).getAsJsonObject();
        String rst_name = jsonObject.get("rst_name").toString();
        String type_big = jsonObject.get("type_big").toString();
        String collegename = jsonObject.get("collegename").toString();
        String type_small = jsonObject.get("type_small").toString();
        String rst_desc = jsonObject.get("rst_desc").toString();
        String detail = jsonObject.get("detail").toString();
        String filename = jsonObject.get("filename").toString();
        String rst_instruction = jsonObject.get("rst_instruction").toString();

        //去掉首位的"
        rst_name = MyUtil.parsMsg(rst_name);
        collegename = MyUtil.parsMsg(collegename);
        type_big = MyUtil.parsMsg(type_big);
        type_small = MyUtil.parsMsg(type_small);
        rst_desc = MyUtil.parsMsg(rst_desc);
        detail = MyUtil.parsMsg(detail);
        filename = MyUtil.parsMsg(filename);
        rst_instruction = MyUtil.parsMsg(rst_instruction);

        Result result = new Result();
        result.setResultname(rst_name);
        result.setCollegename(Integer.parseInt(collegename));
        result.setDescription(rst_desc);
        result.setDetail(detail);
        result.setFilename(filename);
        result.setInstruction(rst_instruction);
        result.setTypebig(Integer.parseInt(type_big));
        result.setTypesmall(Integer.parseInt(type_small));
        result.setTrailstate(0);
        result.setTime(java.sql.Date.valueOf(sdf.format(d)));
        result.setUserid(user.getId());
        imssraService.addResultToDB(result);
        model.addAttribute("url", url);
        return "common/submitSuccess";
    }

    /**
     * 提交通知或者信息
     * @param url
     * @param msg
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value="/submitNotification",method= RequestMethod.POST)
    public String submitNotification(String url,String msg,HttpSession session,Model model) {
        System.out.println(msg);
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonObject jsonObject = parser.parse(msg).getAsJsonObject();
        String n_name = jsonObject.get("n_name").toString();
        String n_type = jsonObject.get("n_type").toString();
        String n_content = jsonObject.get("n_content").toString();
        String filename = jsonObject.get("filename").toString();

        //去掉首位的"
        n_name = MyUtil.parsMsg(n_name);
        n_type = MyUtil.parsMsg(n_type);
        n_content = MyUtil.parsMsg(n_content);
        filename = MyUtil.parsMsg(filename);

        Notification notification = new Notification();
        notification.setTitle(n_name);
        notification.setType(Integer.parseInt(n_type));
        notification.setContent(n_content);
        notification.setFilename(filename);

        //添加时间戳
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        notification.setTime(java.sql.Date.valueOf(sdf.format(d)));

        imssraService.addNotificationToDB(notification);
        model.addAttribute("url", url);
        return "common/submitSuccess";
    }

    /**
     * 修改项目
     */
    @RequestMapping(value="/modifyResultParam",method= RequestMethod.POST)
    public String modifyResultParam(String url, String msg,Integer rst_id,HttpSession session,Model model) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        imssraService.saveMessageForModify(java.sql.Date.valueOf(sdf.format(d)));
        System.out.println(msg);
        //Json的解析类对象
        JsonParser parser = new JsonParser();
//        //将JSON的String 转成一个JsonArray对象
        JsonObject jsonObject = parser.parse(msg).getAsJsonObject();
        String rst_name = jsonObject.get("rst_name").toString();
        String type_big = jsonObject.get("type_big").toString();
        String collegename = jsonObject.get("collegename").toString();
        String type_small = jsonObject.get("type_small").toString();
        String rst_desc = jsonObject.get("rst_desc").toString();
        String detail = jsonObject.get("detail").toString();
//        String filename = jsonObject.get("filename").toString();
        String rst_instruction = jsonObject.get("rst_instruction").toString();
//
//        //去掉首位的"
        rst_name = MyUtil.parsMsg(rst_name);
        collegename = MyUtil.parsMsg(collegename);
        type_big = MyUtil.parsMsg(type_big);
        type_small = MyUtil.parsMsg(type_small);
        rst_desc = MyUtil.parsMsg(rst_desc);
        detail = MyUtil.parsMsg(detail);
//        filename = MyUtil.parsMsg(filename);
        rst_instruction = MyUtil.parsMsg(rst_instruction);
//
        Result result = new Result();
        result.setId(rst_id);
        result.setResultname(rst_name);
        result.setCollegename(Integer.parseInt(collegename));
        result.setDescription(rst_desc);
        result.setDetail(detail);
//        result.setFilename(filename);
        result.setInstruction(rst_instruction);
        result.setTypebig(Integer.parseInt(type_big));
        result.setTypesmall(Integer.parseInt(type_small));
        result.setTrailstate(0);//设为0

        imssraService.updateResult(result);
        model.addAttribute("url", url);
        return "common/submitSuccess";
    }

    /**
     * 打开审核界面（不是导航）
     */
    @RequestMapping(value="/viewResultDetail")
    public String viewResultDetail(Integer result_id, Model model) {
        Result result = imssraService.findResultById(result_id);
        User user = imssraService.findUserById(result.getUserid());
        model.addAttribute("result", result);
        model.addAttribute("user", user);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);
        return "common/viewResultDetail";
    }

    @RequestMapping(value="/trialResult")
    public String trialResult(Integer result_id, Model model) {
        Result result = imssraService.findResultById(result_id);
        User user = imssraService.findUserById(result.getUserid());
        model.addAttribute("result", result);
        model.addAttribute("user", user);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);
        return "admin/trialResult";
    }

    @RequestMapping(value="/modifyResult")
    public String modifyResult(Integer result_id, Model model) {
        Result result = imssraService.findResultById(result_id);
        model.addAttribute("result", result);
        model.addAttribute("type_big", Constant.type_big);
        model.addAttribute("type_small", Constant.type_small);
        model.addAttribute("colleges", Constant.colleges);
        model.addAttribute("trail_state", Constant.trail_state);
        return "user/modifyResult";
    }

    @RequestMapping(value="/showComment")
    public String showComment(Integer result_id, Model model) {
        Comment comment = imssraService.findCommontByResultId(result_id);
        model.addAttribute("comment", comment);
        return "user/viewCommentDetail";
    }

    @RequestMapping(value="/deleteUserById")
    public String deleteUserById(Integer pageIndex,Integer id, Model model,Integer role,Integer collegename) {
//        Comment comment = imssraService.findCommontByResultId(result_id);
        imssraService.removeUserById(id);
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
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

    @RequestMapping(value="/deleteResultById")
    public String deleteResultById(Integer pageIndex,Integer id, Model model,Integer year,Integer collegename,Integer trialstate,Integer type_big,Integer type_small) {
        imssraService.removeResultById(id);
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
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
        System.out.println("当前页面："+pageIndex);
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Result> results = null;
        if (year == null && collegename == null && trialstate == null && type_big == null && type_small == null){//默认条件下查询
            System.out.println("无条件查询");
            results = imssraService.findAllResultList(pageModel);
        }else {//按条件分页查询
            System.out.println("有条件查询");
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
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
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
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
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
        String url = null;
        try {
            url = POIUtil.generateDocx(results,imssraService,request,response);
        } catch (IOException e) {
            System.out.println("异常");
            e.printStackTrace();
        }
        model.addAttribute("url",url);
        return "common/download";
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
            case 2://下载通知文件
                Notification notification = imssraService.findNotificationById(rst_id);
                fileName = notification.getFilename();
                break;
        }
        if (fileName != null) {
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
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
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
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
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
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
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
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
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
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
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
        PageModel pageModel = MyUtil.makePageModel(pageIndex);
        List<Notification> notifications = null;
        notifications = imssraService.findAllNotification(pageModel);
        model.addAttribute("notifications",notifications);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("resultType", Constant.resultType);
        return "admin/managementNotification";
    }

}
