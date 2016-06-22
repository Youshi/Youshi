package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class FiletEntity {


    /**
     * stamess : 获取文件列表信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * fileId : 15
     * folderId : 13010822371896
     * fileName : sfz.jpg
     * filePath : UploadFile/files/管理员/学习资料
     * expandName : jpg
     * fileSize : 44049
     * remark : null
     * isDownload : true
     * secret : null
     * userId : OP10001
     * userName : 管理员
     * uploadDate : 2013-01-09T13:35:18
     * IsShare : false
     * shareUserId : null
     * shareUserName : null
     * sharePower : 0
     * uploadPws : null
     * overDate : 2013-01-09T13:35:18
     * linkUrl : null
     * content : null
     * isTran : false
     * pfileId : 0
     * editdate : null
     * tempeditDate : 2013-01-09 13:35
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
        private int fileId;
        private String folderId;
        private String fileName;
        private String filePath;
        private String expandName;
        private int fileSize;
        private Object remark;
        private boolean isDownload;
        private Object secret;
        private String userId;
        private String userName;
        private String uploadDate;
        private boolean IsShare;
        private Object shareUserId;
        private Object shareUserName;
        private int sharePower;
        private Object uploadPws;
        private String overDate;
        private Object linkUrl;
        private Object content;
        private boolean isTran;
        private int pfileId;
        private Object editdate;
        private String tempeditDate;

        public void setFileId(int fileId) {
            this.fileId = fileId;
        }

        public void setFolderId(String folderId) {
            this.folderId = folderId;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public void setExpandName(String expandName) {
            this.expandName = expandName;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public void setIsDownload(boolean isDownload) {
            this.isDownload = isDownload;
        }

        public void setSecret(Object secret) {
            this.secret = secret;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setUploadDate(String uploadDate) {
            this.uploadDate = uploadDate;
        }

        public void setIsShare(boolean IsShare) {
            this.IsShare = IsShare;
        }

        public void setShareUserId(Object shareUserId) {
            this.shareUserId = shareUserId;
        }

        public void setShareUserName(Object shareUserName) {
            this.shareUserName = shareUserName;
        }

        public void setSharePower(int sharePower) {
            this.sharePower = sharePower;
        }

        public void setUploadPws(Object uploadPws) {
            this.uploadPws = uploadPws;
        }

        public void setOverDate(String overDate) {
            this.overDate = overDate;
        }

        public void setLinkUrl(Object linkUrl) {
            this.linkUrl = linkUrl;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public void setIsTran(boolean isTran) {
            this.isTran = isTran;
        }

        public void setPfileId(int pfileId) {
            this.pfileId = pfileId;
        }

        public void setEditdate(Object editdate) {
            this.editdate = editdate;
        }

        public void setTempeditDate(String tempeditDate) {
            this.tempeditDate = tempeditDate;
        }

        public int getFileId() {
            return fileId;
        }

        public String getFolderId() {
            return folderId;
        }

        public String getFileName() {
            return fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public String getExpandName() {
            return expandName;
        }

        public int getFileSize() {
            return fileSize;
        }

        public Object getRemark() {
            return remark;
        }

        public boolean isIsDownload() {
            return isDownload;
        }

        public Object getSecret() {
            return secret;
        }

        public String getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getUploadDate() {
            return uploadDate;
        }

        public boolean isIsShare() {
            return IsShare;
        }

        public Object getShareUserId() {
            return shareUserId;
        }

        public Object getShareUserName() {
            return shareUserName;
        }

        public int getSharePower() {
            return sharePower;
        }

        public Object getUploadPws() {
            return uploadPws;
        }

        public String getOverDate() {
            return overDate;
        }

        public Object getLinkUrl() {
            return linkUrl;
        }

        public Object getContent() {
            return content;
        }

        public boolean isIsTran() {
            return isTran;
        }

        public int getPfileId() {
            return pfileId;
        }

        public Object getEditdate() {
            return editdate;
        }

        public String getTempeditDate() {
            return tempeditDate;
        }
    }
}
