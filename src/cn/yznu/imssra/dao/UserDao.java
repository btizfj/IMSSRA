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

//    @Insert("INSERT INTO "+ RESULTTABLE +"(resultname, collegename, typebig, typesmall,desc,detail,instruction,filename,trailstate,time) VALUES(#{resultname}, #{collegename}, #{typebig}, #{typesmall}, #{desc}, #{detail}, #{instruction}, #{filename}, #{trailstate}, #{time})")
//    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
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

//    @Insert("INSERT INTO "+ USERTABLE +"(username,password,usertype,collegeName) VALUES(#{username},#{password},#{role}, #{collegename})")
//    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
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

//    @Select("select * from " + NOTIFICATIONTABLE + " where type=#{type} and id=#{id}")
    @SelectProvider(type=UserDynamicSqlProvider.class,method="selectNotificationByIdAndType")
    Notification selectNotificationByIdAndType(Integer type, Integer id);

    @Select("update " + RESULTTABLE + " set isgood=1 where id=#{result_id}")
    void updateGoodState(Integer result_id);

//    @Select("update " + RESULTTABLE + " set trailstate=#{result_trialstate} where id=#{result_id}")
    @SelectProvider(type=UserDynamicSqlProvider.class,method="updateTrialState")
    void updateTrialState(Integer result_id, Integer result_trialstate);

//    @Insert("INSERT INTO "+ RESULTTABLE +"(result_id, comment) VALUES(#{result_id}, #{result_comment})")
//    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
    @SelectProvider(type=UserDynamicSqlProvider.class,method="saveResultComment")
    void saveResultComment(String result_comment, Integer result_id);

    @Select("select * from " + COMMENTTABLE + " where result_id=#{result_id}")
    Comment selectCommontByResultId(Integer result_id);

    @UpdateProvider(type= UserDynamicSqlProvider.class,method="updateResultToDB")
    void updateResult(Result result);
}
