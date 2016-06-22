package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 客户详细信息实体类
 * Created by tao on 2016/2/29.
 */
public class Add_UmInformationEntity {


    /**
     * UserId : KH16000076
     * UserName : 测试客户
     * UserType :
     * UserSex : undefined
     * BirthDate : 2016/03/08
     * ValueEvaluate : null
     * CreditGrade : null
     * UserSort : ZL001
     * UserSortName : 潜在
     * Trade : KHHY001
     * TradeName : 工业
     * UserGrade : DJ001
     * UserGradeName : 强烈
     * PersScale :
     * PersScaleName :
     * Origin : LY001
     * OriginName : 讲座
     * Moment :
     * MomentName :
     * Company :
     * Address :
     * Post :
     * Phone : 15650112591
     * NetAddress :
     * Fax :
     * Email :
     * DgAddress1 : null
     * DgAddress2 : null
     * Remark :
     * OperatorId : OP10001
     * OperatorName : 管理员
     * CreateDate : 0001-01-01T00:00:00
     * UpdateUserId : null
     * UpdateUserName : null
     * UpdateDate : 0001-01-01T00:00:00
     * FuKuanFs :
     * FuKuanFsName :
     * ZheKouLv : 0.0
     * ZhangQi : 0
     * XinYunEd : 0.0
     * NaSuiNo : null
     * DefaultTax : 0.0
     * KaiPiaoType : null
     * KaiPiaoTypeName : null
     * Bank : null
     * Account : null
     * TZPH :
     * TZNL : 低（50万以下）
     * TZQD : P2P
     * TZNLVAL : tznl01
     * TZQDVAL : TZQD01
     * CertifiyType : ZJ01
     * CertifiyNO : 152103198002280970
     * Duty : null
     * JJR : null
     * qq :
     * WeiXin :
     * OfficeTel :
     * HomeTel : null
     * pUserName : null
     * MarriType : HY001
     * ContactName : 张晓
     * ContactTel : 13176312591
     * RelaType : RYGX01
     * Call : 1
     * DeptCode :
     * OwnerID : null
     */

    private DetailInfoEntity DetailInfo;
    /**
     * stamess : 客户信息请求成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * status : [{"stamess":"客户信息请求成功！","staval":"1"}]
     * DetailInfo : {"UserId":"KH16000076","UserName":"测试客户","UserType":"","UserSex":"undefined","BirthDate":"2016/03/08","ValueEvaluate":null,"CreditGrade":null,"UserSort":"ZL001","UserSortName":"潜在","Trade":"KHHY001","TradeName":"工业","UserGrade":"DJ001","UserGradeName":"强烈","PersScale":"","PersScaleName":"","Origin":"LY001","OriginName":"讲座","Moment":"","MomentName":"","Company":"","Address":"","Post":"","Phone":"15650112591","NetAddress":"","Fax":"","Email":"","DgAddress1":null,"DgAddress2":null,"Remark":"","OperatorId":"OP10001","OperatorName":"管理员","CreateDate":"0001-01-01T00:00:00","UpdateUserId":null,"UpdateUserName":null,"UpdateDate":"0001-01-01T00:00:00","FuKuanFs":"","FuKuanFsName":"","ZheKouLv":0,"ZhangQi":0,"XinYunEd":0,"NaSuiNo":null,"DefaultTax":0,"KaiPiaoType":null,"KaiPiaoTypeName":null,"Bank":null,"Account":null,"TZPH":"","TZNL":"低（50万以下）","TZQD":"P2P","TZNLVAL":"tznl01","TZQDVAL":"TZQD01","CertifiyType":"ZJ01","CertifiyNO":"152103198002280970","Duty":null,"JJR":null,"qq":"","WeiXin":"","OfficeTel":"","HomeTel":null,"pUserName":null,"MarriType":"HY001","ContactName":"张晓","ContactTel":"13176312591","RelaType":"RYGX01","Call":"1","DeptCode":"","OwnerID":null}
     * Contacts : []
     */

    private List<?> Contacts;

    public void setDetailInfo(DetailInfoEntity DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public void setContacts(List<?> Contacts) {
        this.Contacts = Contacts;
    }

    public DetailInfoEntity getDetailInfo() {
        return DetailInfo;
    }

    public List<StatusEntity> getStatus() {
        return status;
    }

    public List<?> getContacts() {
        return Contacts;
    }

    public static class DetailInfoEntity {
        private String UserId;
        private String UserName;
        private String UserType;
        private String UserSex;
        private String BirthDate;
        private Object ValueEvaluate;
        private Object CreditGrade;
        private String UserSort;
        private String UserSortName;
        private String Trade;
        private String TradeName;
        private String UserGrade;
        private String UserGradeName;
        private String PersScale;
        private String PersScaleName;
        private String Origin;
        private String OriginName;
        private String Moment;
        private String MomentName;
        private String Company;
        private String Address;
        private String Post;
        private String Phone;
        private String NetAddress;
        private String Fax;
        private String Email;
        private Object DgAddress1;
        private Object DgAddress2;
        private String Remark;
        private String OperatorId;
        private String OperatorName;
        private String CreateDate;
        private Object UpdateUserId;
        private Object UpdateUserName;
        private String UpdateDate;
        private String FuKuanFs;
        private String FuKuanFsName;
        private double ZheKouLv;
        private int ZhangQi;
        private double XinYunEd;
        private Object NaSuiNo;
        private double DefaultTax;
        private Object KaiPiaoType;
        private Object KaiPiaoTypeName;
        private Object Bank;
        private Object Account;
        private String TZPH;
        private String TZNL;
        private String TZQD;
        private String TZNLVAL;
        private String TZQDVAL;
        private String CertifiyType;
        private String CertifiyNO;
        private Object Duty;
        private Object JJR;
        private String qq;
        private String WeiXin;
        private String OfficeTel;
        private Object HomeTel;
        private Object pUserName;
        private String MarriType;
        private String ContactName;
        private String ContactTel;
        private String RelaType;
        private String Call;
        private String DeptCode;
        private Object OwnerID;

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setUserType(String UserType) {
            this.UserType = UserType;
        }

        public void setUserSex(String UserSex) {
            this.UserSex = UserSex;
        }

        public void setBirthDate(String BirthDate) {
            this.BirthDate = BirthDate;
        }

        public void setValueEvaluate(Object ValueEvaluate) {
            this.ValueEvaluate = ValueEvaluate;
        }

        public void setCreditGrade(Object CreditGrade) {
            this.CreditGrade = CreditGrade;
        }

        public void setUserSort(String UserSort) {
            this.UserSort = UserSort;
        }

        public void setUserSortName(String UserSortName) {
            this.UserSortName = UserSortName;
        }

        public void setTrade(String Trade) {
            this.Trade = Trade;
        }

        public void setTradeName(String TradeName) {
            this.TradeName = TradeName;
        }

        public void setUserGrade(String UserGrade) {
            this.UserGrade = UserGrade;
        }

        public void setUserGradeName(String UserGradeName) {
            this.UserGradeName = UserGradeName;
        }

        public void setPersScale(String PersScale) {
            this.PersScale = PersScale;
        }

        public void setPersScaleName(String PersScaleName) {
            this.PersScaleName = PersScaleName;
        }

        public void setOrigin(String Origin) {
            this.Origin = Origin;
        }

        public void setOriginName(String OriginName) {
            this.OriginName = OriginName;
        }

        public void setMoment(String Moment) {
            this.Moment = Moment;
        }

        public void setMomentName(String MomentName) {
            this.MomentName = MomentName;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public void setPost(String Post) {
            this.Post = Post;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public void setNetAddress(String NetAddress) {
            this.NetAddress = NetAddress;
        }

        public void setFax(String Fax) {
            this.Fax = Fax;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public void setDgAddress1(Object DgAddress1) {
            this.DgAddress1 = DgAddress1;
        }

        public void setDgAddress2(Object DgAddress2) {
            this.DgAddress2 = DgAddress2;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public void setOperatorId(String OperatorId) {
            this.OperatorId = OperatorId;
        }

        public void setOperatorName(String OperatorName) {
            this.OperatorName = OperatorName;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public void setUpdateUserId(Object UpdateUserId) {
            this.UpdateUserId = UpdateUserId;
        }

        public void setUpdateUserName(Object UpdateUserName) {
            this.UpdateUserName = UpdateUserName;
        }

        public void setUpdateDate(String UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public void setFuKuanFs(String FuKuanFs) {
            this.FuKuanFs = FuKuanFs;
        }

        public void setFuKuanFsName(String FuKuanFsName) {
            this.FuKuanFsName = FuKuanFsName;
        }

        public void setZheKouLv(double ZheKouLv) {
            this.ZheKouLv = ZheKouLv;
        }

        public void setZhangQi(int ZhangQi) {
            this.ZhangQi = ZhangQi;
        }

        public void setXinYunEd(double XinYunEd) {
            this.XinYunEd = XinYunEd;
        }

        public void setNaSuiNo(Object NaSuiNo) {
            this.NaSuiNo = NaSuiNo;
        }

        public void setDefaultTax(double DefaultTax) {
            this.DefaultTax = DefaultTax;
        }

        public void setKaiPiaoType(Object KaiPiaoType) {
            this.KaiPiaoType = KaiPiaoType;
        }

        public void setKaiPiaoTypeName(Object KaiPiaoTypeName) {
            this.KaiPiaoTypeName = KaiPiaoTypeName;
        }

        public void setBank(Object Bank) {
            this.Bank = Bank;
        }

        public void setAccount(Object Account) {
            this.Account = Account;
        }

        public void setTZPH(String TZPH) {
            this.TZPH = TZPH;
        }

        public void setTZNL(String TZNL) {
            this.TZNL = TZNL;
        }

        public void setTZQD(String TZQD) {
            this.TZQD = TZQD;
        }

        public void setTZNLVAL(String TZNLVAL) {
            this.TZNLVAL = TZNLVAL;
        }

        public void setTZQDVAL(String TZQDVAL) {
            this.TZQDVAL = TZQDVAL;
        }

        public void setCertifiyType(String CertifiyType) {
            this.CertifiyType = CertifiyType;
        }

        public void setCertifiyNO(String CertifiyNO) {
            this.CertifiyNO = CertifiyNO;
        }

        public void setDuty(Object Duty) {
            this.Duty = Duty;
        }

        public void setJJR(Object JJR) {
            this.JJR = JJR;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setWeiXin(String WeiXin) {
            this.WeiXin = WeiXin;
        }

        public void setOfficeTel(String OfficeTel) {
            this.OfficeTel = OfficeTel;
        }

        public void setHomeTel(Object HomeTel) {
            this.HomeTel = HomeTel;
        }

        public void setPUserName(Object pUserName) {
            this.pUserName = pUserName;
        }

        public void setMarriType(String MarriType) {
            this.MarriType = MarriType;
        }

        public void setContactName(String ContactName) {
            this.ContactName = ContactName;
        }

        public void setContactTel(String ContactTel) {
            this.ContactTel = ContactTel;
        }

        public void setRelaType(String RelaType) {
            this.RelaType = RelaType;
        }

        public void setCall(String Call) {
            this.Call = Call;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public void setOwnerID(Object OwnerID) {
            this.OwnerID = OwnerID;
        }

        public String getUserId() {
            return UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public String getUserType() {
            return UserType;
        }

        public String getUserSex() {
            return UserSex;
        }

        public String getBirthDate() {
            return BirthDate;
        }

        public Object getValueEvaluate() {
            return ValueEvaluate;
        }

        public Object getCreditGrade() {
            return CreditGrade;
        }

        public String getUserSort() {
            return UserSort;
        }

        public String getUserSortName() {
            return UserSortName;
        }

        public String getTrade() {
            return Trade;
        }

        public String getTradeName() {
            return TradeName;
        }

        public String getUserGrade() {
            return UserGrade;
        }

        public String getUserGradeName() {
            return UserGradeName;
        }

        public String getPersScale() {
            return PersScale;
        }

        public String getPersScaleName() {
            return PersScaleName;
        }

        public String getOrigin() {
            return Origin;
        }

        public String getOriginName() {
            return OriginName;
        }

        public String getMoment() {
            return Moment;
        }

        public String getMomentName() {
            return MomentName;
        }

        public String getCompany() {
            return Company;
        }

        public String getAddress() {
            return Address;
        }

        public String getPost() {
            return Post;
        }

        public String getPhone() {
            return Phone;
        }

        public String getNetAddress() {
            return NetAddress;
        }

        public String getFax() {
            return Fax;
        }

        public String getEmail() {
            return Email;
        }

        public Object getDgAddress1() {
            return DgAddress1;
        }

        public Object getDgAddress2() {
            return DgAddress2;
        }

        public String getRemark() {
            return Remark;
        }

        public String getOperatorId() {
            return OperatorId;
        }

        public String getOperatorName() {
            return OperatorName;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public Object getUpdateUserId() {
            return UpdateUserId;
        }

        public Object getUpdateUserName() {
            return UpdateUserName;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public String getFuKuanFs() {
            return FuKuanFs;
        }

        public String getFuKuanFsName() {
            return FuKuanFsName;
        }

        public double getZheKouLv() {
            return ZheKouLv;
        }

        public int getZhangQi() {
            return ZhangQi;
        }

        public double getXinYunEd() {
            return XinYunEd;
        }

        public Object getNaSuiNo() {
            return NaSuiNo;
        }

        public double getDefaultTax() {
            return DefaultTax;
        }

        public Object getKaiPiaoType() {
            return KaiPiaoType;
        }

        public Object getKaiPiaoTypeName() {
            return KaiPiaoTypeName;
        }

        public Object getBank() {
            return Bank;
        }

        public Object getAccount() {
            return Account;
        }

        public String getTZPH() {
            return TZPH;
        }

        public String getTZNL() {
            return TZNL;
        }

        public String getTZQD() {
            return TZQD;
        }

        public String getTZNLVAL() {
            return TZNLVAL;
        }

        public String getTZQDVAL() {
            return TZQDVAL;
        }

        public String getCertifiyType() {
            return CertifiyType;
        }

        public String getCertifiyNO() {
            return CertifiyNO;
        }

        public Object getDuty() {
            return Duty;
        }

        public Object getJJR() {
            return JJR;
        }

        public String getQq() {
            return qq;
        }

        public String getWeiXin() {
            return WeiXin;
        }

        public String getOfficeTel() {
            return OfficeTel;
        }

        public Object getHomeTel() {
            return HomeTel;
        }

        public Object getPUserName() {
            return pUserName;
        }

        public String getMarriType() {
            return MarriType;
        }

        public String getContactName() {
            return ContactName;
        }

        public String getContactTel() {
            return ContactTel;
        }

        public String getRelaType() {
            return RelaType;
        }

        public String getCall() {
            return Call;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public Object getOwnerID() {
            return OwnerID;
        }
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
}
