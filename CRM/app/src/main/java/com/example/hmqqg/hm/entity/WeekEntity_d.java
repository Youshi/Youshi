package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
public class WeekEntity_d {


    /**
     * stamess : 获取日报信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * HbId : HB2016050000107
     * HbType : week
     * HbTitle : null
     * cyear : 2016
     * flag : 已阅
     * UserId : OP10086
     * USERNAME : 万权
     * StartDate : 2016-05-26
     * EndDate : 2016-05-26
     * ZYYW : null
     * ZYWT : null
     * JDSM : null
     * YJJY : null
     * AdminUser : null
     * WriteDate : null
     * UpdateDate : null
     * DPNR : null
     * DPR : null
     * DpDate : null
     * tday : null
     * tmonth : 5
     * tweek : 22
     * tmpDpDate : null
     * Score : null
     * thour : null
     * AlienCall : 1
     * ReturnCall : 1
     * IntentNum : 1
     * ReturnCond : 1
     * DealCond : 1
     * NextWork : 1
     * CreatedBy : null
     * InvitNum : 1
     */

    private List<DetailInfoEntity> DetailInfo;
    /**
     * PROCID : 1
     * OperateFlag : 已审批
     * OperateByName : 123
     * OperatedBy : OP10125
     * OperatedDate : 2016-05-26T00:00:00
     * OperateMessage : 同意1
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
        private String HbId;
        private String HbType;
        private Object HbTitle;
        private int cyear;
        private String flag;
        private String UserId;
        private String USERNAME;
        private String StartDate;
        private String EndDate;
        private Object ZYYW;
        private Object ZYWT;
        private Object JDSM;
        private Object YJJY;
        private Object AdminUser;
        private Object WriteDate;
        private Object UpdateDate;
        private Object DPNR;
        private Object DPR;
        private Object DpDate;
        private Object tday;
        private int tmonth;
        private int tweek;
        private Object tmpDpDate;
        private Object Score;
        private Object thour;
        private int AlienCall;
        private int ReturnCall;
        private int IntentNum;
        private String ReturnCond;
        private String DealCond;
        private String NextWork;
        private Object CreatedBy;
        private int InvitNum;

        public void setHbId(String HbId) {
            this.HbId = HbId;
        }

        public void setHbType(String HbType) {
            this.HbType = HbType;
        }

        public void setHbTitle(Object HbTitle) {
            this.HbTitle = HbTitle;
        }

        public void setCyear(int cyear) {
            this.cyear = cyear;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setUSERNAME(String USERNAME) {
            this.USERNAME = USERNAME;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public void setZYYW(Object ZYYW) {
            this.ZYYW = ZYYW;
        }

        public void setZYWT(Object ZYWT) {
            this.ZYWT = ZYWT;
        }

        public void setJDSM(Object JDSM) {
            this.JDSM = JDSM;
        }

        public void setYJJY(Object YJJY) {
            this.YJJY = YJJY;
        }

        public void setAdminUser(Object AdminUser) {
            this.AdminUser = AdminUser;
        }

        public void setWriteDate(Object WriteDate) {
            this.WriteDate = WriteDate;
        }

        public void setUpdateDate(Object UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public void setDPNR(Object DPNR) {
            this.DPNR = DPNR;
        }

        public void setDPR(Object DPR) {
            this.DPR = DPR;
        }

        public void setDpDate(Object DpDate) {
            this.DpDate = DpDate;
        }

        public void setTday(Object tday) {
            this.tday = tday;
        }

        public void setTmonth(int tmonth) {
            this.tmonth = tmonth;
        }

        public void setTweek(int tweek) {
            this.tweek = tweek;
        }

        public void setTmpDpDate(Object tmpDpDate) {
            this.tmpDpDate = tmpDpDate;
        }

        public void setScore(Object Score) {
            this.Score = Score;
        }

        public void setThour(Object thour) {
            this.thour = thour;
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

        public void setNextWork(String NextWork) {
            this.NextWork = NextWork;
        }

        public void setCreatedBy(Object CreatedBy) {
            this.CreatedBy = CreatedBy;
        }

        public void setInvitNum(int InvitNum) {
            this.InvitNum = InvitNum;
        }

        public String getHbId() {
            return HbId;
        }

        public String getHbType() {
            return HbType;
        }

        public Object getHbTitle() {
            return HbTitle;
        }

        public int getCyear() {
            return cyear;
        }

        public String getFlag() {
            return flag;
        }

        public String getUserId() {
            return UserId;
        }

        public String getUSERNAME() {
            return USERNAME;
        }

        public String getStartDate() {
            return StartDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public Object getZYYW() {
            return ZYYW;
        }

        public Object getZYWT() {
            return ZYWT;
        }

        public Object getJDSM() {
            return JDSM;
        }

        public Object getYJJY() {
            return YJJY;
        }

        public Object getAdminUser() {
            return AdminUser;
        }

        public Object getWriteDate() {
            return WriteDate;
        }

        public Object getUpdateDate() {
            return UpdateDate;
        }

        public Object getDPNR() {
            return DPNR;
        }

        public Object getDPR() {
            return DPR;
        }

        public Object getDpDate() {
            return DpDate;
        }

        public Object getTday() {
            return tday;
        }

        public int getTmonth() {
            return tmonth;
        }

        public int getTweek() {
            return tweek;
        }

        public Object getTmpDpDate() {
            return tmpDpDate;
        }

        public Object getScore() {
            return Score;
        }

        public Object getThour() {
            return thour;
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

        public String getNextWork() {
            return NextWork;
        }

        public Object getCreatedBy() {
            return CreatedBy;
        }

        public int getInvitNum() {
            return InvitNum;
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
