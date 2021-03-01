package com.cairin.cash.entity;

import com.google.gson.Gson;

import java.util.List;

public class ContactsInfo {

    private List<Contacts> contacts;

    public static ContactsInfo objectFromData(String str) {

        return new Gson().fromJson(str, ContactsInfo.class);
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public static class Contacts {
        /**
         * contactId : 86
         * displayName : Test
         * lastName : Test
         * lastUpdatedTimestamp : 1591681541643
         * mobile : 0167 7649 31
         * prefix :
         * firstName :
         * middleName :
         * suffix :
         * phoneticFirstName :
         * phoneticMiddleName :
         * phoneticLastName :
         * org :
         * title :
         * department :
         * homeNum :
         * jobNum :
         * workFax :
         * homeFax :
         * pager :
         * quickNum :
         * jobTel :
         * carNum :
         * isdn :
         * tel :
         * wirelessDev :
         * telegram :
         * tty_tdd :
         * jobMobile :
         * jobPager :
         * assistantNum :
         * mms :
         * otherPhone :
         * otherFax :
         * workEmail :
         * customEmail :
         * mobileEmail :
         * homeEmail :
         * otherEmail :
         * note :
         * birthday :
         * anniversary :
         * other :
         * aim :
         * msn :
         * yahoo :
         * skype :
         * qq :
         * googleTalk :
         * icq :
         * jabber :
         * netmeeting :
         * otherIm :
         * nickName :
         * customPage :
         * home :
         * homePage :
         * workPage :
         * ftpPage :
         * otherPage :
         * workStreet :
         * workCity :
         * workPobox :
         * workNeighborhood :
         * workRegion :
         * workPostcode :
         * workCountry :
         * workFormattedAddress :
         * homeStreet :
         * homeCity :
         * homePobox :
         * homeNeighborhood :
         * homeRegion :
         * homePostcode :
         * homeCountry :
         * homeFormattedAddress :
         * otherStreet :
         * otherCity :
         * otherPobox :
         * otherNeighborhood :
         * otherRegion :
         * otherPostcode :
         * otherCountry :
         * otherFormattedAddress :
         */

        private String contactId;
        private String displayName;
        private String lastName;
        private String lastUpdatedTimestamp;
        private String mobile;
        private String prefix;
        private String firstName;
        private String middleName;
        private String suffix;
        private String phoneticFirstName;
        private String phoneticMiddleName;
        private String phoneticLastName;
        private String org;
        private String title;
        private String department;
        private String homeNum;
        private String jobNum;
        private String workFax;
        private String homeFax;
        private String pager;
        private String quickNum;
        private String jobTel;
        private String carNum;
        private String isdn;
        private String tel;
        private String wirelessDev;
        private String telegram;
        private String tty_tdd;
        private String jobMobile;
        private String jobPager;
        private String assistantNum;
        private String mms;
        private String otherPhone;
        private String otherFax;
        private String workEmail;
        private String customEmail;
        private String mobileEmail;
        private String homeEmail;
        private String otherEmail;
        private String note;
        private String birthday;
        private String anniversary;
        private String other;
        private String aim;
        private String msn;
        private String yahoo;
        private String skype;
        private String qq;
        private String googleTalk;
        private String icq;
        private String jabber;
        private String netmeeting;
        private String otherIm;
        private String nickName;
        private String customPage;
        private String home;
        private String homePage;
        private String workPage;
        private String ftpPage;
        private String otherPage;
        private String workStreet;
        private String workCity;
        private String workPobox;
        private String workNeighborhood;
        private String workRegion;
        private String workPostcode;
        private String workCountry;
        private String workFormattedAddress;
        private String homeStreet;
        private String homeCity;
        private String homePobox;
        private String homeNeighborhood;
        private String homeRegion;
        private String homePostcode;
        private String homeCountry;
        private String homeFormattedAddress;
        private String otherStreet;
        private String otherCity;
        private String otherPobox;
        private String otherNeighborhood;
        private String otherRegion;
        private String otherPostcode;
        private String otherCountry;
        private String otherFormattedAddress;

        public static Contacts objectFromData(String str) {

            return new Gson().fromJson(str, Contacts.class);
        }

        public String getContactId() {
            return contactId;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getLastUpdatedTimestamp() {
            return lastUpdatedTimestamp;
        }

        public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
            this.lastUpdatedTimestamp = lastUpdatedTimestamp;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public String getPhoneticFirstName() {
            return phoneticFirstName;
        }

        public void setPhoneticFirstName(String phoneticFirstName) {
            this.phoneticFirstName = phoneticFirstName;
        }

        public String getPhoneticMiddleName() {
            return phoneticMiddleName;
        }

        public void setPhoneticMiddleName(String phoneticMiddleName) {
            this.phoneticMiddleName = phoneticMiddleName;
        }

        public String getPhoneticLastName() {
            return phoneticLastName;
        }

        public void setPhoneticLastName(String phoneticLastName) {
            this.phoneticLastName = phoneticLastName;
        }

        public String getOrg() {
            return org;
        }

        public void setOrg(String org) {
            this.org = org;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getHomeNum() {
            return homeNum;
        }

        public void setHomeNum(String homeNum) {
            this.homeNum = homeNum;
        }

        public String getJobNum() {
            return jobNum;
        }

        public void setJobNum(String jobNum) {
            this.jobNum = jobNum;
        }

        public String getWorkFax() {
            return workFax;
        }

        public void setWorkFax(String workFax) {
            this.workFax = workFax;
        }

        public String getHomeFax() {
            return homeFax;
        }

        public void setHomeFax(String homeFax) {
            this.homeFax = homeFax;
        }

        public String getPager() {
            return pager;
        }

        public void setPager(String pager) {
            this.pager = pager;
        }

        public String getQuickNum() {
            return quickNum;
        }

        public void setQuickNum(String quickNum) {
            this.quickNum = quickNum;
        }

        public String getJobTel() {
            return jobTel;
        }

        public void setJobTel(String jobTel) {
            this.jobTel = jobTel;
        }

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getIsdn() {
            return isdn;
        }

        public void setIsdn(String isdn) {
            this.isdn = isdn;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getWirelessDev() {
            return wirelessDev;
        }

        public void setWirelessDev(String wirelessDev) {
            this.wirelessDev = wirelessDev;
        }

        public String getTelegram() {
            return telegram;
        }

        public void setTelegram(String telegram) {
            this.telegram = telegram;
        }

        public String getTty_tdd() {
            return tty_tdd;
        }

        public void setTty_tdd(String tty_tdd) {
            this.tty_tdd = tty_tdd;
        }

        public String getJobMobile() {
            return jobMobile;
        }

        public void setJobMobile(String jobMobile) {
            this.jobMobile = jobMobile;
        }

        public String getJobPager() {
            return jobPager;
        }

        public void setJobPager(String jobPager) {
            this.jobPager = jobPager;
        }

        public String getAssistantNum() {
            return assistantNum;
        }

        public void setAssistantNum(String assistantNum) {
            this.assistantNum = assistantNum;
        }

        public String getMms() {
            return mms;
        }

        public void setMms(String mms) {
            this.mms = mms;
        }

        public String getOtherPhone() {
            return otherPhone;
        }

        public void setOtherPhone(String otherPhone) {
            this.otherPhone = otherPhone;
        }

        public String getOtherFax() {
            return otherFax;
        }

        public void setOtherFax(String otherFax) {
            this.otherFax = otherFax;
        }

        public String getWorkEmail() {
            return workEmail;
        }

        public void setWorkEmail(String workEmail) {
            this.workEmail = workEmail;
        }

        public String getCustomEmail() {
            return customEmail;
        }

        public void setCustomEmail(String customEmail) {
            this.customEmail = customEmail;
        }

        public String getMobileEmail() {
            return mobileEmail;
        }

        public void setMobileEmail(String mobileEmail) {
            this.mobileEmail = mobileEmail;
        }

        public String getHomeEmail() {
            return homeEmail;
        }

        public void setHomeEmail(String homeEmail) {
            this.homeEmail = homeEmail;
        }

        public String getOtherEmail() {
            return otherEmail;
        }

        public void setOtherEmail(String otherEmail) {
            this.otherEmail = otherEmail;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAnniversary() {
            return anniversary;
        }

        public void setAnniversary(String anniversary) {
            this.anniversary = anniversary;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getAim() {
            return aim;
        }

        public void setAim(String aim) {
            this.aim = aim;
        }

        public String getMsn() {
            return msn;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public String getYahoo() {
            return yahoo;
        }

        public void setYahoo(String yahoo) {
            this.yahoo = yahoo;
        }

        public String getSkype() {
            return skype;
        }

        public void setSkype(String skype) {
            this.skype = skype;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getGoogleTalk() {
            return googleTalk;
        }

        public void setGoogleTalk(String googleTalk) {
            this.googleTalk = googleTalk;
        }

        public String getIcq() {
            return icq;
        }

        public void setIcq(String icq) {
            this.icq = icq;
        }

        public String getJabber() {
            return jabber;
        }

        public void setJabber(String jabber) {
            this.jabber = jabber;
        }

        public String getNetmeeting() {
            return netmeeting;
        }

        public void setNetmeeting(String netmeeting) {
            this.netmeeting = netmeeting;
        }

        public String getOtherIm() {
            return otherIm;
        }

        public void setOtherIm(String otherIm) {
            this.otherIm = otherIm;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getCustomPage() {
            return customPage;
        }

        public void setCustomPage(String customPage) {
            this.customPage = customPage;
        }

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getHomePage() {
            return homePage;
        }

        public void setHomePage(String homePage) {
            this.homePage = homePage;
        }

        public String getWorkPage() {
            return workPage;
        }

        public void setWorkPage(String workPage) {
            this.workPage = workPage;
        }

        public String getFtpPage() {
            return ftpPage;
        }

        public void setFtpPage(String ftpPage) {
            this.ftpPage = ftpPage;
        }

        public String getOtherPage() {
            return otherPage;
        }

        public void setOtherPage(String otherPage) {
            this.otherPage = otherPage;
        }

        public String getWorkStreet() {
            return workStreet;
        }

        public void setWorkStreet(String workStreet) {
            this.workStreet = workStreet;
        }

        public String getWorkCity() {
            return workCity;
        }

        public void setWorkCity(String workCity) {
            this.workCity = workCity;
        }

        public String getWorkPobox() {
            return workPobox;
        }

        public void setWorkPobox(String workPobox) {
            this.workPobox = workPobox;
        }

        public String getWorkNeighborhood() {
            return workNeighborhood;
        }

        public void setWorkNeighborhood(String workNeighborhood) {
            this.workNeighborhood = workNeighborhood;
        }

        public String getWorkRegion() {
            return workRegion;
        }

        public void setWorkRegion(String workRegion) {
            this.workRegion = workRegion;
        }

        public String getWorkPostcode() {
            return workPostcode;
        }

        public void setWorkPostcode(String workPostcode) {
            this.workPostcode = workPostcode;
        }

        public String getWorkCountry() {
            return workCountry;
        }

        public void setWorkCountry(String workCountry) {
            this.workCountry = workCountry;
        }

        public String getWorkFormattedAddress() {
            return workFormattedAddress;
        }

        public void setWorkFormattedAddress(String workFormattedAddress) {
            this.workFormattedAddress = workFormattedAddress;
        }

        public String getHomeStreet() {
            return homeStreet;
        }

        public void setHomeStreet(String homeStreet) {
            this.homeStreet = homeStreet;
        }

        public String getHomeCity() {
            return homeCity;
        }

        public void setHomeCity(String homeCity) {
            this.homeCity = homeCity;
        }

        public String getHomePobox() {
            return homePobox;
        }

        public void setHomePobox(String homePobox) {
            this.homePobox = homePobox;
        }

        public String getHomeNeighborhood() {
            return homeNeighborhood;
        }

        public void setHomeNeighborhood(String homeNeighborhood) {
            this.homeNeighborhood = homeNeighborhood;
        }

        public String getHomeRegion() {
            return homeRegion;
        }

        public void setHomeRegion(String homeRegion) {
            this.homeRegion = homeRegion;
        }

        public String getHomePostcode() {
            return homePostcode;
        }

        public void setHomePostcode(String homePostcode) {
            this.homePostcode = homePostcode;
        }

        public String getHomeCountry() {
            return homeCountry;
        }

        public void setHomeCountry(String homeCountry) {
            this.homeCountry = homeCountry;
        }

        public String getHomeFormattedAddress() {
            return homeFormattedAddress;
        }

        public void setHomeFormattedAddress(String homeFormattedAddress) {
            this.homeFormattedAddress = homeFormattedAddress;
        }

        public String getOtherStreet() {
            return otherStreet;
        }

        public void setOtherStreet(String otherStreet) {
            this.otherStreet = otherStreet;
        }

        public String getOtherCity() {
            return otherCity;
        }

        public void setOtherCity(String otherCity) {
            this.otherCity = otherCity;
        }

        public String getOtherPobox() {
            return otherPobox;
        }

        public void setOtherPobox(String otherPobox) {
            this.otherPobox = otherPobox;
        }

        public String getOtherNeighborhood() {
            return otherNeighborhood;
        }

        public void setOtherNeighborhood(String otherNeighborhood) {
            this.otherNeighborhood = otherNeighborhood;
        }

        public String getOtherRegion() {
            return otherRegion;
        }

        public void setOtherRegion(String otherRegion) {
            this.otherRegion = otherRegion;
        }

        public String getOtherPostcode() {
            return otherPostcode;
        }

        public void setOtherPostcode(String otherPostcode) {
            this.otherPostcode = otherPostcode;
        }

        public String getOtherCountry() {
            return otherCountry;
        }

        public void setOtherCountry(String otherCountry) {
            this.otherCountry = otherCountry;
        }

        public String getOtherFormattedAddress() {
            return otherFormattedAddress;
        }

        public void setOtherFormattedAddress(String otherFormattedAddress) {
            this.otherFormattedAddress = otherFormattedAddress;
        }
    }
}
