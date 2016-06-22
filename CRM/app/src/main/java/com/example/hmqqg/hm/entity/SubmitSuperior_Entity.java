package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 获取审核人列表sipnner的容器
 * Created by Administrator on 2016/5/9.
 */
public class SubmitSuperior_Entity {

    /**
     * stamess : 获取审批人信息成功，请假如下！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * userid : OP10071
     * UserName : 张宪峰
     */

    private List<DetailInfoEntity> DetailInfo;

    public List<StatusEntity> getStatus() {
        return status;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public List<DetailInfoEntity> getDetailInfo() {
        return DetailInfo;
    }

    public void setDetailInfo(List<DetailInfoEntity> DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public static class StatusEntity {
        private String stamess;
        private String staval;

        public String getStamess() {
            return stamess;
        }

        public void setStamess(String stamess) {
            this.stamess = stamess;
        }

        public String getStaval() {
            return staval;
        }

        public void setStaval(String staval) {
            this.staval = staval;
        }
    }

    public static class DetailInfoEntity {
        private String userid;
        private String UserName;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }
    }
}
