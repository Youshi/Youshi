package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public class ResultsEntity {

    /**
     * stamess : 获取已审核信息成功，请假如下！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * Row : 1
     * id : RZ16052700004
     * wtype : day
     * wtitle :
     * flag : 已阅
     * userid : OP10086
     * UserName : 万权
     * WorkTime : 8.0
     * StartDate : 2016-05-27T00:00:00
     * EndDate : 2016-05-27T00:00:00
     * AlienCall : 12
     * returncall : 13
     * intentnum : 14
     * invitnum : 1
     * returncond : aa
     * dealcond : bb
     * nextwork : cc
     * createdBy : null
     * operateflag : Y
     * OperatedBy : OP10125
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
        private int Row;
        private String id;
        private String wtype;
        private String wtitle;
        private String flag;
        private String userid;
        private String UserName;
        private double WorkTime;
        private String StartDate;
        private String EndDate;
        private int AlienCall;
        private int returncall;
        private int intentnum;
        private int invitnum;
        private String returncond;
        private String dealcond;
        private String nextwork;
        private Object createdBy;
        private String operateflag;
        private String OperatedBy;

        public void setRow(int Row) {
            this.Row = Row;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setWtype(String wtype) {
            this.wtype = wtype;
        }

        public void setWtitle(String wtitle) {
            this.wtitle = wtitle;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setWorkTime(double WorkTime) {
            this.WorkTime = WorkTime;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public void setAlienCall(int AlienCall) {
            this.AlienCall = AlienCall;
        }

        public void setReturncall(int returncall) {
            this.returncall = returncall;
        }

        public void setIntentnum(int intentnum) {
            this.intentnum = intentnum;
        }

        public void setInvitnum(int invitnum) {
            this.invitnum = invitnum;
        }

        public void setReturncond(String returncond) {
            this.returncond = returncond;
        }

        public void setDealcond(String dealcond) {
            this.dealcond = dealcond;
        }

        public void setNextwork(String nextwork) {
            this.nextwork = nextwork;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public void setOperateflag(String operateflag) {
            this.operateflag = operateflag;
        }

        public void setOperatedBy(String OperatedBy) {
            this.OperatedBy = OperatedBy;
        }

        public int getRow() {
            return Row;
        }

        public String getId() {
            return id;
        }

        public String getWtype() {
            return wtype;
        }

        public String getWtitle() {
            return wtitle;
        }

        public String getFlag() {
            return flag;
        }

        public String getUserid() {
            return userid;
        }

        public String getUserName() {
            return UserName;
        }

        public double getWorkTime() {
            return WorkTime;
        }

        public String getStartDate() {
            return StartDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public int getAlienCall() {
            return AlienCall;
        }

        public int getReturncall() {
            return returncall;
        }

        public int getIntentnum() {
            return intentnum;
        }

        public int getInvitnum() {
            return invitnum;
        }

        public String getReturncond() {
            return returncond;
        }

        public String getDealcond() {
            return dealcond;
        }

        public String getNextwork() {
            return nextwork;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public String getOperateflag() {
            return operateflag;
        }

        public String getOperatedBy() {
            return OperatedBy;
        }
    }
}
