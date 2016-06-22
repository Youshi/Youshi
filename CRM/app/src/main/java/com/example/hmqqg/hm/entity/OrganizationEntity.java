package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/23.
 */
public class OrganizationEntity {


    /**
     * stamess : 列表请求成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * DeptCode : 1002
     * ParnetCode : 1001
     * DeptName : 售后服务
     * ShowXh : 1
     * HumanNum : 1
     * Phone :
     * Tax :
     * Address :
     * DirectorId :
     * DirectorName :
     * MainLeadId :
     * MainLeadName :
     * FgLeadId :
     * FgLeadName :
     * allHuman : 1
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
        private int allHuman;

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

        public void setAllHuman(int allHuman) {
            this.allHuman = allHuman;
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

        public int getAllHuman() {
            return allHuman;
        }
    }
}
