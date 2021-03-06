package cn.yznu.imssra.util;

import cn.yznu.imssra.bean.User;
import cn.yznu.imssra.util.tag.PageModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static cn.yznu.imssra.util.constants.Constant.*;

public class MyUtil {

    public static String getPage(User user) {
        String str = null;
        if (user.getUsertype() == TYPE_ADMIN){
            str = "adminMain";
        }else {
            str = "userMainByType";
        }
        return str;
    }

    /**
     * 处理msg里的特殊字符
     * @param msg
     * @return
     */
    public static String parsMsg(String msg){
        String result = null;
//        //去掉“\t”
//        result = msg.replace("\\t","    ");
//        //去掉“\n”
//        result = result.replace("\\n","<br/>");
        //替换“\"”为“"”
        result = msg.replace("\\\"","\"");
        return result.substring(1,result.length()-1);
    }

    /**
     * 给msg的前后加上'
     * @param msg
     * @return
     */
    public static String addMsg(String msg){
        return "'"+msg+"'";
    }

    public static PageModel makePageModel(Integer pageIndex){
        // 创建分页对象
        PageModel pageModel = new PageModel();
        // 如果参数pageIndex不为null，设置pageIndex，即显示第几页
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        return pageModel;
    }

}
