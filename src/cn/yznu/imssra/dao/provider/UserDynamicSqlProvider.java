package cn.yznu.imssra.dao.provider;


import cn.yznu.imssra.bean.Notification;
import cn.yznu.imssra.bean.Result;
import cn.yznu.imssra.util.MyUtil;
import org.apache.ibatis.jdbc.SQL;

import java.sql.Date;
import java.util.Map;

import static cn.yznu.imssra.util.constants.Constant.*;

public class UserDynamicSqlProvider {

    public String selectAllNotification(Map<String, Object> params){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(NOTIFICATIONTABLE);
            }
        }.toString();
        sql += " order by time desc ";
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
        }
        return sql;
    }

    public String selectAllAdminMessage(Map<String, Object> params){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(MESSAGETABEL);
                WHERE(" usertype=1 ");
            }
        }.toString();
        sql += " order by time desc ";
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
        }
        return sql;
    }

    public String selectAllMessageByIdOfUser(Map<String, Object> params){
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(MESSAGETABEL);
                WHERE(" user_id=#{user_id} ");
            }
        }.toString();
        sql += " order by time desc ";
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
        }
        return sql;
    }

    public String updateCommentById(String result_comment, Integer result_id){
        String SQL = "update " + COMMENTTABLE +" set comment='"+result_comment+"' where result_id="+result_id;
        System.out.println(SQL);
        return SQL;
    }

    public String selectResultBySearch(Integer rst_number,String rst_name){
        String SQL = null;
        switch (rst_number){
            case 0:
                SQL = "select * from " + RESULTTABLE +" where id="+rst_name;
                break;
            case 1:
                SQL = "select * from " + RESULTTABLE +" where resultname LIKE '%"+rst_name+"%'";
                break;
        }
        System.out.println(SQL);
        return SQL;
    }

    public String saveResultComment(String result_comment, Integer result_id){
        String SQL = "INSERT INTO "+ COMMENTTABLE +"(result_id, comment) VALUES("+result_id+", '"+result_comment+"')";
        System.out.println(SQL);
        return SQL;
    }

    public String updateTrialState(Integer result_id, Integer result_trialstate){
        String SQL = "update " + RESULTTABLE + " set trailstate="+result_trialstate+" where id="+result_id;
        System.out.println(SQL);
        return SQL;
    }

    public String selectNotificationByIdAndType(Integer type, Integer id){
        String SQL = "select * from " + NOTIFICATIONTABLE + " where type="+type+" and id="+id;
        System.out.println(SQL);
        return SQL;
    }

    public String insertUser(String username, String password, Integer collegename, Integer role){
        String SQL = "insert into " + USERTABLE+"(username,password,usertype,collegeName) values ('" + username+"','"+password+"',"+role+","+collegename+")";
        System.out.println(SQL);
        return SQL;
    }

    public String updateUserInfoById(int id, String username, String realname, String sex, String phonenumber, String email){
        String SQL = "update " + USERTABLE+" set username='"+username+"',realname='"+realname+
                "',sex='"+sex+"',phonenumber='"+phonenumber+"',email='"+email+"' where id="+id;
        System.out.println(SQL);
        return SQL;
    }

    public String insertResultToDB(Result result){
        String resultname = result.getResultname();
        Integer collegename = result.getCollegename();
        String desc = result.getDescription();
        String detail = result.getDetail();
        String filename = result.getFilename();
        String instruction = result.getInstruction();
        Integer typebig = result.getTypebig();
        Integer typesmall = result.getTypesmall();
        Integer trailstate = result.getTrailstate();
        Integer userid = result.getUserid();
        Date time = result.getTime();

        String SQL = "INSERT INTO "+ RESULTTABLE + "(resultname, collegename, description, detail, filename, instruction, typebig, typesmall, trailstate, userid, time,isgood) " + "VALUES("+ MyUtil.addMsg(resultname)+","+collegename+","+MyUtil.addMsg(desc) +","+MyUtil.addMsg(detail)+","+MyUtil.addMsg(filename)+","+MyUtil.addMsg(instruction)+","+typebig+","+typesmall+","+trailstate+","+userid+","+MyUtil.addMsg(time+"")+","+"0"+")";
        System.out.println(SQL);
        return SQL;
    }

    public String updateResultToDB(Result result){
        Integer id = result.getId();
        String resultname = result.getResultname();
        Integer collegename = result.getCollegename();
        String desc = result.getDescription();
        String detail = result.getDetail();
        String instruction = result.getInstruction();
        Integer typebig = result.getTypebig();
        Integer typesmall = result.getTypesmall();
        Integer trailstate = result.getTrailstate();

        String SQL = "UPDATE "+ RESULTTABLE +
                " SET resultname="+MyUtil.addMsg(resultname)
                +", collegename="+collegename+
                ", description="+MyUtil.addMsg(desc)+
                ", detail="+MyUtil.addMsg(detail)+
                ", instruction="+MyUtil.addMsg(instruction)+
                ", typebig="+typebig+
                ", typesmall="+typesmall+
                ", trailstate="+trailstate+" where id="+id;
        System.out.println(SQL);
        return SQL;
    }

    public String insertNotificationToDB(Notification notification){
        String title = notification.getTitle();
        Integer type = notification.getType();
        String content = notification.getContent();
        String filename = notification.getFilename();
        Date time = notification.getTime();

        String SQL = "INSERT INTO "+ NOTIFICATIONTABLE + "(type, title, content, filename, time) " + "VALUES("+ type+","+MyUtil.addMsg(title)+","+MyUtil.addMsg(content) +","+MyUtil.addMsg(filename)+","+MyUtil.addMsg(time+"")+")";
        System.out.println(SQL);
        return SQL;
    }

    public String selectResultsByUserid(Map<String, Object> params) {
        int userid = (int) params.get("userid");
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(RESULTTABLE);
                WHERE(" userid="+userid);
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        System.out.println(sql.toString());
        return sql.toString();
    }

    public String selectAllNotificationByPageAndType(Map<String, Object> params) {
        int n_type = (int) params.get("n_type");
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(NOTIFICATIONTABLE);
                WHERE(" type="+n_type);
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        System.out.println(sql.toString());
        return sql.toString();
    }

    public String selectAllGoodResultByPageAndType(Map<String, Object> params) {
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(RESULTTABLE);
                WHERE(" isgood="+1);
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        System.out.println(sql.toString());
        return sql.toString();
    }

    public String selectAllResults(Map<String, Object> params) {
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(RESULTTABLE);
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        System.out.println(sql.toString());
        return sql.toString();
    }

    public String selectAllUsers(Map<String, Object> params) {
        String sql =  new SQL(){
            {
                SELECT("*");
                FROM(USERTABLE);
            }
        }.toString();
        if(params.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }
        System.out.println(sql.toString());
        return sql.toString();
    }

    public String countResultsByCondition(Map<String, Object> params){
        boolean isAdd = false;//是否已经添加了where
        boolean hasPre = false;//是否前面有一个条件
        int userid = (int) params.get("userid");
        int year = (int) params.get("year");
        int collegename = (int) params.get("collegename");
        int trialstate = (int) params.get("trialstate");
        int typebig = (int) params.get("typebig");
        int typesmall = (int) params.get("typesmall");
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from " + RESULTTABLE);
        if (year != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" YEAR(time)= "+year);
        }
        if (collegename != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" collegename="+collegename);
        }
        if (trialstate != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" trailstate="+trialstate);
        }
        if (typebig != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typebig="+typebig);
        }
        if (typesmall != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typesmall="+typesmall);
        }
        if (!isAdd){
            isAdd = true;
            sb.append(" where ");
        }
        if (!hasPre){
            hasPre = true;
        }else {
            sb.append(" and ");
        }
        sb.append(" userid="+userid);
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String countResultsByCondition2(Map<String, Object> params){
        boolean isAdd = false;//是否已经添加了where
        boolean hasPre = false;//是否前面有一个条件
        int year = (int) params.get("year");
        int collegename = (int) params.get("collegename");
        int trialstate = (int) params.get("trialstate");
        int typebig = (int) params.get("typebig");
        int typesmall = (int) params.get("typesmall");
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from " + RESULTTABLE);
        if (year != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" YEAR(time)= "+year);
        }
        if (collegename != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" collegename="+collegename);
        }
        if (trialstate != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" trailstate="+trialstate);
        }
        if (typebig != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typebig="+typebig);
        }
        if (typesmall != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typesmall="+typesmall);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String countUsersByCondition(Map<String, Object> params){
        boolean isAdd = false;//是否已经添加了where
        boolean hasPre = false;//是否前面有一个条件
        int role = (int) params.get("role");
        int collegename = (int) params.get("collegename");
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from " + USERTABLE);
        if (role != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" YEAR(time)= "+role);
        }
        if (collegename != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" collegename="+collegename);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String selectResultsByCondition(Map<String, Object> params){
        boolean isAdd = false;//是否已经添加了where
        boolean hasPre = false;//是否前面有一个条件
        int userid = (int) params.get("userid");
        int year = (int) params.get("year");
        int collegename = (int) params.get("collegename");
        int trialstate = (int) params.get("trialstate");
        int typebig = (int) params.get("typebig");
        int typesmall = (int) params.get("typesmall");
        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + RESULTTABLE);
        if (year != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" YEAR(time)= "+year);
        }
        if (collegename != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" collegename="+collegename);
        }
        if (trialstate != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" trailstate="+trialstate);
        }
        if (typebig != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typebig="+typebig);
        }
        if (typesmall != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typesmall="+typesmall);
        }
        if (!isAdd){
            isAdd = true;
            sb.append(" where ");
        }
        if (!hasPre){
            hasPre = true;
        }else {
            sb.append(" and ");
        }
        sb.append(" userid="+userid);
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String selectResultsByCondition3(Map<String, Object> params){
        boolean isAdd = false;//是否已经添加了where
        boolean hasPre = false;//是否前面有一个条件
        int year = (int) params.get("year");
        int collegename = (int) params.get("collegename");
        int trialstate = (int) params.get("trialstate");
        int typebig = (int) params.get("typebig");
        int typesmall = (int) params.get("typesmall");
        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + RESULTTABLE);
        if (year != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" YEAR(time)= "+year);
        }
        if (collegename != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" collegename="+collegename);
        }
        if (trialstate != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" trailstate="+trialstate);
        }
        if (typebig != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typebig="+typebig);
        }
        if (typesmall != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typesmall="+typesmall);
        }
        if (!isAdd){
            isAdd = true;
            sb.append(" where ");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String selectResultsByCondition2(Map<String, Object> params){
        boolean isAdd = false;//是否已经添加了where
        boolean hasPre = false;//是否前面有一个条件
        int year = (int) params.get("year");
        int collegename = (int) params.get("collegename");
        int trialstate = (int) params.get("trialstate");
        int typebig = (int) params.get("typebig");
        int typesmall = (int) params.get("typesmall");
        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + RESULTTABLE);
        if (year != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" YEAR(time)= "+year);
        }
        if (collegename != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" collegename="+collegename);
        }
        if (trialstate != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" trailstate="+trialstate);
        }
        if (typebig != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typebig="+typebig);
        }
        if (typesmall != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" typesmall="+typesmall);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String selectUsersByCondition(Map<String, Object> params){
        boolean isAdd = false;//是否已经添加了where
        boolean hasPre = false;//是否前面有一个条件
        int role = (int) params.get("role");
        int collegename = (int) params.get("collegename");
        StringBuilder sb = new StringBuilder();
        sb.append("select * from " + USERTABLE);
        if (role != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" YEAR(time)= "+role);
        }
        if (collegename != -1){
            if (!isAdd){
                isAdd = true;
                sb.append(" where ");
            }
            if (!hasPre){
                hasPre = true;
            }else {
                sb.append(" and ");
            }
            sb.append(" collegename="+collegename);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

}
