package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class ApplyEntity {

    /**
     * stamess : 获取成功，请假如下！
     * staval : 0
     */

    private List<StatusEntity> status;
    /**
     * Row : 1
     * ApprovalID : 39
     * FlowID : null
     * flag : 通过
     * DeptName : 京西总部
     * DeptID : 1014
     * AppTypeName : null
     * ApprovalType : 事假
     * ApprovalTitle : 286
     * ApprovalDate : null
     * StartDate : 2016-05-05T00:00:00
     * EndDate : 2016-05-05T00:00:00
     * Days : 0.0
     * Hours : 1.0
     * Attachment : null
     * ApprovalReason : 红
     * ApprovalByName : 万权
     * ApprovalBy : OP10086
     * CreateByName : 万权
     * CreatedDate : null
     * UpdatedByName : null
     * UpdatedDate : null
     * DeleteMark : null
     * PROCID : 39
     * OperateFlag : 未审批
     * OperateByName : 管理员
     * OperatedBy : OP10001
     * OperatedDate : null
     * OperateMessage : null
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
        private Object FlowID;
        private String flag;
        private String DeptName;
        private String DeptID;
        private Object AppTypeName;
        private String ApprovalType;
        private String ApprovalTitle;
        private Object ApprovalDate;
        private String StartDate;
        private String EndDate;
        private double Days;
        private double Hours;
        private Object Attachment;
        private String ApprovalReason;
        private String ApprovalByName;
        private String ApprovalBy;
        private String CreateByName;
        private Object CreatedDate;
        private Object UpdatedByName;
        private Object UpdatedDate;
        private Object DeleteMark;
        private int PROCID;
        private String OperateFlag;
        private String OperateByName;
        private String OperatedBy;
        private Object OperatedDate;
        private Object OperateMessage;
        private Object ProcAttach;

        public void setRow(int Row) {
            this.Row = Row;
        }

        public void setApprovalID(int ApprovalID) {
            this.ApprovalID = ApprovalID;
        }

        public void setFlowID(Object FlowID) {
            this.FlowID = FlowID;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public void setDeptID(String DeptID) {
            this.DeptID = DeptID;
        }

        public void setAppTypeName(Object AppTypeName) {
            this.AppTypeName = AppTypeName;
        }

        public void setApprovalType(String ApprovalType) {
            this.ApprovalType = ApprovalType;
        }

        public void setApprovalTitle(String ApprovalTitle) {
            this.ApprovalTitle = ApprovalTitle;
        }

        public void setApprovalDate(Object ApprovalDate) {
            this.ApprovalDate = ApprovalDate;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public void setDays(double Days) {
            this.Days = Days;
        }

        public void setHours(double Hours) {
            this.Hours = Hours;
        }

        public void setAttachment(Object Attachment) {
            this.Attachment = Attachment;
        }

        public void setApprovalReason(String ApprovalReason) {
            this.ApprovalReason = ApprovalReason;
        }

        public void setApprovalByName(String ApprovalByName) {
            this.ApprovalByName = ApprovalByName;
        }

        public void setApprovalBy(String ApprovalBy) {
            this.ApprovalBy = ApprovalBy;
        }

        public void setCreateByName(String CreateByName) {
            this.CreateByName = CreateByName;
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

        public void setOperatedDate(Object OperatedDate) {
            this.OperatedDate = OperatedDate;
        }

        public void setOperateMessage(Object OperateMessage) {
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

        public Object getFlowID() {
            return FlowID;
        }

        public String getFlag() {
            return flag;
        }

        public String getDeptName() {
            return DeptName;
        }

        public String getDeptID() {
            return DeptID;
        }

        public Object getAppTypeName() {
            return AppTypeName;
        }

        public String getApprovalType() {
            return ApprovalType;
        }

        public String getApprovalTitle() {
            return ApprovalTitle;
        }

        public Object getApprovalDate() {
            return ApprovalDate;
        }

        public String getStartDate() {
            return StartDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public double getDays() {
            return Days;
        }

        public double getHours() {
            return Hours;
        }

        public Object getAttachment() {
            return Attachment;
        }

        public String getApprovalReason() {
            return ApprovalReason;
        }

        public String getApprovalByName() {
            return ApprovalByName;
        }

        public String getApprovalBy() {
            return ApprovalBy;
        }

        public String getCreateByName() {
            return CreateByName;
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

        public Object getOperatedDate() {
            return OperatedDate;
        }

        public Object getOperateMessage() {
            return OperateMessage;
        }

        public Object getProcAttach() {
            return ProcAttach;
        }
    }
}
