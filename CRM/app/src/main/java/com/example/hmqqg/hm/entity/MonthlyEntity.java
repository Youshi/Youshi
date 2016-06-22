package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 月报容器
 * Created by Administrator on 2016/4/19.
 */
public class MonthlyEntity {


    /**
     * stamess : 获取月报报信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * HbId : HB2016050000109
     * HbType : month
     * HbTitle :
     * cyear : 2016
     * UserId : OP10086
     * UserName : 万权
     * DeptCode : null
     * DeptName : null
     * StartDate : 2016-05-26T00:00:00
     * EndDate : 2016-05-26T00:00:00
     * AlienCall : 1
     * ReturnCall : 1
     * IntentNum : 1
     * InvitNum : 1
     * ReturnCond : 1
     * DealCond : 1
     * NextWork : 1
     * Team : null
     * TeamLeader : null
     * IsDraft : 0
     * ZYYW :
     * ZYWT :
     * JDSM :
     * YJJY :
     * AdminUser :
     * WriteDate : null
     * DPNR :
     * DPR :
     * DpDate : null
     * Score : null
     * CreateDate : null
     * CreatedBy :
     * UpdateDate : null
     * UpdatedBy : null
     * IsEnable : false
     * PROCID : 6
     * OperateFlag : 已审批
     * OperatedBy : OP10136
     * OperatedByName : bt
     * OperatedDate : 2016-05-26T00:00:00
     * OperateMessage : 同意11
     * ProcAttach :
     * StartDateExt : 2016-05-26
     * EndDateExt : 2016-05-26
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
        private String HbId;
        private String HbType;
        private String HbTitle;
        private int cyear;
        private String UserId;
        private String UserName;
        private Object DeptCode;
        private Object DeptName;
        private String StartDate;
        private String EndDate;
        private int AlienCall;
        private int ReturnCall;
        private int IntentNum;
        private int InvitNum;
        private String ReturnCond;
        private String DealCond;
        private String NextWork;
        private Object Team;
        private Object TeamLeader;
        private String IsDraft;
        private String ZYYW;
        private String ZYWT;
        private String JDSM;
        private String YJJY;
        private String AdminUser;
        private Object WriteDate;
        private String DPNR;
        private String DPR;
        private Object DpDate;
        private Object Score;
        private Object CreateDate;
        private String CreatedBy;
        private Object UpdateDate;
        private Object UpdatedBy;
        private boolean IsEnable;
        private int PROCID;
        private String OperateFlag;
        private String OperatedBy;
        private String OperatedByName;
        private String OperatedDate;
        private String OperateMessage;
        private String ProcAttach;
        private String StartDateExt;
        private String EndDateExt;

        public void setHbId(String HbId) {
            this.HbId = HbId;
        }

        public void setHbType(String HbType) {
            this.HbType = HbType;
        }

        public void setHbTitle(String HbTitle) {
            this.HbTitle = HbTitle;
        }

        public void setCyear(int cyear) {
            this.cyear = cyear;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setDeptCode(Object DeptCode) {
            this.DeptCode = DeptCode;
        }

        public void setDeptName(Object DeptName) {
            this.DeptName = DeptName;
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

        public void setReturnCall(int ReturnCall) {
            this.ReturnCall = ReturnCall;
        }

        public void setIntentNum(int IntentNum) {
            this.IntentNum = IntentNum;
        }

        public void setInvitNum(int InvitNum) {
            this.InvitNum = InvitNum;
        }

        public void setReturnCond(String ReturnCond) {
            this.ReturnCond = ReturnCond;
        }

        public void setDealCond(String DealCond) {
            this.DealCond = DealCond;
        }

        public void setNextWork(String NextWork) {
            this.NextWork = NextWork;
        }

        public void setTeam(Object Team) {
            this.Team = Team;
        }

        public void setTeamLeader(Object TeamLeader) {
            this.TeamLeader = TeamLeader;
        }

        public void setIsDraft(String IsDraft) {
            this.IsDraft = IsDraft;
        }

        public void setZYYW(String ZYYW) {
            this.ZYYW = ZYYW;
        }

        public void setZYWT(String ZYWT) {
            this.ZYWT = ZYWT;
        }

        public void setJDSM(String JDSM) {
            this.JDSM = JDSM;
        }

        public void setYJJY(String YJJY) {
            this.YJJY = YJJY;
        }

        public void setAdminUser(String AdminUser) {
            this.AdminUser = AdminUser;
        }

        public void setWriteDate(Object WriteDate) {
            this.WriteDate = WriteDate;
        }

        public void setDPNR(String DPNR) {
            this.DPNR = DPNR;
        }

        public void setDPR(String DPR) {
            this.DPR = DPR;
        }

        public void setDpDate(Object DpDate) {
            this.DpDate = DpDate;
        }

        public void setScore(Object Score) {
            this.Score = Score;
        }

        public void setCreateDate(Object CreateDate) {
            this.CreateDate = CreateDate;
        }

        public void setCreatedBy(String CreatedBy) {
            this.CreatedBy = CreatedBy;
        }

        public void setUpdateDate(Object UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public void setUpdatedBy(Object UpdatedBy) {
            this.UpdatedBy = UpdatedBy;
        }

        public void setIsEnable(boolean IsEnable) {
            this.IsEnable = IsEnable;
        }

        public void setPROCID(int PROCID) {
            this.PROCID = PROCID;
        }

        public void setOperateFlag(String OperateFlag) {
            this.OperateFlag = OperateFlag;
        }

        public void setOperatedBy(String OperatedBy) {
            this.OperatedBy = OperatedBy;
        }

        public void setOperatedByName(String OperatedByName) {
            this.OperatedByName = OperatedByName;
        }

        public void setOperatedDate(String OperatedDate) {
            this.OperatedDate = OperatedDate;
        }

        public void setOperateMessage(String OperateMessage) {
            this.OperateMessage = OperateMessage;
        }

        public void setProcAttach(String ProcAttach) {
            this.ProcAttach = ProcAttach;
        }

        public void setStartDateExt(String StartDateExt) {
            this.StartDateExt = StartDateExt;
        }

        public void setEndDateExt(String EndDateExt) {
            this.EndDateExt = EndDateExt;
        }

        public String getHbId() {
            return HbId;
        }

        public String getHbType() {
            return HbType;
        }

        public String getHbTitle() {
            return HbTitle;
        }

        public int getCyear() {
            return cyear;
        }

        public String getUserId() {
            return UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public Object getDeptCode() {
            return DeptCode;
        }

        public Object getDeptName() {
            return DeptName;
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

        public int getReturnCall() {
            return ReturnCall;
        }

        public int getIntentNum() {
            return IntentNum;
        }

        public int getInvitNum() {
            return InvitNum;
        }

        public String getReturnCond() {
            return ReturnCond;
        }

        public String getDealCond() {
            return DealCond;
        }

        public String getNextWork() {
            return NextWork;
        }

        public Object getTeam() {
            return Team;
        }

        public Object getTeamLeader() {
            return TeamLeader;
        }

        public String getIsDraft() {
            return IsDraft;
        }

        public String getZYYW() {
            return ZYYW;
        }

        public String getZYWT() {
            return ZYWT;
        }

        public String getJDSM() {
            return JDSM;
        }

        public String getYJJY() {
            return YJJY;
        }

        public String getAdminUser() {
            return AdminUser;
        }

        public Object getWriteDate() {
            return WriteDate;
        }

        public String getDPNR() {
            return DPNR;
        }

        public String getDPR() {
            return DPR;
        }

        public Object getDpDate() {
            return DpDate;
        }

        public Object getScore() {
            return Score;
        }

        public Object getCreateDate() {
            return CreateDate;
        }

        public String getCreatedBy() {
            return CreatedBy;
        }

        public Object getUpdateDate() {
            return UpdateDate;
        }

        public Object getUpdatedBy() {
            return UpdatedBy;
        }

        public boolean isIsEnable() {
            return IsEnable;
        }

        public int getPROCID() {
            return PROCID;
        }

        public String getOperateFlag() {
            return OperateFlag;
        }

        public String getOperatedBy() {
            return OperatedBy;
        }

        public String getOperatedByName() {
            return OperatedByName;
        }

        public String getOperatedDate() {
            return OperatedDate;
        }

        public String getOperateMessage() {
            return OperateMessage;
        }

        public String getProcAttach() {
            return ProcAttach;
        }

        public String getStartDateExt() {
            return StartDateExt;
        }

        public String getEndDateExt() {
            return EndDateExt;
        }
    }
}
