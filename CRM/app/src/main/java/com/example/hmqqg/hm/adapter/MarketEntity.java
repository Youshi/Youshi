package com.example.hmqqg.hm.adapter;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class MarketEntity {


    /**
     * stamess : 获取活动信息请求成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * ActId : 1
     * ActTitle : 拉拉
     * ActType :
     * ActTypeName :
     * FzUserId :
     * FzUserName :
     * Charge : null
     * ActAddr : 烟台
     * ActDate : 2016-04-25T00:00:00
     * ActPurpose : 钱
     * ActObject : 0
     * TeamLeader :
     * ActSpeaker :
     * ActControl :
     * ActMaterial : 1
     * IsSupport : true
     * FilePath : /UploadFile/photo/OP10001.doc
     * CreatedDate : 2016-04-25T00:00:00
     * CreatedBy : undefined
     * ApplyedBy :
     * ApplyedDate : null
     * ApplyedMess :
     * FLAG :
     * UpdateDate : null
     * UpdatedBy :
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
        private int ActId;
        private String ActTitle;
        private String ActType;
        private String ActTypeName;
        private String FzUserId;
        private String FzUserName;
        private Object Charge;
        private String ActAddr;
        private String ActDate;
        private String ActPurpose;
        private String ActObject;
        private String TeamLeader;
        private String ActSpeaker;
        private String ActControl;
        private String ActMaterial;
        private boolean IsSupport;
        private String FilePath;
        private String CreatedDate;
        private String CreatedBy;
        private String ApplyedBy;
        private Object ApplyedDate;
        private String ApplyedMess;
        private String FLAG;
        private Object UpdateDate;
        private String UpdatedBy;

        public void setActId(int ActId) {
            this.ActId = ActId;
        }

        public void setActTitle(String ActTitle) {
            this.ActTitle = ActTitle;
        }

        public void setActType(String ActType) {
            this.ActType = ActType;
        }

        public void setActTypeName(String ActTypeName) {
            this.ActTypeName = ActTypeName;
        }

        public void setFzUserId(String FzUserId) {
            this.FzUserId = FzUserId;
        }

        public void setFzUserName(String FzUserName) {
            this.FzUserName = FzUserName;
        }

        public void setCharge(Object Charge) {
            this.Charge = Charge;
        }

        public void setActAddr(String ActAddr) {
            this.ActAddr = ActAddr;
        }

        public void setActDate(String ActDate) {
            this.ActDate = ActDate;
        }

        public void setActPurpose(String ActPurpose) {
            this.ActPurpose = ActPurpose;
        }

        public void setActObject(String ActObject) {
            this.ActObject = ActObject;
        }

        public void setTeamLeader(String TeamLeader) {
            this.TeamLeader = TeamLeader;
        }

        public void setActSpeaker(String ActSpeaker) {
            this.ActSpeaker = ActSpeaker;
        }

        public void setActControl(String ActControl) {
            this.ActControl = ActControl;
        }

        public void setActMaterial(String ActMaterial) {
            this.ActMaterial = ActMaterial;
        }

        public void setIsSupport(boolean IsSupport) {
            this.IsSupport = IsSupport;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public void setCreatedDate(String CreatedDate) {
            this.CreatedDate = CreatedDate;
        }

        public void setCreatedBy(String CreatedBy) {
            this.CreatedBy = CreatedBy;
        }

        public void setApplyedBy(String ApplyedBy) {
            this.ApplyedBy = ApplyedBy;
        }

        public void setApplyedDate(Object ApplyedDate) {
            this.ApplyedDate = ApplyedDate;
        }

        public void setApplyedMess(String ApplyedMess) {
            this.ApplyedMess = ApplyedMess;
        }

        public void setFLAG(String FLAG) {
            this.FLAG = FLAG;
        }

        public void setUpdateDate(Object UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public void setUpdatedBy(String UpdatedBy) {
            this.UpdatedBy = UpdatedBy;
        }

        public int getActId() {
            return ActId;
        }

        public String getActTitle() {
            return ActTitle;
        }

        public String getActType() {
            return ActType;
        }

        public String getActTypeName() {
            return ActTypeName;
        }

        public String getFzUserId() {
            return FzUserId;
        }

        public String getFzUserName() {
            return FzUserName;
        }

        public Object getCharge() {
            return Charge;
        }

        public String getActAddr() {
            return ActAddr;
        }

        public String getActDate() {
            return ActDate;
        }

        public String getActPurpose() {
            return ActPurpose;
        }

        public String getActObject() {
            return ActObject;
        }

        public String getTeamLeader() {
            return TeamLeader;
        }

        public String getActSpeaker() {
            return ActSpeaker;
        }

        public String getActControl() {
            return ActControl;
        }

        public String getActMaterial() {
            return ActMaterial;
        }

        public boolean isIsSupport() {
            return IsSupport;
        }

        public String getFilePath() {
            return FilePath;
        }

        public String getCreatedDate() {
            return CreatedDate;
        }

        public String getCreatedBy() {
            return CreatedBy;
        }

        public String getApplyedBy() {
            return ApplyedBy;
        }

        public Object getApplyedDate() {
            return ApplyedDate;
        }

        public String getApplyedMess() {
            return ApplyedMess;
        }

        public String getFLAG() {
            return FLAG;
        }

        public Object getUpdateDate() {
            return UpdateDate;
        }

        public String getUpdatedBy() {
            return UpdatedBy;
        }
    }
}
