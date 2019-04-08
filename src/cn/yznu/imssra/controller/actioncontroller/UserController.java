package cn.yznu.imssra.controller.actioncontroller;

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

import javax.servlet.http.HttpSession;
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
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
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
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
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
//        //添加时间戳
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        result.setTime(java.sql.Date.valueOf(sdf.format(d)));
//
//        User user = (User) session.getAttribute("user");
//        result.setUserid(user.getId());
//        imssraService.addResultToDB(result);
        imssraService.updateResult(result);
        model.addAttribute("url", url);
        return "common/submitSuccess";
    }

    /**
     * 打开审核界面（不是导航）
     * @param result_id
     * @param model
     * @return
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

    @RequestMapping(value="/deleteResultById")
    public String deleteResultById(Integer pageIndex,Integer id, Model model,Integer role,Integer year,Integer collegename,Integer trialstate,Integer type_big,Integer type_small) {
//        Comment comment = imssraService.findCommontByResultId(result_id);
        imssraService.removeResultById(id);
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

}
