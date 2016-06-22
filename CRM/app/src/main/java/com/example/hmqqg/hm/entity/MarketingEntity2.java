package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class MarketingEntity2 {

    /**
     * stamess : 投资信息请求成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * ActId : 5
     * ActTitle : 促销活动
     * ActType : HDLX08
     * ActTypeName : 其他活动
     * FzUserId : op10001
     * FzUserName :
     * Charge : 10000.0
     * ActAddress : 烟台市
     * ActDate : 2016-11-11T00:00:00
     * ActPlan : 活动计划
     * ZCQK :
     * ZDZJ :
     * XGPG :
     * Remark :
     * ApplyId : null
     * ApplyName : null
     * State : 待审批
     * ApplyMess :
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
        private int ActId;
        private String ActTitle;
        private String ActType;
        private String ActTypeName;
        private String FzUserId;
        private String FzUserName;
        private double Charge;
        private String ActAddress;
        private String ActDate;
        private String ActPlan;
        private String ZCQK;
        private String ZDZJ;
        private String XGPG;
        private String Remark;
        private Object ApplyId;
        private Object ApplyName;
        private String State;
        private String ApplyMess;

        public void setActId(int ActId) {
            this.ActId = ActId;
        }

        public void setActTitle(String ActTitle) {
            this.ActTitle = ActTitle;
        }

        public void setActType(String ActType) {
            this.ActType = ActType;
        }

        public void setActTypeName(String ActTypeName) {
            this.ActTypeName = ActTypeName;
        }

        public void setFzUserId(String FzUserId) {
            this.FzUserId = FzUserId;
        }

        public void setFzUserName(String FzUserName) {
            this.FzUserName = FzUserName;
        }

        public void setCharge(double Charge) {
            this.Charge = Charge;
        }

        public void setActAddress(String ActAddress) {
            this.ActAddress = ActAddress;
        }

        public void setActDate(String ActDate) {
            this.ActDate = ActDate;
        }

        public void setActPlan(String ActPlan) {
            this.ActPlan = ActPlan;
        }

        public void setZCQK(String ZCQK) {
            this.ZCQK = ZCQK;
        }

        public void setZDZJ(String ZDZJ) {
            this.ZDZJ = ZDZJ;
        }

        public void setXGPG(String XGPG) {
            this.XGPG = XGPG;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public void setApplyId(Object ApplyId) {
            this.ApplyId = ApplyId;
        }

        public void setApplyName(Object ApplyName) {
            this.ApplyName = ApplyName;
        }

        public void setState(String State) {
            this.State = State;
        }

        public void setApplyMess(String ApplyMess) {
            this.ApplyMess = ApplyMess;
        }

        public int getActId() {
            return ActId;
        }

        public String getActTitle() {
            return ActTitle;
        }

        public String getActType() {
            return ActType;
        }

        public String getActTypeName() {
            return ActTypeName;
        }

        public String getFzUserId() {
            return FzUserId;
        }

        public String getFzUserName() {
            return FzUserName;
        }

        public double getCharge() {
            return Charge;
        }

        public String getActAddress() {
            return ActAddress;
        }

        public String getActDate() {
            return ActDate;
        }

        public String getActPlan() {
            return ActPlan;
        }

        public String getZCQK() {
            return ZCQK;
        }

        public String getZDZJ() {
            return ZDZJ;
        }

        public String getXGPG() {
            return XGPG;
        }

        public String getRemark() {
            return Remark;
        }

        public Object getApplyId() {
            return ApplyId;
        }

        public Object getApplyName() {
            return ApplyName;
        }

        public String getState() {
            return State;
        }

        public String getApplyMess() {
            return ApplyMess;
        }
    }
}
