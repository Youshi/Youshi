package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
public class DailyEntity_d {


    /**
     * stamess : 获取日报信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * WLogId : RZ160526000014
     * UserId : OP10086
     * UserName : 万权
     * deptcode : null
     * flag : 已阅
     * DeptName : null
     * Content : null
     * WorkTime : 8.0
     * StartDate : 2016-05-25
     * EndDate : 2016-05-25T00:00:00
     * tempStartDate : 2016-05-25
     * tempEndDate : 2016-05-25
     * CreateDate : 2016-05-26
     * UpdateDate : 2016-05-26
     * AlienCall : 12
     * ReturnCall : 13
     * IntentNum : 14
     * ReturnCond : aa
     * DealCond : bb
     * NextDay : cc
     * CreatedBy : null
     * InvitNum : 1
     * UpdatedBY : null
     * IsEnable : true
     */

    private List<DetailInfoEntity> DetailInfo;
    /**
     * PROCID : 14
     * OperateFlag : 已审批
     * OperateByName : 123
     * OperatedBy : OP10125
     * OperatedDate : 2016-05-26T00:00:00
     * OperateMessage : ooooooooooooooooo
     * ProcAttach : null
     */

    private List<CommentsEntity> Comments;

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public void setDetailInfo(List<DetailInfoEntity> DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public void setComments(List<CommentsEntity> Comments) {
        this.Comments = Comments;
    }

    public List<StatusEntity> getStatus() {
        return status;
    }

    public List<DetailInfoEntity> getDetailInfo() {
        return DetailInfo;
    }

    public List<CommentsEntity> getComments() {
        return Comments;
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
        private String WLogId;
        private String UserId;
        private String UserName;
        private Object deptcode;
        private String flag;
        private Object DeptName;
        private Object Content;
        private double WorkTime;
        private String StartDate;
        private String EndDate;
        private String tempStartDate;
        private String tempEndDate;
        private String CreateDate;
        private String UpdateDate;
        private int AlienCall;
        private int ReturnCall;
        private int IntentNum;
        private String ReturnCond;
        private String DealCond;
        private String NextDay;
        private Object CreatedBy;
        private int InvitNum;
        private Object UpdatedBY;
        private boolean IsEnable;

        public void setWLogId(String WLogId) {
            this.WLogId = WLogId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setDeptcode(Object deptcode) {
            this.deptcode = deptcode;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setDeptName(Object DeptName) {
            this.DeptName = DeptName;
        }

        public void setContent(Object Content) {
            this.Content = Content;
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

        public void setTempStartDate(String tempStartDate) {
            this.tempStartDate = tempStartDate;
        }

        public void setTempEndDate(String tempEndDate) {
            this.tempEndDate = tempEndDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public void setUpdateDate(String UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public void setAlienCall(int AlienCall) {
            this.AlienCall = AlienCall;
        }

        public void setReturnCall(int ReturnCall) {
            this.ReturnCall = ReturnCall;
        }

        public void setIntentNum(int IntentNum) {
            this.IntentNum = IntentNum;
        }

        public void setReturnCond(String ReturnCond) {
            this.ReturnCond = ReturnCond;
        }

        public void setDealCond(String DealCond) {
            this.DealCond = DealCond;
        }

        public void setNextDay(String NextDay) {
            this.NextDay = NextDay;
        }

        public void setCreatedBy(Object CreatedBy) {
            this.CreatedBy = CreatedBy;
        }

        public void setInvitNum(int InvitNum) {
            this.InvitNum = InvitNum;
        }

        public void setUpdatedBY(Object UpdatedBY) {
            this.UpdatedBY = UpdatedBY;
        }

        public void setIsEnable(boolean IsEnable) {
            this.IsEnable = IsEnable;
        }

        public String getWLogId() {
            return WLogId;
        }

        public String getUserId() {
            return UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public Object getDeptcode() {
            return deptcode;
        }

        public String getFlag() {
            return flag;
        }

        public Object getDeptName() {
            return DeptName;
        }

        public Object getContent() {
            return Content;
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

        public String getTempStartDate() {
            return tempStartDate;
        }

        public String getTempEndDate() {
            return tempEndDate;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public int getAlienCall() {
            return AlienCall;
        }

        public int getReturnCall() {
            return ReturnCall;
        }

        public int getIntentNum() {
            return IntentNum;
        }

        public String getReturnCond() {
            return ReturnCond;
        }

        public String getDealCond() {
            return DealCond;
        }

        public String getNextDay() {
            return NextDay;
        }

        public Object getCreatedBy() {
            return CreatedBy;
        }

        public int getInvitNum() {
            return InvitNum;
        }

        public Object getUpdatedBY() {
            return UpdatedBY;
        }

        public boolean isIsEnable() {
            return IsEnable;
        }
    }

    public static class CommentsEntity {
        private int PROCID;
        private String OperateFlag;
        private String OperateByName;
        private String OperatedBy;
        private String OperatedDate;
        private String OperateMessage;
        private Object ProcAttach;

        public void setPROCID(int PROCID) {
            this.PROCID = PROCID;
        }

        public void setOperateFlag(String OperateFlag) {
            this.OperateFlag = OperateFlag;
        }

        public void setOperateByName(String OperateByName) {
            this.OperateByName = OperateByName;
        }

        public void setOperatedBy(String OperatedBy) {
            this.OperatedBy = OperatedBy;
        }

        public void setOperatedDate(String OperatedDate) {
            this.OperatedDate = OperatedDate;
        }

        public void setOperateMessage(String OperateMessage) {
            this.OperateMessage = OperateMessage;
        }

        public void setProcAttach(Object ProcAttach) {
            this.ProcAttach = ProcAttach;
        }

        public int getPROCID() {
            return PROCID;
        }

        public String getOperateFlag() {
            return OperateFlag;
        }

        public String getOperateByName() {
            return OperateByName;
        }

        public String getOperatedBy() {
            return OperatedBy;
        }

        public String getOperatedDate() {
            return OperatedDate;
        }

        public String getOperateMessage() {
            return OperateMessage;
        }

        public Object getProcAttach() {
            return ProcAttach;
        }
    }
}
