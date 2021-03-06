package cn.yznu.imssra.dao;

import cn.yznu.imssra.bean.*;
import cn.yznu.imssra.bean.Result;
import cn.yznu.imssra.dao.provider.UserDynamicSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import static cn.yznu.imssra.util.constants.Constant.*;

public interface UserDao {

    //通过密码账号和用户类型来查找用户
    @Select("select * from " + USERTABLE + " where username = #{username} and password = #{password} and usertype = #{usertype}")
    User selectUserByUserNameAndPasswordAndType(@Param("username") String username, @Param("password") String password, @Param("usertype")String usertype);

    @UpdateProvider(type= UserDynamicSqlProvider.class,method="updateUserInfoById")
    void updateUserInfo(int id, String username, String realname, String sex, String phonenumber, String email);

    @Select("select * from " + USERTABLE + " where id=#{id}")
    User selectUserById(int id);

    @UpdateProvider(type= UserDynamicSqlProvider.class,method="insertResultToDB")
    void insertResultToDB(Result result);

    @UpdateProvider(type= UserDynamicSqlProvider.class,method="insertNotificationToDB")
    void insertNotificationToDB(Notification notification);

    //查询userid的成果数量
    @Select("select count(*) from " + RESULTTABLE + " where userid=#{userid}")
    int countResultByUserid(Integer userid);

    //查询user的成果数量
    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectResultsByUserid")
    List<Result> selectResultsByParam(Map<String, Object> params);

    //根据条件查询成果数量
    @SelectProvider(type=UserDynamicSqlProvider.class,method="countResultsByCondition")
    int countResultByCondition(Map<String, Object> params);

    //根据条件查询成果数量（管理员）
    @SelectProvider(type=UserDynamicSqlProvider.class,method="countResultsByCondition2")
    int countResultByCondition2(Map<String, Object> params);

    //根据条件查询用户数量（管理员）
    @SelectProvider(type=UserDynamicSqlProvider.class,method="countUsersByCondition")
    int countUserByCondition(Map<String, Object> params);

    //根据条件查询所有成果
    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectResultsByCondition")
    List<Result> selectResultsByCondition(Map<String, Object> params);

    //根据条件查询所有成果（管理员）
    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectResultsByCondition2")
    List<Result> selectResultsByCondition2(Map<String, Object> params);

    //根据条件查询所有成果（管理员）
    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectResultsByCondition3")
    List<Result> selectResultsByCondition3(Map<String, Object> params);

    //根据条件查询所有用户（管理员）
    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectUsersByCondition")
    List<User> selectUsersByCondition(Map<String, Object> params);

    @Select("select * from " + RESULTTABLE + " where id=#{id}")
    Result selectResultById(Integer result_id);

    @Select("select count(*) from " + RESULTTABLE )
    int countAllResult();

    @Select("select count(*) from " + USERTABLE )
    int countAllUser();

    //分页查询系统里所有的成果（管理员）
    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectAllResults")
    List<Result> selectAllResults(Map<String, Object> params);

    //分页查询系统里所有的成果（管理员）
    @Select("select * from " + RESULTTABLE)
    List<Result> selectAllResult();

    //分页查询系统里所有的用户（管理员）
    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectAllUsers")
    List<User> selectAllUsers(Map<String, Object> params);

    @SelectProvider(type=UserDynamicSqlProvider.class,method="insertUser")
    void insertUser(String username, String password, Integer collegename, Integer role);

    @Select("select * from " + WEBSITESTATETABLE)
    WebSatet selectWebSatet();

    //更新网站状态
    @Update("update " + WEBSITESTATETABLE +" set isopen=#{state} where id=1")
    void updateWebSatet(Integer state);

    @Select("select * from " + NOTIFICATIONTABLE + " where type=1 limit 5")
    List<Notification> select5Info();

    @Select("select * from " + NOTIFICATIONTABLE + " where type=0 limit 5")
    List<Notification> select5Note();

    @Select("select * from " + RESULTTABLE + " where isgood=1 limit 5")
    List<Result> select5GoodResult();

    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectNotificationByIdAndType")
    Notification selectNotificationByIdAndType(Integer type, Integer id);

    @Select("update " + RESULTTABLE + " set isgood=1 where id=#{result_id}")
    void updateGoodState(Integer result_id);

    @SelectProvider(type=UserDynamicSqlProvider.class,method="updateTrialState")
    void updateTrialState(Integer result_id, Integer result_trialstate);

    @SelectProvider(type=UserDynamicSqlProvider.class,method="saveResultComment")
    void saveResultComment(String result_comment, Integer result_id);

    @Select("select * from " + COMMENTTABLE + " where result_id=#{result_id}")
    Comment selectCommontByResultId(Integer result_id);

    @UpdateProvider(type= UserDynamicSqlProvider.class,method="updateResultToDB")
    void updateResult(Result result);

    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectResultBySearch")
    List<Result> selectResultBySearch(Integer rst_number,String rst_name);

    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectAllNotificationByPageAndType")
    List<Notification> selectAllNotificationByPageAndType(Map<String,Object> params);

    @Select("select count(*) from " + NOTIFICATIONTABLE + " where type=#{n_type}")
    int countAllNotificationByPageAndType(Integer n_type);

    @Select("select count(*) from " + RESULTTABLE + " where isgood=1")
    int countAllGoodResultByPage();

    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectAllGoodResultByPageAndType")
    List<Result> selectAllGoodResultByPageAndType(Map<String, Object> params);

    @SelectProvider(type=UserDynamicSqlProvider.class,method="updateCommentById")
    void updateCommentById(String result_comment, Integer result_id);

    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectAllMessageByIdOfUser")
    List<Message> selectAllMessageByUserId(Map<String, Object> params);

    @Delete("delete from " + MESSAGETABEL + " where id=#{message_id}")
    void deleteMessageByUserId(Integer message_id);

    @Select("select count(*) from " + MESSAGETABEL + " where user_id=#{userid}")
    int countAllMessageByUserId(Integer userid);

    @Insert("INSERT INTO "+ MESSAGETABEL +"(user_id, content, time) VALUES(#{user_id}, #{result_id}, #{date})")
    void insertMessage1(Map<String,Object> params);

    @Insert("INSERT INTO "+ MESSAGETABEL +"(user_id, content, time) VALUES(#{user_id}, #{result_id}, #{date})")
    void insertMessage2(Map<String,Object> params);

    @Select("select count(*) from " + MESSAGETABEL + " where usertype=1")
    int countAllAdminMessage();

    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectAllAdminMessage")
    List<Message> selectAllAdminMessage(Map<String, Object> params);

    @Insert("INSERT INTO "+ MESSAGETABEL +"(usertype, content, time) VALUES(1,#{content}, #{date})")
    void insertMessageForSubmit(Map<String,Object> params);

    @Insert("INSERT INTO "+ MESSAGETABEL +"(usertype, content, time) VALUES(1,#{content}, #{date})")
    void insertMessageForModify(Map<String,Object> params);

    @Delete("delete from " + USERTABLE + " where id=#{id}")
    void deleteUserById(Integer id);

    @Delete("delete from " + RESULTTABLE + " where id=#{id}")
    void deleteResultById(Integer id);

    @Select("select count(*) from " + NOTIFICATIONTABLE )
    int countAllNotification();

    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectAllNotification")
    List<Notification> selectAllNotification(Map<String, Object> params);

    @Delete("delete from " + NOTIFICATIONTABLE + " where id=#{id}")
    void deleteNotificationById(Integer id);

    @Select("select * from " + NOTIFICATIONTABLE + " where id=#{rst_id}")
    Notification selectNotificationById(Integer rst_id);
}
