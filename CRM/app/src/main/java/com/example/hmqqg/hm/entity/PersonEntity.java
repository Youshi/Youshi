package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 个人信息查询实体类
 * Created by Android-Wq on 2016/1/21.
 */
public class PersonEntity {


    /**
     * stamess : 数据获取成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * UserId : OP10001
     * UserName : 管理员
     * DeptName : 研发组
     * Sex : 男
     * brthday : 1978-07-10T00:00:00
     * Mobile : 13051646064
     * email : yxd_hangar@126.com
     * duty : 副总经理
     * qq : 284590656
     * Wx : null
     * Address : 张家口
     * headimg : /UploadFile/photo/OP10001.jpg
     */

    private List<DetailInfoEntity> DetailInfo;

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public void setDetailInfo(List<DetailInfoEntity> DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public List<StatusEntity> getStatus() {
        return status;
    }

    public List<DetailInfoEntity> getDetailInfo() {
        return DetailInfo;
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

    public static class DetailInfoEntity {
        private String UserId;
        private String UserName;
        private String DeptName;
        private String Sex;
        private String brthday;
        private String Mobile;
        private String email;
        private String duty;
        private String qq;
        private Object Wx;
        private String Address;
        private String headimg;

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

        public void setEmail(String email) {
            this.email = email;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setWx(Object Wx) {
            this.Wx = Wx;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
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

        public String getEmail() {
            return email;
        }

        public String getDuty() {
            return duty;
        }

        public String getQq() {
            return qq;
        }

        public Object getWx() {
            return Wx;
        }

        public String getAddress() {
            return Address;
        }

        public String getHeadimg() {
            return headimg;
        }
    }
}
