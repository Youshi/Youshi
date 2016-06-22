package com.example.hmqqg.hm.entity;

/**
 *
 * Created by iwalking11 on 2016/1/20.
 */
public class UserInfo {

    /**
     * UserId : OP10001
     * UserName : 管理员
     * DeptName : 研发组
     * Sex : 男
     * brthday : 1978-07-10T00:00:00
     * Mobile : 13051646064
     * duty : 副总经理
     */

    private String UserId;
    private String UserName;
    private String DeptName;
    private String Sex;
    private String brthday;
    private String Mobile;
    private String duty;

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public void setBrthday(String brthday) {
        this.brthday = brthday;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getDeptName() {
        return DeptName;
    }

    public String getSex() {
        return Sex;
    }

    public String getBrthday() {
        return brthday;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getDuty() {
        return duty;
    }
}
