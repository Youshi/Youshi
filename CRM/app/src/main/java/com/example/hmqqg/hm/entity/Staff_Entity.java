package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by bona on 2016/4/25.
 */
public class Staff_Entity {

    /**
     * stamess : 获取生日信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * UserId : OP10108
     * UserName : zzz
     * Sex : 女
     * Brthday : 0001-01-01T00:00:00
     * Marriage : 0
     * Folk : 0
     * Native :
     * HkAddress :
     * Political : 0
     * DegreeNo :
     * DepCode : 1014
     * QuartersId : null
     * Blood : null
     * Health : null
     * Stature : 0
     * Avoirdupois : 0
     * BankNo : null
     * LeaderId : null
     * LeaderName : null
     * Handset : null
     * QqMsn : null
     * Email : null
     * Address : null
     * Postal : null
     * NewAddress : null
     * NewPostal : null
     * FamilyTel : null
     * FamilyRemark : null
     * School : null
     * FinishData : 0001-01-01T00:00:00
     * Specialty : null
     * Education : null
     * ForeignLang : null
     * Certificate : null
     * FlLevel : null
     * PthLevel : null
     * JsjLevel : null
     * JsjZs : null
     * Archives : null
     * Technical : null
     * WorkDate : 0001-01-01T00:00:00
     * JoinDate : 0001-01-01T00:00:00
     * AddService : 0
     * NewService : 0
     * Remark : null
     * Experience : null
     * Train : null
     * Other : null
     * WorkState : null
     * TenantId : null
     * CurrPhone : null
     * PhotoUrl : null
     * FullDate : 0001-01-01T00:00:00
     * LeaveDate : 0001-01-01T00:00:00
     * bloodName : null
     * MarriageName : null
     * FolkName : null
     * PoliticalName : null
     * wState : null
     * EducationName : null
     * DeptName : null
     * QuartersName : null
     * age : null
     * AddServiceYear : 0
     * NewServiceYear : 0
     * fBrthday : 2016-04-25
     * fFinishData : null
     * fWorkDate : null
     * fJoinDate : null
     * fFullDate : null
     * fLeaveDate : null
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
        private String UserId;
        private String UserName;
        private String Sex;
        private String Brthday;
        private String Marriage;
        private String Folk;
        private String Native;
        private String HkAddress;
        private String Political;
        private String DegreeNo;
        private String DepCode;
        private Object QuartersId;
        private Object Blood;
        private Object Health;
        private int Stature;
        private int Avoirdupois;
        private Object BankNo;
        private Object LeaderId;
        private Object LeaderName;
        private Object Handset;
        private Object QqMsn;
        private Object Email;
        private Object Address;
        private Object Postal;
        private Object NewAddress;
        private Object NewPostal;
        private Object FamilyTel;
        private Object FamilyRemark;
        private Object School;
        private String FinishData;
        private Object Specialty;
        private Object Education;
        private Object ForeignLang;
        private Object Certificate;
        private Object FlLevel;
        private Object PthLevel;
        private Object JsjLevel;
        private Object JsjZs;
        private Object Archives;
        private Object Technical;
        private String WorkDate;
        private String JoinDate;
        private int AddService;
        private int NewService;
        private Object Remark;
        private Object Experience;
        private Object Train;
        private Object Other;
        private Object WorkState;
        private Object TenantId;
        private Object CurrPhone;
        private Object PhotoUrl;
        private String FullDate;
        private String LeaveDate;
        private Object bloodName;
        private Object MarriageName;
        private Object FolkName;
        private Object PoliticalName;
        private Object wState;
        private Object EducationName;
        private Object DeptName;
        private Object QuartersName;
        private Object age;
        private int AddServiceYear;
        private int NewServiceYear;
        private String fBrthday;
        private Object fFinishData;
        private Object fWorkDate;
        private Object fJoinDate;
        private Object fFullDate;
        private Object fLeaveDate;

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public void setBrthday(String Brthday) {
            this.Brthday = Brthday;
        }

        public void setMarriage(String Marriage) {
            this.Marriage = Marriage;
        }

        public void setFolk(String Folk) {
            this.Folk = Folk;
        }

        public void setNative(String Native) {
            this.Native = Native;
        }

        public void setHkAddress(String HkAddress) {
            this.HkAddress = HkAddress;
        }

        public void setPolitical(String Political) {
            this.Political = Political;
        }

        public void setDegreeNo(String DegreeNo) {
            this.DegreeNo = DegreeNo;
        }

        public void setDepCode(String DepCode) {
            this.DepCode = DepCode;
        }

        public void setQuartersId(Object QuartersId) {
            this.QuartersId = QuartersId;
        }

        public void setBlood(Object Blood) {
            this.Blood = Blood;
        }

        public void setHealth(Object Health) {
            this.Health = Health;
        }

        public void setStature(int Stature) {
            this.Stature = Stature;
        }

        public void setAvoirdupois(int Avoirdupois) {
            this.Avoirdupois = Avoirdupois;
        }

        public void setBankNo(Object BankNo) {
            this.BankNo = BankNo;
        }

        public void setLeaderId(Object LeaderId) {
            this.LeaderId = LeaderId;
        }

        public void setLeaderName(Object LeaderName) {
            this.LeaderName = LeaderName;
        }

        public void setHandset(Object Handset) {
            this.Handset = Handset;
        }

        public void setQqMsn(Object QqMsn) {
            this.QqMsn = QqMsn;
        }

        public void setEmail(Object Email) {
            this.Email = Email;
        }

        public void setAddress(Object Address) {
            this.Address = Address;
        }

        public void setPostal(Object Postal) {
            this.Postal = Postal;
        }

        public void setNewAddress(Object NewAddress) {
            this.NewAddress = NewAddress;
        }

        public void setNewPostal(Object NewPostal) {
            this.NewPostal = NewPostal;
        }

        public void setFamilyTel(Object FamilyTel) {
            this.FamilyTel = FamilyTel;
        }

        public void setFamilyRemark(Object FamilyRemark) {
            this.FamilyRemark = FamilyRemark;
        }

        public void setSchool(Object School) {
            this.School = School;
        }

        public void setFinishData(String FinishData) {
            this.FinishData = FinishData;
        }

        public void setSpecialty(Object Specialty) {
            this.Specialty = Specialty;
        }

        public void setEducation(Object Education) {
            this.Education = Education;
        }

        public void setForeignLang(Object ForeignLang) {
            this.ForeignLang = ForeignLang;
        }

        public void setCertificate(Object Certificate) {
            this.Certificate = Certificate;
        }

        public void setFlLevel(Object FlLevel) {
            this.FlLevel = FlLevel;
        }

        public void setPthLevel(Object PthLevel) {
            this.PthLevel = PthLevel;
        }

        public void setJsjLevel(Object JsjLevel) {
            this.JsjLevel = JsjLevel;
        }

        public void setJsjZs(Object JsjZs) {
            this.JsjZs = JsjZs;
        }

        public void setArchives(Object Archives) {
            this.Archives = Archives;
        }

        public void setTechnical(Object Technical) {
            this.Technical = Technical;
        }

        public void setWorkDate(String WorkDate) {
            this.WorkDate = WorkDate;
        }

        public void setJoinDate(String JoinDate) {
            this.JoinDate = JoinDate;
        }

        public void setAddService(int AddService) {
            this.AddService = AddService;
        }

        public void setNewService(int NewService) {
            this.NewService = NewService;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public void setExperience(Object Experience) {
            this.Experience = Experience;
        }

        public void setTrain(Object Train) {
            this.Train = Train;
        }

        public void setOther(Object Other) {
            this.Other = Other;
        }

        public void setWorkState(Object WorkState) {
            this.WorkState = WorkState;
        }

        public void setTenantId(Object TenantId) {
            this.TenantId = TenantId;
        }

        public void setCurrPhone(Object CurrPhone) {
            this.CurrPhone = CurrPhone;
        }

        public void setPhotoUrl(Object PhotoUrl) {
            this.PhotoUrl = PhotoUrl;
        }

        public void setFullDate(String FullDate) {
            this.FullDate = FullDate;
        }

        public void setLeaveDate(String LeaveDate) {
            this.LeaveDate = LeaveDate;
        }

        public void setBloodName(Object bloodName) {
            this.bloodName = bloodName;
        }

        public void setMarriageName(Object MarriageName) {
            this.MarriageName = MarriageName;
        }

        public void setFolkName(Object FolkName) {
            this.FolkName = FolkName;
        }

        public void setPoliticalName(Object PoliticalName) {
            this.PoliticalName = PoliticalName;
        }

        public void setWState(Object wState) {
            this.wState = wState;
        }

        public void setEducationName(Object EducationName) {
            this.EducationName = EducationName;
        }

        public void setDeptName(Object DeptName) {
            this.DeptName = DeptName;
        }

        public void setQuartersName(Object QuartersName) {
            this.QuartersName = QuartersName;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public void setAddServiceYear(int AddServiceYear) {
            this.AddServiceYear = AddServiceYear;
        }

        public void setNewServiceYear(int NewServiceYear) {
            this.NewServiceYear = NewServiceYear;
        }

        public void setFBrthday(String fBrthday) {
            this.fBrthday = fBrthday;
        }

        public void setFFinishData(Object fFinishData) {
            this.fFinishData = fFinishData;
        }

        public void setFWorkDate(Object fWorkDate) {
            this.fWorkDate = fWorkDate;
        }

        public void setFJoinDate(Object fJoinDate) {
            this.fJoinDate = fJoinDate;
        }

        public void setFFullDate(Object fFullDate) {
            this.fFullDate = fFullDate;
        }

        public void setFLeaveDate(Object fLeaveDate) {
            this.fLeaveDate = fLeaveDate;
        }

        public String getUserId() {
            return UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public String getSex() {
            return Sex;
        }

        public String getBrthday() {
            return Brthday;
        }

        public String getMarriage() {
            return Marriage;
        }

        public String getFolk() {
            return Folk;
        }

        public String getNative() {
            return Native;
        }

        public String getHkAddress() {
            return HkAddress;
        }

        public String getPolitical() {
            return Political;
        }

        public String getDegreeNo() {
            return DegreeNo;
        }

        public String getDepCode() {
            return DepCode;
        }

        public Object getQuartersId() {
            return QuartersId;
        }

        public Object getBlood() {
            return Blood;
        }

        public Object getHealth() {
            return Health;
        }

        public int getStature() {
            return Stature;
        }

        public int getAvoirdupois() {
            return Avoirdupois;
        }

        public Object getBankNo() {
            return BankNo;
        }

        public Object getLeaderId() {
            return LeaderId;
        }

        public Object getLeaderName() {
            return LeaderName;
        }

        public Object getHandset() {
            return Handset;
        }

        public Object getQqMsn() {
            return QqMsn;
        }

        public Object getEmail() {
            return Email;
        }

        public Object getAddress() {
            return Address;
        }

        public Object getPostal() {
            return Postal;
        }

        public Object getNewAddress() {
            return NewAddress;
        }

        public Object getNewPostal() {
            return NewPostal;
        }

        public Object getFamilyTel() {
            return FamilyTel;
        }

        public Object getFamilyRemark() {
            return FamilyRemark;
        }

        public Object getSchool() {
            return School;
        }

        public String getFinishData() {
            return FinishData;
        }

        public Object getSpecialty() {
            return Specialty;
        }

        public Object getEducation() {
            return Education;
        }

        public Object getForeignLang() {
            return ForeignLang;
        }

        public Object getCertificate() {
            return Certificate;
        }

        public Object getFlLevel() {
            return FlLevel;
        }

        public Object getPthLevel() {
            return PthLevel;
        }

        public Object getJsjLevel() {
            return JsjLevel;
        }

        public Object getJsjZs() {
            return JsjZs;
        }

        public Object getArchives() {
            return Archives;
        }

        public Object getTechnical() {
            return Technical;
        }

        public String getWorkDate() {
            return WorkDate;
        }

        public String getJoinDate() {
            return JoinDate;
        }

        public int getAddService() {
            return AddService;
        }

        public int getNewService() {
            return NewService;
        }

        public Object getRemark() {
            return Remark;
        }

        public Object getExperience() {
            return Experience;
        }

        public Object getTrain() {
            return Train;
        }

        public Object getOther() {
            return Other;
        }

        public Object getWorkState() {
            return WorkState;
        }

        public Object getTenantId() {
            return TenantId;
        }

        public Object getCurrPhone() {
            return CurrPhone;
        }

        public Object getPhotoUrl() {
            return PhotoUrl;
        }

        public String getFullDate() {
            return FullDate;
        }

        public String getLeaveDate() {
            return LeaveDate;
        }

        public Object getBloodName() {
            return bloodName;
        }

        public Object getMarriageName() {
            return MarriageName;
        }

        public Object getFolkName() {
            return FolkName;
        }

        public Object getPoliticalName() {
            return PoliticalName;
        }

        public Object getWState() {
            return wState;
        }

        public Object getEducationName() {
            return EducationName;
        }

        public Object getDeptName() {
            return DeptName;
        }

        public Object getQuartersName() {
            return QuartersName;
        }

        public Object getAge() {
            return age;
        }

        public int getAddServiceYear() {
            return AddServiceYear;
        }

        public int getNewServiceYear() {
            return NewServiceYear;
        }

        public String getFBrthday() {
            return fBrthday;
        }

        public Object getFFinishData() {
            return fFinishData;
        }

        public Object getFWorkDate() {
            return fWorkDate;
        }

        public Object getFJoinDate() {
            return fJoinDate;
        }

        public Object getFFullDate() {
            return fFullDate;
        }

        public Object getFLeaveDate() {
            return fLeaveDate;
        }
    }
}
