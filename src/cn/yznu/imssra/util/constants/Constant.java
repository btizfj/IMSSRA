package cn.yznu.imssra.util.constants;

public class Constant {

    public static final String USERTABLE = "imssra_user";//用户表
    public static final String RESULTTABLE = "imssra_result";//成果表
    public static final String WEBSITESTATETABLE = "imssra_website_state";//网站状态表（关闭和打开）
    public static final String NOTIFICATIONTABLE = "imssra_note_and_info";//通知和信息表
    public static final String COMMENTTABLE = "imssra_comment";//审核评语表

    public static final int TYPE_STUDENT = 0;//学生类型
    public static final int TYPE_TEACHER = 1;//老师类型
    public static final int TYPE_ADMIN = 2;//管理员

    //学院及其编号                              0           1           2           3        4          5           6           7               8
    public static final String colleges[] = {"文学院","传媒学院","计算机学院","财经学院","管理学院","美术学院","体育学院","化工化学学院","外国语学院"};

    //每一页显示的最大条目数量
    public static final int PAGE_DEFAULT_SIZE = 15;

    //是否为优秀项目
    public static final String isGoodResule[] = {"否","是"};
    //通知类型
    public static final String resultType[] = {"通知公告","公开信息"};
    //用户类型
    public static final String userType[] = {"普通","普通","管理员"};

    //审核状态
    public static final String trail_state[] = {"正在审核","审核失败","审核通过"};
    //成果大类                                      1              2             3
    public static final String type_big[] = {"基础理论成果","应用技术成果","软科学成果"};
    //成果小类                                     1(1)        1(2)         1(3)           1(4)               2(5)                 2(6)           3(7)       3(8)     4(9)
    public static final String type_small[] = {"论文和专著","发明专利","实用新型专利","外观设计专利","自主研发的新产品原型","自主开发的新技术","基础软件","应用软件","其他"};
}
