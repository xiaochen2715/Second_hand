package model;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/17 14:49
 * @description 描述：
 */
public class User {
    private int uid;
    private String uemail;
    private String upwd;
    private String uname;
    private String usex;
    private String uclass;
    private String ucontentway;
    private String udesc;

    public int getUid() {
        return uid;
    }

    public void setUid(int id) {
        this.uid = id;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUname() {
        return uname;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUclass() {
        return uclass;
    }

    public void setUclass(String uclass) {
        this.uclass = uclass;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUcontentway() {
        return ucontentway;
    }

    public void setUcontentway(String ucontentway) {
        this.ucontentway = ucontentway;
    }

    public String getUdesc() {
        return udesc;
    }

    public void setUdesc(String udesc) {
        this.udesc = udesc;
    }
}
