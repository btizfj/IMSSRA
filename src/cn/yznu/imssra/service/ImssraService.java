package cn.yznu.imssra.service;

import cn.yznu.imssra.bean.*;
import cn.yznu.imssra.util.tag.PageModel;

import java.util.List;

public interface ImssraService {

    //通过密码账号和用户类型来查找用户
    public User findUserByUserNameAndPasswordAndType(String username, String password, String usertype);

    //修改用户个人信息（用户名，真实姓名，性别，电话号码，电子邮箱）
    void modifyUserInfo(int id, String username, String realname, String sex, String phonenumber, String email);

    //通过用户的id来查找用户
    User findUserById(int id);

    //添加成果到数据库
    void addResultToDB(Result result);

    //添加通知到数据库
    void addNotificationToDB(Notification notification);

    //根据用户id查询对应的用户所有成果
    List<Result> findResultListByUserid(Integer userid, PageModel pageModel);

    //按条件分页查询（用户）
    List<Result> findResultListByCondition(Integer userid, Integer year, Integer collegename, Integer trialstate, Integer typebig, Integer typesmall, PageModel pageModel);

    //按条件分页查询（管理员）
    List<Result> findResultListByCondition2(Integer year, Integer collegename, Integer trialstate, Integer typebig, Integer typesmall, PageModel pageModel);

    List<Result> findResultListByCondition3(Integer year, Integer collegename, Integer trialstate, Integer typebig, Integer typesmall);

    //按条件分页查询用户（管理员）
    List<User> findUserListByCondition(Integer role, Integer collegename, PageModel pageModel);

    //通过成果的id来查找用户
    Result findResultById(Integer result_id);

    //管理员查找所有成果
    List<Result> findAllResultList(PageModel pageModel);

    //管理员查找所有成果
    List<Result> findAllResultList();

    //管理员查找所有用户
    List<User> findAllUserList(PageModel pageModel);

    //管理员添加用户
    void addUser(String username, String password, Integer collegename, Integer role);

    //查询网站状态
    WebSatet findWebSatet();

    //更新网站状态
    void updateWebSatet(Integer state);

    List<Notification> find5InfoFromDB();

    List<Notification> find5NoteFromDB();

    List<Result> find5GoodResultFromDB();

    Notification findNotificationByIdAndType(Integer type, Integer id);

    void updateGoodStateById(Integer result_id);

    void updateResultTrialState(Integer result_id, Integer result_trialstate);

    void saveComment(String result_comment, Integer result_id);

    Comment findCommontByResultId(Integer result_id);

    void updateResult(Result result);
}
