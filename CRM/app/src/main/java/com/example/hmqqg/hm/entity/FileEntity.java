package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by bona on 2016/3/10.
 */
public class FileEntity {

    /**
     * stamess : 获取公司文档分类信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * folderId : 13010820103087
     * parentId : comspace
     * folderName : 企业文化
     * sortId : comspace
     * withUser : All
     * isShare : false
     * shareUserId :
     * shareUserName :
     * showXh : 1
     * remark : 价值观和使命
     * uploadPws : null
     * overDate : 0001-01-01T00:00:00
     * userId : null
     * userName : null
     * folderPath : null
     * createDate : 0001-01-01T00:00:00
     * SonCount : 0
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
        private String folderId;
        private String parentId;
        private String folderName;
        private String sortId;
        private String withUser;
        private boolean isShare;
        private String shareUserId;
        private String shareUserName;
        private int showXh;
        private String remark;
        private Object uploadPws;
        private String overDate;
        private Object userId;
        private Object userName;
        private Object folderPath;
        private String createDate;
        private int SonCount;

        public void setFolderId(String folderId) {
            this.folderId = folderId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public void setFolderName(String folderName) {
            this.folderName = folderName;
        }

        public void setSortId(String sortId) {
            this.sortId = sortId;
        }

        public void setWithUser(String withUser) {
            this.withUser = withUser;
        }

        public void setIsShare(boolean isShare) {
            this.isShare = isShare;
        }

        public void setShareUserId(String shareUserId) {
            this.shareUserId = shareUserId;
        }

        public void setShareUserName(String shareUserName) {
            this.shareUserName = shareUserName;
        }

        public void setShowXh(int showXh) {
            this.showXh = showXh;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setUploadPws(Object uploadPws) {
            this.uploadPws = uploadPws;
        }

        public void setOverDate(String overDate) {
            this.overDate = overDate;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public void setFolderPath(Object folderPath) {
            this.folderPath = folderPath;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setSonCount(int SonCount) {
            this.SonCount = SonCount;
        }

        public String getFolderId() {
            return folderId;
        }

        public String getParentId() {
            return parentId;
        }

        public String getFolderName() {
            return folderName;
        }

        public String getSortId() {
            return sortId;
        }

        public String getWithUser() {
            return withUser;
        }

        public boolean isIsShare() {
            return isShare;
        }

        public String getShareUserId() {
            return shareUserId;
        }

        public String getShareUserName() {
            return shareUserName;
        }

        public int getShowXh() {
            return showXh;
        }

        public String getRemark() {
            return remark;
        }

        public Object getUploadPws() {
            return uploadPws;
        }

        public String getOverDate() {
            return overDate;
        }

        public Object getUserId() {
            return userId;
        }

        public Object getUserName() {
            return userName;
        }

        public Object getFolderPath() {
            return folderPath;
        }

        public String getCreateDate() {
            return createDate;
        }

        public int getSonCount() {
            return SonCount;
        }
    }
}
