package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 登录实体类
 * Created by Administrator on 2016/1/20.
 */
public class LoginEntity {


    /**
     * UserId : OP10001
     * LoginId : admin
     * Password : 25D55AD283AA400AF464C76D713C07AD
     * UserName : 管理员
     * Enabled : true
     * UserType : 0
     * DeptCode : 1044
     * Sex : 男
     * LockIp : 192.168.0.198
     * IsUsedIp : true
     * UserState : false
     * SiteId :
     * UpdatePWDDate : 0001-01-01T00:00:00
     */

    private DetailInfoEntity DetailInfo;
    /**
     * status : [{"stamess":"登陆成功！","staval":"1"}]
     * DetailInfo : {"UserId":"OP10001","LoginId":"admin","Password":"25D55AD283AA400AF464C76D713C07AD","UserName":"管理员","Enabled":true,"UserType":0,"DeptCode":"1044","Sex":"男","LockIp":"192.168.0.198","IsUsedIp":true,"UserState":false,"SiteId":"","UpdatePWDDate":"0001-01-01T00:00:00"}
     * Wakeup : true
     * Remindset : true
     */

    private boolean Wakeup;
    private boolean Remindset;
    /**
     * stamess : 登陆成功！
     * staval : 1
     */

    private List<StatusEntity> status;

    public void setDetailInfo(DetailInfoEntity DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public void setWakeup(boolean Wakeup) {
        this.Wakeup = Wakeup;
    }

    public void setRemindset(boolean Remindset) {
        this.Remindset = Remindset;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public DetailInfoEntity getDetailInfo() {
        return DetailInfo;
    }

    public boolean isWakeup() {
        return Wakeup;
    }

    public boolean isRemindset() {
        return Remindset;
    }

    public List<StatusEntity> getStatus() {
        return status;
    }

    public static class DetailInfoEntity {
        private String UserId;
        private String LoginId;
        private String Password;
        private String UserName;
        private boolean Enabled;
        private int UserType;
        private String DeptCode;
        private String Sex;
        private String LockIp;
        private boolean IsUsedIp;
        private boolean UserState;
        private String SiteId;
        private String UpdatePWDDate;

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setLoginId(String LoginId) {
            this.LoginId = LoginId;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setEnabled(boolean Enabled) {
            this.Enabled = Enabled;
        }

        public void setUserType(int UserType) {
            this.UserType = UserType;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public void setLockIp(String LockIp) {
            this.LockIp = LockIp;
        }

        public void setIsUsedIp(boolean IsUsedIp) {
            this.IsUsedIp = IsUsedIp;
        }

        public void setUserState(boolean UserState) {
            this.UserState = UserState;
        }

        public void setSiteId(String SiteId) {
            this.SiteId = SiteId;
        }

        public void setUpdatePWDDate(String UpdatePWDDate) {
            this.UpdatePWDDate = UpdatePWDDate;
        }

        public String getUserId() {
            return UserId;
        }

        public String getLoginId() {
            return LoginId;
        }

        public String getPassword() {
            return Password;
        }

        public String getUserName() {
            return UserName;
        }

        public boolean isEnabled() {
            return Enabled;
        }

        public int getUserType() {
            return UserType;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public String getSex() {
            return Sex;
        }

        public String getLockIp() {
            return LockIp;
        }

        public boolean isIsUsedIp() {
            return IsUsedIp;
        }

        public boolean isUserState() {
            return UserState;
        }

        public String getSiteId() {
            return SiteId;
        }

        public String getUpdatePWDDate() {
            return UpdatePWDDate;
        }
    }

    public static class StatusEntity {
        private String stamess;
        private String staval;

        public void setStamess(String stamess) {
            this.stamess = stamess;
        }

        public void setStaval(String staval) {
            this.staval = staval;
        }

        public String getStamess() {
            return stamess;
        }

        public String getStaval() {
            return staval;
        }
    }
}
