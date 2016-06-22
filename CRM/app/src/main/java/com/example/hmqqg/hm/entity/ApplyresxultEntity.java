package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by bona on 2016/5/6.
 */
public class ApplyresxultEntity {


    /**
     * stamess : 获取已审核信息成功，请假如下！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * Row : 1
     * ApprovalID : 39
     * FlowID : 7
     * DeptID : 1014
     * DEPTNAME : 京西总部
     * ApprovalType : 事假
     * ApprovalBy : OP10086
     * ApprovalByName : 万权
     * Flag : Y
     * ApprovalTitle : 286
     * ApprovalDate : null
     * Days : 0.0
     * Hours : 1.0
     * StartDate : 2016-05-05T00:00:00
     * EndDate : 2016-05-05T00:00:00
     * ApprovalReason : 红
     * FlowTypeName : 员工请假
     * CreatedDate : null
     * UpdatedByName : null
     * UpdatedDate : null
     * DeleteMark : null
     * PROCID : 39
     * OperateFlag : 已审批
     * OperateByName : 管理员
     * OperatedBy : OP10001
     * OperatedDate : 2016-05-06T00:00:00
     * OperateMessage : 哈哈
     * ProcAttach : null
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
        private int ApprovalID;
        private int FlowID;
        private String DeptID;
        private String DEPTNAME;
        private String ApprovalType;
        private String ApprovalBy;
        private String ApprovalByName;
        private String Flag;
        private String ApprovalTitle;
        private Object ApprovalDate;
        private double Days;
        private double Hours;
        private String StartDate;
        private String EndDate;
        private String ApprovalReason;
        private String FlowTypeName;
        private Object CreatedDate;
        private Object UpdatedByName;
        private Object UpdatedDate;
        private Object DeleteMark;
        private int PROCID;
        private String OperateFlag;
        private String OperateByName;
        private String OperatedBy;
        private String OperatedDate;
        private String OperateMessage;
        private Object ProcAttach;

        public void setRow(int Row) {
            this.Row = Row;
        }

        public void setApprovalID(int ApprovalID) {
            this.ApprovalID = ApprovalID;
        }

        public void setFlowID(int FlowID) {
            this.FlowID = FlowID;
        }

        public void setDeptID(String DeptID) {
            this.DeptID = DeptID;
        }

        public void setDEPTNAME(String DEPTNAME) {
            this.DEPTNAME = DEPTNAME;
        }

        public void setApprovalType(String ApprovalType) {
            this.ApprovalType = ApprovalType;
        }

        public void setApprovalBy(String ApprovalBy) {
            this.ApprovalBy = ApprovalBy;
        }

        public void setApprovalByName(String ApprovalByName) {
            this.ApprovalByName = ApprovalByName;
        }

        public void setFlag(String Flag) {
            this.Flag = Flag;
        }

        public void setApprovalTitle(String ApprovalTitle) {
            this.ApprovalTitle = ApprovalTitle;
        }

        public void setApprovalDate(Object ApprovalDate) {
            this.ApprovalDate = ApprovalDate;
        }

        public void setDays(double Days) {
            this.Days = Days;
        }

        public void setHours(double Hours) {
            this.Hours = Hours;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public void setApprovalReason(String ApprovalReason) {
            this.ApprovalReason = ApprovalReason;
        }

        public void setFlowTypeName(String FlowTypeName) {
            this.FlowTypeName = FlowTypeName;
        }

        public void setCreatedDate(Object CreatedDate) {
            this.CreatedDate = CreatedDate;
        }

        public void setUpdatedByName(Object UpdatedByName) {
            this.UpdatedByName = UpdatedByName;
        }

        public void setUpdatedDate(Object UpdatedDate) {
            this.UpdatedDate = UpdatedDate;
        }

        public void setDeleteMark(Object DeleteMark) {
            this.DeleteMark = DeleteMark;
        }

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

        public int getRow() {
            return Row;
        }

        public int getApprovalID() {
            return ApprovalID;
        }

        public int getFlowID() {
            return FlowID;
        }

        public String getDeptID() {
            return DeptID;
        }

        public String getDEPTNAME() {
            return DEPTNAME;
        }

        public String getApprovalType() {
            return ApprovalType;
        }

        public String getApprovalBy() {
            return ApprovalBy;
        }

        public String getApprovalByName() {
            return ApprovalByName;
        }

        public String getFlag() {
            return Flag;
        }

        public String getApprovalTitle() {
            return ApprovalTitle;
        }

        public Object getApprovalDate() {
            return ApprovalDate;
        }

        public double getDays() {
            return Days;
        }

        public double getHours() {
            return Hours;
        }

        public String getStartDate() {
            return StartDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public String getApprovalReason() {
            return ApprovalReason;
        }

        public String getFlowTypeName() {
            return FlowTypeName;
        }

        public Object getCreatedDate() {
            return CreatedDate;
        }

        public Object getUpdatedByName() {
            return UpdatedByName;
        }

        public Object getUpdatedDate() {
            return UpdatedDate;
        }

        public Object getDeleteMark() {
            return DeleteMark;
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
