package cn.yznu.imssra.controller.actioncontroller;

import cn.yznu.imssra.bean.Comment;
import cn.yznu.imssra.bean.Notification;
import cn.yznu.imssra.bean.Result;
import cn.yznu.imssra.bean.User;
import cn.yznu.imssra.service.ImssraService;
import cn.yznu.imssra.util.MyUtil;
import cn.yznu.imssra.util.constants.Constant;
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
    @RequestMapping(value="/submitResultComment",method= RequestMethod.POST)
    public String submitResultComment(String url, Integer result_trialstate, String result_comment, Integer result_id, Model model) {
        imssraService.updateResultTrialState(result_id,result_trialstate);
        imssraService.saveComment(result_comment,result_id);
        model.addAttribute("url", url);
        return "common/submitSuccess";
    }

    /**
     * 提交项目
     */
    @RequestMapping(value="/submitResultParam",method= RequestMethod.POST)
    public String submitResultParam(String url,String msg,HttpSession session,Model model) {
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
        //添加时间戳
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        result.setTime(java.sql.Date.valueOf(sdf.format(d)));

        User user = (User) session.getAttribute("user");
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

}
