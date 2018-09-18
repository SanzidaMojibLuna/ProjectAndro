package com.example.user.projectandro;


public class StudentInfo {

    String fname, lname,femail,fpass,fphn,type;

    public StudentInfo(String fname, String lname, String femail, String fpass, String fphn, String type) {
        this.fname = fname;
        this.lname = lname;
        this.femail = femail;
        this.fpass = fpass;
        this.fphn = fphn;
        this.type=type;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFemail() {
        return femail;
    }

    public void setFemail(String femail) {
        this.femail = femail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFpass() {
        return fpass;

    }

    public void setFpass(String fpass) {
        this.fpass = fpass;
    }

    public String getFphn() {
        return fphn;
    }

    public void setFphn(String fphn) {
        this.fphn = fphn;
    }
}
