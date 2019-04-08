package cn.yznu.imssra.bean;

import java.sql.Date;

/**
 * 用户实体类
 */
public class Result implements java.io.Serializable {

    private int id;//主键（编号）
    private String resultname;//成果名称
    private Integer collegename;//所属学院编码
    private Integer typebig;//所属大类别
    private Integer typesmall;//所属小类别
    private String description;//简介
    private String detail;//详细介绍（html）
    private String instruction;//成果说明
    private String filename;//上传的文件名称
    private Integer trailstate;//审核状态码
    private Integer userid;//哪个用户的成果
    private Integer isgood;//哪个用户的成果

    public Integer getIsgood() {
        return isgood;
    }

    public void setIsgood(Integer isgood) {
        this.isgood = isgood;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResultname() {
        return resultname;
    }

    public void setResultname(String resultname) {
        this.resultname = resultname;
    }

    public Integer getCollegename() {
        return collegename;
    }

    public void setCollegename(Integer collegename) {
        this.collegename = collegename;
    }

    public Integer getTypebig() {
        return typebig;
    }

    public void setTypebig(Integer typebig) {
        this.typebig = typebig;
    }

    public Integer getTypesmall() {
        return typesmall;
    }

    public void setTypesmall(Integer typesmall) {
        this.typesmall = typesmall;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getTrailstate() {
        return trailstate;
    }

    public void setTrailstate(Integer trailstate) {
        this.trailstate = trailstate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    //提供无参构造方法
    public Result() {
    }

}
