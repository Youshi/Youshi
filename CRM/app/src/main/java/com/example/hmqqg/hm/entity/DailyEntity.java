package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
public class DailyEntity {

    /**
     * stamess : 获取日报信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * WLogId : RZ160525000020
     * UserId : OP10125
     * UserName : 123
     * Content :
     * WorkTime : 11.0
     * StartDate : 2016-05-25T00:00:00
     * EndDate : 2016-05-25T00:00:00
     * AlienCall : 1
     * ReturnCall : 1
     * IntentNum : 1
     * InvitNum : 1
     * ReturnCond : rr
     * DealCond : rt
     * NextDay : vv
     * Team : null
     * TeamLeader : null
     * IsDraft : 0
     * CreateDate : 2016-05-25T00:00:00
     * UpdateDate : 2016-05-25T00:00:00
     * CreatedBy :
     * UpdatedBy :
     * IsEnable : true
     * PROCID : 30
     * PROCFLAG : 30
     * OperatedBy : OP10136
     * OperatedByName : bt
     * OperatedDate : null
     * OperateMessage : null
     * ProcAttach : null
     * StartDateExt : 2016-05-25
     * EndDateExt : 2016-05-25
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
        private String WLogId;
        private String UserId;
        private String UserName;
        private String Content;
        private double WorkTime;
        private String StartDate;
        private String EndDate;
        private int AlienCall;
        private int ReturnCall;
        private int IntentNum;
        private int InvitNum;
        private String ReturnCond;
        private String DealCond;
        private String NextDay;
        private Object Team;
        private Object TeamLeader;
        private String IsDraft;
        private String CreateDate;
        private String UpdateDate;
        private String CreatedBy;
        private String UpdatedBy;
        private boolean IsEnable;
        private int PROCID;
        private String PROCFLAG;
        private String OperatedBy;
        private String OperatedByName;
        private Object OperatedDate;
        private Object OperateMessage;
        private Object ProcAttach;
        private String StartDateExt;
        private String EndDateExt;

        public String getWLogId() {
            return WLogId;
        }

        public void setWLogId(String WLogId) {
            this.WLogId = WLogId;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public double getWorkTime() {
            return WorkTime;
        }

        public void setWorkTime(double WorkTime) {
            this.WorkTime = WorkTime;
        }

        public String getStartDate() {
            return StartDate;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public int getAlienCall() {
            return AlienCall;
        }

        public void setAlienCall(int AlienCall) {
            this.AlienCall = AlienCall;
        }

        public int getReturnCall() {
            return ReturnCall;
        }

        public void setReturnCall(int ReturnCall) {
            this.ReturnCall = ReturnCall;
        }

        public int getIntentNum() {
            return IntentNum;
        }

        public void setIntentNum(int IntentNum) {
            this.IntentNum = IntentNum;
        }

        public int getInvitNum() {
            return InvitNum;
        }

        public void setInvitNum(int InvitNum) {
            this.InvitNum = InvitNum;
        }

        public String getReturnCond() {
            return ReturnCond;
        }

        public void setReturnCond(String ReturnCond) {
            this.ReturnCond = ReturnCond;
        }

        public String getDealCond() {
            return DealCond;
        }

        public void setDealCond(String DealCond) {
            this.DealCond = DealCond;
        }

        public String getNextDay() {
            return NextDay;
        }

        public void setNextDay(String NextDay) {
            this.NextDay = NextDay;
        }

        public Object getTeam() {
            return Team;
        }

        public void setTeam(Object Team) {
            this.Team = Team;
        }

        public Object getTeamLeader() {
            return TeamLeader;
        }

        public void setTeamLeader(Object TeamLeader) {
            this.TeamLeader = TeamLeader;
        }

        public String getIsDraft() {
            return IsDraft;
        }

        public void setIsDraft(String IsDraft) {
            this.IsDraft = IsDraft;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public void setUpdateDate(String UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public String getCreatedBy() {
            return CreatedBy;
        }

        public void setCreatedBy(String CreatedBy) {
            this.CreatedBy = CreatedBy;
        }

        public String getUpdatedBy() {
            return UpdatedBy;
        }

        public void setUpdatedBy(String UpdatedBy) {
            this.UpdatedBy = UpdatedBy;
        }

        public boolean isIsEnable() {
            return IsEnable;
        }

        public void setIsEnable(boolean IsEnable) {
            this.IsEnable = IsEnable;
        }

        public int getPROCID() {
            return PROCID;
        }

        public void setPROCID(int PROCID) {
            this.PROCID = PROCID;
        }

        public String getPROCFLAG() {
            return PROCFLAG;
        }

        public void setPROCFLAG(String PROCFLAG) {
            this.PROCFLAG = PROCFLAG;
        }

        public String getOperatedBy() {
            return OperatedBy;
        }

        public void setOperatedBy(String OperatedBy) {
            this.OperatedBy = OperatedBy;
        }

        public String getOperatedByName() {
            return OperatedByName;
        }

        public void setOperatedByName(String OperatedByName) {
            this.OperatedByName = OperatedByName;
        }

        public Object getOperatedDate() {
            return OperatedDate;
        }

        public void setOperatedDate(Object OperatedDate) {
            this.OperatedDate = OperatedDate;
        }

        public Object getOperateMessage() {
            return OperateMessage;
        }

        public void setOperateMessage(Object OperateMessage) {
            this.OperateMessage = OperateMessage;
        }

        public Object getProcAttach() {
            return ProcAttach;
        }

        public void setProcAttach(Object ProcAttach) {
            this.ProcAttach = ProcAttach;
        }

        public String getStartDateExt() {
            return StartDateExt;
        }

        public void setStartDateExt(String StartDateExt) {
            this.StartDateExt = StartDateExt;
        }

        public String getEndDateExt() {
            return EndDateExt;
        }

        public void setEndDateExt(String EndDateExt) {
            this.EndDateExt = EndDateExt;
        }
    }
}
