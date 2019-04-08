package cn.yznu.imssra.service.impl;

import cn.yznu.imssra.bean.*;
import cn.yznu.imssra.dao.UserDao;
import cn.yznu.imssra.service.ImssraService;
import cn.yznu.imssra.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public Comment findCommontByResultId(Integer result_id) {
        return userDao.selectCommontByResultId(result_id);
    }

    @Override
    public void updateResult(Result result) {
        userDao.updateResult(result);
    }
}
