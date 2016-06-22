package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class LeaveEntity {


    /**
     * stamess : 获取成功，请假如下！
     * staval : 0
     */

    private List<StatusEntity> status;
    /**
     * ApprovalID : 33
     * FlowID : null
     * flag : 通过
     * DeptName : 经理室
     * DeptID : 1044
     * AppTypeName : null
     * ApprovalType : 病假
     * ApprovalTitle : 1111
     * ApprovalDate : null
     * StartDate : 2016-05-05T00:00:00
     * EndDate : 2016-05-05T00:00:00
     * Days : 1.0
     * Hours : 0.0
     * Attachment : null
     * ApprovalReason : 111111
     * ApprovalByName : 管理员
     * ApprovalBy : OP10001
     * CreateByName : 管理员
     * CreatedDate : null
     * UpdatedByName : null
     * UpdatedDate : null
     * DeleteMark : null
     * PROCID : 33
     * OperateFlag : 未审批
     * OperateByName : 管理员
     * OperatedBy : op10001
     * OperatedDate : null
     * OperateMessage : null
     * ProcAttach : null
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

        public int getApprovalID() {
            return ApprovalID;
        }

        public void setApprovalID(int ApprovalID) {
            this.ApprovalID = ApprovalID;
        }

        public Object getFlowID() {
            return FlowID;
        }

        public void setFlowID(Object FlowID) {
            this.FlowID = FlowID;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getDeptID() {
            return DeptID;
        }

        public void setDeptID(String DeptID) {
            this.DeptID = DeptID;
        }

        public Object getAppTypeName() {
            return AppTypeName;
        }

        public void setAppTypeName(Object AppTypeName) {
            this.AppTypeName = AppTypeName;
        }

        public String getApprovalType() {
            return ApprovalType;
        }

        public void setApprovalType(String ApprovalType) {
            this.ApprovalType = ApprovalType;
        }

        public String getApprovalTitle() {
            return ApprovalTitle;
        }

        public void setApprovalTitle(String ApprovalTitle) {
            this.ApprovalTitle = ApprovalTitle;
        }

        public Object getApprovalDate() {
            return ApprovalDate;
        }

        public void setApprovalDate(Object ApprovalDate) {
            this.ApprovalDate = ApprovalDate;
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

        public double getDays() {
            return Days;
        }

        public void setDays(double Days) {
            this.Days = Days;
        }

        public double getHours() {
            return Hours;
        }

        public void setHours(double Hours) {
            this.Hours = Hours;
        }

        public Object getAttachment() {
            return Attachment;
        }

        public void setAttachment(Object Attachment) {
            this.Attachment = Attachment;
        }

        public String getApprovalReason() {
            return ApprovalReason;
        }

        public void setApprovalReason(String ApprovalReason) {
            this.ApprovalReason = ApprovalReason;
        }

        public String getApprovalByName() {
            return ApprovalByName;
        }

        public void setApprovalByName(String ApprovalByName) {
            this.ApprovalByName = ApprovalByName;
        }

        public String getApprovalBy() {
            return ApprovalBy;
        }

        public void setApprovalBy(String ApprovalBy) {
            this.ApprovalBy = ApprovalBy;
        }

        public String getCreateByName() {
            return CreateByName;
        }

        public void setCreateByName(String CreateByName) {
            this.CreateByName = CreateByName;
        }

        public Object getCreatedDate() {
            return CreatedDate;
        }

        public void setCreatedDate(Object CreatedDate) {
            this.CreatedDate = CreatedDate;
        }

        public Object getUpdatedByName() {
            return UpdatedByName;
        }

        public void setUpdatedByName(Object UpdatedByName) {
            this.UpdatedByName = UpdatedByName;
        }

        public Object getUpdatedDate() {
            return UpdatedDate;
        }

        public void setUpdatedDate(Object UpdatedDate) {
            this.UpdatedDate = UpdatedDate;
        }

        public Object getDeleteMark() {
            return DeleteMark;
        }

        public void setDeleteMark(Object DeleteMark) {
            this.DeleteMark = DeleteMark;
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
