package cn.yznu.imssra.service.impl;

import cn.yznu.imssra.bean.*;
import cn.yznu.imssra.dao.UserDao;
import cn.yznu.imssra.service.ImssraService;
import cn.yznu.imssra.util.tag.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OPMS系统服务层接口实现类
 * @Service用于将当前类注释为一个Spring的bean，名为userService
 */
@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT)
@Service("imssraService")
public class ImssraServiceImpl implements ImssraService {

    /**
     * 自动注入持久层Dao对象
     * */
    @Resource
    private UserDao userDao;

    @Override
    public User findUserByUserNameAndPasswordAndType(String username, String password, String usertype) {
        return userDao.selectUserByUserNameAndPasswordAndType(username,password,usertype);
    }

    @Override
    public void modifyUserInfo(int id, String username, String realname, String sex, String phonenumber, String email) {
        userDao.updateUserInfo(id,username,realname,sex,phonenumber,email);
    }

    @Override
    public User findUserById(int id) {
        return userDao.selectUserById(id);
    }

    @Override
    public void addResultToDB(Result result) {
        userDao.insertResultToDB(result);
    }

    @Override
    public void addNotificationToDB(Notification notification) {
        userDao.insertNotificationToDB(notification);
    }

    @Override
    public List<Result> findResultListByUserid(Integer userid, PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        params.put("userid", userid);
        int recordCount = userDao.countResultByUserid(userid);
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List<Result> results = userDao.selectResultsByParam(params);
        return results;
    }

    @Override
    public List<Result> findResultListByCondition(Integer userid, Integer year, Integer collegename, Integer trialstate, Integer typebig, Integer typesmall, PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        params.put("userid", userid);
        params.put("year", year);
        params.put("collegename", collegename);
        params.put("trialstate", trialstate);
        params.put("typebig", typebig);
        params.put("typesmall", typesmall);
        int recordCount = userDao.countResultByCondition(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List<Result> results = userDao.selectResultsByCondition(params);
        return results;
    }

    @Override
    public List<Result> findResultListByCondition2(Integer year, Integer collegename, Integer trialstate, Integer typebig, Integer typesmall, PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        params.put("year", year);
        params.put("collegename", collegename);
        params.put("trialstate", trialstate);
        params.put("typebig", typebig);
        params.put("typesmall", typesmall);
        int recordCount = userDao.countResultByCondition2(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List<Result> results = userDao.selectResultsByCondition2(params);
        return results;
    }

    @Override
    public List<Result> findResultListByCondition3(Integer year, Integer collegename, Integer trialstate, Integer typebig, Integer typesmall) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        params.put("year", year);
        params.put("collegename", collegename);
        params.put("trialstate", trialstate);
        params.put("typebig", typebig);
        params.put("typesmall", typesmall);
//        int recordCount = userDao.countResultByCondition3(params);
        List<Result> results = userDao.selectResultsByCondition3(params);
        return results;
    }

    @Override
    public List<User> findUserListByCondition(Integer role, Integer collegename, PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        params.put("role", role);
        params.put("collegename", collegename);
        int recordCount = userDao.countUserByCondition(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List<User> users = userDao.selectUsersByCondition(params);
        return users;
    }

    @Override
    public Result findResultById(Integer result_id) {
        return userDao.selectResultById(result_id);
    }

    @Override
    public List<Result> findAllResultList(PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        int recordCount = userDao.countAllResult();
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        System.out.println("#{pageModel.firstLimitParam}:"+pageModel.getFirstLimitParam());
        System.out.println("#{pageModel.pageSize}:"+pageModel.getPageSize());
        System.out.println("#{pageModel.pageIndex}:"+pageModel.getPageIndex());
        System.out.println("#{pageModel.recordCount}:"+pageModel.getRecordCount());
        List<Result> results = userDao.selectAllResults(params);
        return results;
    }

    @Override
    public List<Result> findAllResultList() {
        List<Result> results = userDao.selectAllResult();
        return results;
    }

    @Override
    public List<User> findAllUserList(PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        int recordCount = userDao.countAllUser();
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List<User> users = userDao.selectAllUsers(params);
        return users;
    }

    @Override
    public void addUser(String username, String password, Integer collegename, Integer role) {
        userDao.insertUser(username,password,collegename,role);
    }

    @Override
    public WebSatet findWebSatet() {
        return userDao.selectWebSatet();
    }

    @Override
    public void updateWebSatet(Integer state) {
        userDao.updateWebSatet(state);
    }

    @Override
    public List<Notification> find5InfoFromDB() {
        return userDao.select5Info();
    }

    @Override
    public List<Notification> find5NoteFromDB() {
        return userDao.select5Note();
    }

    @Override
    public List<Result> find5GoodResultFromDB() {
        return userDao.select5GoodResult();
    }

    @Override
    public Notification findNotificationByIdAndType(Integer type, Integer id) {
        return userDao.selectNotificationByIdAndType(type,id);
    }

    @Override
    public void updateGoodStateById(Integer result_id) {
        userDao.updateGoodState(result_id);
    }

    @Override
    public void updateResultTrialState(Integer result_id, Integer result_trialstate) {
        userDao.updateTrialState(result_id,result_trialstate);
    }

    @Override
    public void saveComment(String result_comment, Integer result_id) {
        userDao.saveResultComment(result_comment,result_id);
    }

    @Override
    public void updateComment(String result_comment, Integer result_id) {
        userDao.updateCommentById(result_comment,result_id);
    }

    @Override
    public Comment findCommontByResultId(Integer result_id) {
        return userDao.selectCommontByResultId(result_id);
    }

    @Override
    public void updateResult(Result result) {
        userDao.updateResult(result);
    }

    @Override
    public  List<Result> findResultBySearch(Integer rst_number,String rst_name) {
        return userDao.selectResultBySearch(rst_number,rst_name);
    }

    @Override
    public List<Notification> findAllNotificationByPageAndType(Integer n_type, PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        int recordCount = userDao.countAllNotificationByPageAndType(n_type);
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
            params.put("n_type", n_type);
        }
        return userDao.selectAllNotificationByPageAndType(params);
    }

    @Override
    public List<Result> findAllGoodResultByPage(PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        int recordCount = userDao.countAllGoodResultByPage();
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        return userDao.selectAllGoodResultByPageAndType(params);
    }

    @Override
    public List<Message> findMessageByUserId(Integer userid, PageModel pageModel) {
        System.out.println("userid:"+userid);
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        int recordCount = userDao.countAllMessageByUserId(userid);
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
            params.put("user_id", userid);
        }
        List<Message> messages = null;
        messages = userDao.selectAllMessageByUserId(params);
        return messages;
    }

    @Override
    public List<Message> findAdminMessage(PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        int recordCount = userDao.countAllAdminMessage();
        System.out.println(recordCount);
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List<Message> messages = null;
        messages = userDao.selectAllAdminMessage(params);
        return messages;
    }

    @Override
    public void removeMessageByMessageId(Integer message_id) {
        userDao.deleteMessageByUserId(message_id);
    }

    @Override
    public void saveMessage1(Integer userid, Integer result_id, Date date) {
        Map<String,Object> params = new HashMap<>();
        params.put("user_id", userid);
        params.put("result_id", "恭喜您！编号为："+result_id+"的成果已经被管理员设置为优秀成果！");
        params.put("date", date);
//        params.put("usertype", 1);
        userDao.insertMessage1(params);
    }

    @Override
    public void saveMessage2(Integer userid, Integer result_id, Integer result_trialstate, Date time) {
        Map<String,Object> params = new HashMap<>();
        params.put("user_id", userid);
        if (result_trialstate == 1){
            params.put("result_id", "您好！编号为："+result_id+"的成果已经被管理员驳回审核，请修改后重新提交！");
        }else {
            params.put("result_id", "您好！编号为："+result_id+"的成果已经被管理员通过审核！");
        }
        params.put("date", time);
//        params.put("usertype", 1);
        userDao.insertMessage2(params);
    }

    @Override
    public void saveMessageForSubmit(Date date) {
        Map<String,Object> params = new HashMap<>();
        params.put("content", "有用户提交成果，请审核！");
        params.put("date", date);
        userDao.insertMessageForSubmit(params);
    }

    @Override
    public void saveMessageForModify(Date date) {
        Map<String,Object> params = new HashMap<>();
        params.put("content", "有用户修改成果，请重新审核！");
        params.put("date", date);
        userDao.insertMessageForModify(params);
    }

    @Override
    public void removeUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void removeResultById(Integer id) {
        userDao.deleteResultById(id);
    }

    @Override
    public List<Notification> findAllNotification(PageModel pageModel) {
        /** 当前需要分页的总数据条数  */
        Map<String,Object> params = new HashMap<>();
        int recordCount = userDao.countAllNotification();
        System.out.println(recordCount);
        pageModel.setRecordCount(recordCount);
        if(recordCount > 0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List<Notification> notifications = null;
        notifications = userDao.selectAllNotification(params);
        return notifications;
    }

    @Override
    public void removeNotificationById(Integer id) {
        userDao.deleteNotificationById(id);
    }

    @Override
    public Notification findNotificationById(Integer rst_id) {
        return userDao.selectNotificationById(rst_id);
    }
}
