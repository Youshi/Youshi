package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by iwalking11 on 2016/1/25.
 */
public class DeptDetailEntity {
    /**
     * DeptCode : 1005
     * ParnetCode : 1004
     * DeptName : 研发组
     * ShowXh : 1
     * HumanNum : 3
     * Phone : 88611094,88611860
     * Tax : 12345678
     * Address : 1230
     * DirectorId : OP10009
     * DirectorName : 黄建秒
     * MainLeadId : OP10001,OP10003
     * MainLeadName : 杨秀东,杨宁骐
     * FgLeadId : OP10001
     * FgLeadName : 杨秀东
     * SonCount : 0
     */

    private DetailInfoEntity DetailInfo;
    /**
     * stamess : 部门信息请求成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * UserId : OP10009
     * UserName : 黄建秒
     * DeptName : 研发组
     * Sex : 女
     * brthday : 2009-07-06T16:43:59.453
     * Mobile :
     * email :
     * duty : null
     * qq :
     * Wx : null
     * Address :
     * headimg : /images/noPic.gif
     */

    private List<LeadersEntity> Leaders;
    /**
     * userid : OP10001
     * username : 管理员
     * sex : 男
     * birthday : 1978-07-10T00:00:00
     * email : 956472024@qq.com
     * address : 烟台市牟平区
     * mobile : 15166866385
     * qq : 956472024
     * headimg :
     * duty : 副总经理
     */

    private List<StaffersEntity> Staffers;

    public void setDetailInfo(DetailInfoEntity DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public void setLeaders(List<LeadersEntity> Leaders) {
        this.Leaders = Leaders;
    }

    public void setStaffers(List<StaffersEntity> Staffers) {
        this.Staffers = Staffers;
    }

    public DetailInfoEntity getDetailInfo() {
        return DetailInfo;
    }

    public List<StatusEntity> getStatus() {
        return status;
    }

    public List<LeadersEntity> getLeaders() {
        return Leaders;
    }

    public List<StaffersEntity> getStaffers() {
        return Staffers;
    }

    public static class DetailInfoEntity {
        private String DeptCode;
        private String ParnetCode;
        private String DeptName;
        private int ShowXh;
        private int HumanNum;
        private String Phone;
        private String Tax;
        private String Address;
        private String DirectorId;
        private String DirectorName;
        private String MainLeadId;
        private String MainLeadName;
        private String FgLeadId;
        private String FgLeadName;
        private int SonCount;

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public void setParnetCode(String ParnetCode) {
            this.ParnetCode = ParnetCode;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public void setShowXh(int ShowXh) {
            this.ShowXh = ShowXh;
        }

        public void setHumanNum(int HumanNum) {
            this.HumanNum = HumanNum;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public void setTax(String Tax) {
            this.Tax = Tax;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public void setDirectorId(String DirectorId) {
            this.DirectorId = DirectorId;
        }

        public void setDirectorName(String DirectorName) {
            this.DirectorName = DirectorName;
        }

        public void setMainLeadId(String MainLeadId) {
            this.MainLeadId = MainLeadId;
        }

        public void setMainLeadName(String MainLeadName) {
            this.MainLeadName = MainLeadName;
        }

        public void setFgLeadId(String FgLeadId) {
            this.FgLeadId = FgLeadId;
        }

        public void setFgLeadName(String FgLeadName) {
            this.FgLeadName = FgLeadName;
        }

        public void setSonCount(int SonCount) {
            this.SonCount = SonCount;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public String getParnetCode() {
            return ParnetCode;
        }

        public String getDeptName() {
            return DeptName;
        }

        public int getShowXh() {
            return ShowXh;
        }

        public int getHumanNum() {
            return HumanNum;
        }

        public String getPhone() {
            return Phone;
        }

        public String getTax() {
            return Tax;
        }

        public String getAddress() {
            return Address;
        }

        public String getDirectorId() {
            return DirectorId;
        }

        public String getDirectorName() {
            return DirectorName;
        }

        public String getMainLeadId() {
            return MainLeadId;
        }

        public String getMainLeadName() {
            return MainLeadName;
        }

        public String getFgLeadId() {
            return FgLeadId;
        }

        public String getFgLeadName() {
            return FgLeadName;
        }

        public int getSonCount() {
            return SonCount;
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

    public static class LeadersEntity {
        private String UserId;
        private String UserName;
        private String DeptName;
        private String Sex;
        private String brthday;
        private String Mobile;
        private String email;
        private Object duty;
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

        public void setDuty(Object duty) {
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

        public Object getDuty() {
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

    public static class StaffersEntity {
        private String userid;
        private String username;
        private String sex;
        private String birthday;
        private String email;
        private String address;
        private String mobile;
        private String qq;
        private String headimg;
        private String duty;

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public String getUserid() {
            return userid;
        }

        public String getUsername() {
            return username;
        }

        public String getSex() {
            return sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }

        public String getMobile() {
            return mobile;
        }

        public String getQq() {
            return qq;
        }

        public String getHeadimg() {
            return headimg;
        }

        public String getDuty() {
            return duty;
        }
    }
}
