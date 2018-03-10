package com.app.cleartv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

@SerializedName("odata.metadata")
@Expose
private String odataMetadata;
@SerializedName("ApplicantId")
@Expose
private Integer applicantId;
@SerializedName("Title")
@Expose
private String title;
@SerializedName("ApplicantName")
@Expose
private String applicantName;
@SerializedName("Gender")
@Expose
private Integer gender;
@SerializedName("ApplicantPhoto")
@Expose
private String applicantPhoto;
@SerializedName("PHouseNo")
@Expose
private Object pHouseNo;
@SerializedName("PWardNo")
@Expose
private Object pWardNo;
@SerializedName("PStreetName")
@Expose
private String pStreetName;
@SerializedName("PVdcMunicipality")
@Expose
private String pVdcMunicipality;
@SerializedName("PDistrict")
@Expose
private String pDistrict;
@SerializedName("PZone")
@Expose
private Object pZone;
@SerializedName("PCountry")
@Expose
private Object pCountry;
@SerializedName("ContactNo")
@Expose
private String contactNo;
@SerializedName("Email")
@Expose
private Object email;
@SerializedName("ApplicantSign")
@Expose
private String applicantSign;
@SerializedName("BoxNoPhoto")
@Expose
private Object boxNoPhoto;
@SerializedName("CardNoPhoto")
@Expose
private Object cardNoPhoto;
@SerializedName("Nationality")
@Expose
private Object nationality;
@SerializedName("UserId")
@Expose
private String userId;
@SerializedName("CreatedDate")
@Expose
private String createdDate;
@SerializedName("Occupation")
@Expose
private String occupation;
@SerializedName("Identification")
@Expose
private String identification;
@SerializedName("IsPassport")
@Expose
private Object isPassport;
@SerializedName("SubscriptionType")
@Expose
private String subscriptionType;
@SerializedName("ClearTV")
@Expose
private Object clearTV;
@SerializedName("CableInternet")
@Expose
private Object cableInternet;
@SerializedName("FTTH")
@Expose
private Object fTTH;
@SerializedName("UserIdNumber")
@Expose
private Object userIdNumber;
@SerializedName("FingerPrint")
@Expose
private String fingerPrint;
@SerializedName("Status")
@Expose
private Object status;

public String getOdataMetadata() {
return odataMetadata;
}

public void setOdataMetadata(String odataMetadata) {
this.odataMetadata = odataMetadata;
}

public Integer getApplicantId() {
return applicantId;
}

public void setApplicantId(Integer applicantId) {
this.applicantId = applicantId;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getApplicantName() {
return applicantName;
}

public void setApplicantName(String applicantName) {
this.applicantName = applicantName;
}

public Integer getGender() {
return gender;
}

public void setGender(Integer gender) {
this.gender = gender;
}

public String getApplicantPhoto() {
return applicantPhoto;
}

public void setApplicantPhoto(String applicantPhoto) {
this.applicantPhoto = applicantPhoto;
}

public Object getPHouseNo() {
return pHouseNo;
}

public void setPHouseNo(Object pHouseNo) {
this.pHouseNo = pHouseNo;
}

public Object getPWardNo() {
return pWardNo;
}

public void setPWardNo(Object pWardNo) {
this.pWardNo = pWardNo;
}

public String getPStreetName() {
return pStreetName;
}

public void setPStreetName(String pStreetName) {
this.pStreetName = pStreetName;
}

public String getPVdcMunicipality() {
return pVdcMunicipality;
}

public void setPVdcMunicipality(String pVdcMunicipality) {
this.pVdcMunicipality = pVdcMunicipality;
}

public String getPDistrict() {
return pDistrict;
}

public void setPDistrict(String pDistrict) {
this.pDistrict = pDistrict;
}

public Object getPZone() {
return pZone;
}

public void setPZone(Object pZone) {
this.pZone = pZone;
}

public Object getPCountry() {
return pCountry;
}

public void setPCountry(Object pCountry) {
this.pCountry = pCountry;
}

public String getContactNo() {
return contactNo;
}

public void setContactNo(String contactNo) {
this.contactNo = contactNo;
}

public Object getEmail() {
return email;
}

public void setEmail(Object email) {
this.email = email;
}

public String getApplicantSign() {
return applicantSign;
}

public void setApplicantSign(String applicantSign) {
this.applicantSign = applicantSign;
}

public Object getBoxNoPhoto() {
return boxNoPhoto;
}

public void setBoxNoPhoto(Object boxNoPhoto) {
this.boxNoPhoto = boxNoPhoto;
}

public Object getCardNoPhoto() {
return cardNoPhoto;
}

public void setCardNoPhoto(Object cardNoPhoto) {
this.cardNoPhoto = cardNoPhoto;
}

public Object getNationality() {
return nationality;
}

public void setNationality(Object nationality) {
this.nationality = nationality;
}

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getCreatedDate() {
return createdDate;
}

public void setCreatedDate(String createdDate) {
this.createdDate = createdDate;
}

public String getOccupation() {
return occupation;
}

public void setOccupation(String occupation) {
this.occupation = occupation;
}

public String getIdentification() {
return identification;
}

public void setIdentification(String identification) {
this.identification = identification;
}

public Object getIsPassport() {
return isPassport;
}

public void setIsPassport(Object isPassport) {
this.isPassport = isPassport;
}

public String getSubscriptionType() {
return subscriptionType;
}

public void setSubscriptionType(String subscriptionType) {
this.subscriptionType = subscriptionType;
}

public Object getClearTV() {
return clearTV;
}

public void setClearTV(Object clearTV) {
this.clearTV = clearTV;
}

public Object getCableInternet() {
return cableInternet;
}

public void setCableInternet(Object cableInternet) {
this.cableInternet = cableInternet;
}

public Object getFTTH() {
return fTTH;
}

public void setFTTH(Object fTTH) {
this.fTTH = fTTH;
}

public Object getUserIdNumber() {
return userIdNumber;
}

public void setUserIdNumber(Object userIdNumber) {
this.userIdNumber = userIdNumber;
}

public String getFingerPrint() {
return fingerPrint;
}

public void setFingerPrint(String fingerPrint) {
this.fingerPrint = fingerPrint;
}

public Object getStatus() {
return status;
}

public void setStatus(Object status) {
this.status = status;
}

}