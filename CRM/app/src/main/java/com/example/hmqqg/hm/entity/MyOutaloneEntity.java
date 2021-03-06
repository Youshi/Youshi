package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 我的外出单  容器
 * Created by Administrator on 2016/5/3.
 */
public class MyOutaloneEntity {


    /**
     * stamess : 获取外出信息成功，请假如下！
     * staval : 0
     */

    private List<StatusEntity> status;
    /**
     * Row : 1
     * ApprovalID : 40
     * FlowID : null
     * flag : 空值
     * DeptName : 京西票号
     * DeptID : 1014
     * AppTypeName : null
     * ApprovalType : 客户要约
     * ApprovalTitle : hhh
     * ApprovalDate : null
     * StartDate : 2016-05-06T00:00:00
     * EndDate : 2016-05-07T00:00:00
     * Days : 1.0
     * Hours : 0.0
     * Attachment : \UploadFile\picture/201605161511066134.jpg
     * ApprovalReason : gvjk
     * AppRovalByName : 万权
     * ApprovalBy : OP10086
     * CreateByName : 万权
     * addr : null
     * CreatedDate : null
     * UpdatedByName : 万权
     * UpdatedDate : 2016-05-16T00:00:00
     * DeleteMark : null
     * PROCID : 17
     * OperateFlag : 未审批
     * OperateByName : 管理员
     * OperatedBy : op10001
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
        private String Attachment;
        private String ApprovalReason;
        private String AppRovalByName;
        private String ApprovalBy;
        private String CreateByName;
        private Object addr;
        private Object CreatedDate;
        private String UpdatedByName;
        private String UpdatedDate;
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

        public void setAttachment(String Attachment) {
            this.Attachment = Attachment;
        }

        public void setApprovalReason(String ApprovalReason) {
            this.ApprovalReason = ApprovalReason;
        }

        public void setAppRovalByName(String AppRovalByName) {
            this.AppRovalByName = AppRovalByName;
        }

        public void setApprovalBy(String ApprovalBy) {
            this.ApprovalBy = ApprovalBy;
        }

        public void setCreateByName(String CreateByName) {
            this.CreateByName = CreateByName;
        }

        public void setAddr(Object addr) {
            this.addr = addr;
        }

        public void setCreatedDate(Object CreatedDate) {
            this.CreatedDate = CreatedDate;
        }

        public void setUpdatedByName(String UpdatedByName) {
            this.UpdatedByName = UpdatedByName;
        }

        public void setUpdatedDate(String UpdatedDate) {
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

        public String getAttachment() {
            return Attachment;
        }

        public String getApprovalReason() {
            return ApprovalReason;
        }

        public String getAppRovalByName() {
            return AppRovalByName;
        }

        public String getApprovalBy() {
            return ApprovalBy;
        }

        public String getCreateByName() {
            return CreateByName;
        }

        public Object getAddr() {
            return addr;
        }

        public Object getCreatedDate() {
            return CreatedDate;
        }

        public String getUpdatedByName() {
            return UpdatedByName;
        }

        public String getUpdatedDate() {
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
