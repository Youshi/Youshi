package com.example.hmqqg.hm.entity;

import java.util.List;

/**下属员工的月报详情
 * Created by Administrator on 2016/4/20.
 */
public class UndlingMonthEntity {


    /**
     * stamess : 获取月报报信息成功！
     * staval : 1
     */

    private List<StatusBean> status;
    /**
     * HbId : HB201605000093
     * HbType : month
     * HbTitle : null
     * cyear : 2016
     * flag : 未阅
     * UserId : OP10086
     * USERNAME : 万权
     * StartDate : 2016-05-25
     * EndDate : 2016-05-25
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
     * AlienCall : 2
     * ReturnCall : 3
     * IntentNum : 4
     * ReturnCond : fg
     * DealCond : cv
     * NextWork : gg
     * CreatedBy : null
     * InvitNum : 1
     * PROCID : 5
     * OperateFlag : 未审批
     * OperateByName : 123
     * OperatedBy : OP10125
     * OperatedDate : null
     * OperateMessage : null
     * ProcAttach : null
     */

    private List<DetailInfoBean> DetailInfo;

    public List<StatusBean> getStatus() {
        return status;
    }

    public void setStatus(List<StatusBean> status) {
        this.status = status;
    }

    public List<DetailInfoBean> getDetailInfo() {
        return DetailInfo;
    }

    public void setDetailInfo(List<DetailInfoBean> DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public static class StatusBean {
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

    public static class DetailInfoBean {
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
        private int PROCID;
        private String OperateFlag;
        private String OperateByName;
        private String OperatedBy;
        private Object OperatedDate;
        private Object OperateMessage;
        private Object ProcAttach;

        public String getHbId() {
            return HbId;
        }

        public void setHbId(String HbId) {
            this.HbId = HbId;
        }

        public String getHbType() {
            return HbType;
        }

        public void setHbType(String HbType) {
            this.HbType = HbType;
        }

        public Object getHbTitle() {
            return HbTitle;
        }

        public void setHbTitle(Object HbTitle) {
            this.HbTitle = HbTitle;
        }

        public int getCyear() {
            return cyear;
        }

        public void setCyear(int cyear) {
            this.cyear = cyear;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getUSERNAME() {
            return USERNAME;
        }

        public void setUSERNAME(String USERNAME) {
            this.USERNAME = USERNAME;
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

        public Object getZYYW() {
            return ZYYW;
        }

        public void setZYYW(Object ZYYW) {
            this.ZYYW = ZYYW;
        }

        public Object getZYWT() {
            return ZYWT;
        }

        public void setZYWT(Object ZYWT) {
            this.ZYWT = ZYWT;
        }

        public Object getJDSM() {
            return JDSM;
        }

        public void setJDSM(Object JDSM) {
            this.JDSM = JDSM;
        }

        public Object getYJJY() {
            return YJJY;
        }

        public void setYJJY(Object YJJY) {
            this.YJJY = YJJY;
        }

        public Object getAdminUser() {
            return AdminUser;
        }

        public void setAdminUser(Object AdminUser) {
            this.AdminUser = AdminUser;
        }

        public Object getWriteDate() {
            return WriteDate;
        }

        public void setWriteDate(Object WriteDate) {
            this.WriteDate = WriteDate;
        }

        public Object getUpdateDate() {
            return UpdateDate;
        }

        public void setUpdateDate(Object UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public Object getDPNR() {
            return DPNR;
        }

        public void setDPNR(Object DPNR) {
            this.DPNR = DPNR;
        }

        public Object getDPR() {
            return DPR;
        }

        public void setDPR(Object DPR) {
            this.DPR = DPR;
        }

        public Object getDpDate() {
            return DpDate;
        }

        public void setDpDate(Object DpDate) {
            this.DpDate = DpDate;
        }

        public Object getTday() {
            return tday;
        }

        public void setTday(Object tday) {
            this.tday = tday;
        }

        public int getTmonth() {
            return tmonth;
        }

        public void setTmonth(int tmonth) {
            this.tmonth = tmonth;
        }

        public int getTweek() {
            return tweek;
        }

        public void setTweek(int tweek) {
            this.tweek = tweek;
        }

        public Object getTmpDpDate() {
            return tmpDpDate;
        }

        public void setTmpDpDate(Object tmpDpDate) {
            this.tmpDpDate = tmpDpDate;
        }

        public Object getScore() {
            return Score;
        }

        public void setScore(Object Score) {
            this.Score = Score;
        }

        public Object getThour() {
            return thour;
        }

        public void setThour(Object thour) {
            this.thour = thour;
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

        public String getNextWork() {
            return NextWork;
        }

        public void setNextWork(String NextWork) {
            this.NextWork = NextWork;
        }

        public Object getCreatedBy() {
            return CreatedBy;
        }

        public void setCreatedBy(Object CreatedBy) {
            this.CreatedBy = CreatedBy;
        }

        public int getInvitNum() {
            return InvitNum;
        }

        public void setInvitNum(int InvitNum) {
            this.InvitNum = InvitNum;
        }

        public int getPROCID() {
            return PROCID;
        }

        public void setPROCID(int PROCID) {
            this.PROCID = PROCID;
        }

        public String getOperateFlag() {
            return OperateFlag;
        }

        public void setOperateFlag(String OperateFlag) {
            this.OperateFlag = OperateFlag;
        }

        public String getOperateByName() {
            return OperateByName;
        }

        public void setOperateByName(String OperateByName) {
            this.OperateByName = OperateByName;
        }

        public String getOperatedBy() {
            return OperatedBy;
        }

        public void setOperatedBy(String OperatedBy) {
            this.OperatedBy = OperatedBy;
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
    }
}
