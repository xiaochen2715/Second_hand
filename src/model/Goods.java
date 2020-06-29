package model;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/17 14:37
 * @description 描述：商品实体模型
 */
public class Goods {
    private int id;
    private String gname;
    private String gdegree;
    private double gprice;
    private String gtype;
    private String gsite;
    private String gpicture;
    private String gintro;
    private String gtime;
    private int gputaway;
    private String gstate;
    private  int gpv;

    public int getGpv() {
        return gpv;
    }

    public void setGpv(int gpv) {
        this.gpv = gpv;
    }

    private int user_uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGdegree() {
        return gdegree;
    }

    public void setGdegree(String gdegree) {
        this.gdegree = gdegree;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public String getGtype() {
        return gtype;
    }

    public void setGtype(String gtype) {
        this.gtype = gtype;
    }

    public String getGsite() {
        return gsite;
    }

    public void setGsite(String gsite) {
        this.gsite = gsite;
    }

    public String getGpicture() {
        return gpicture;
    }

    public void setGpicture(String gpicture) {
        this.gpicture = gpicture;
    }

    public String getGintro() {
        return gintro;
    }

    public void setGintro(String gintro) {
        this.gintro = gintro;
    }

    public String getGtime() {
        return gtime;
    }

    public void setGtime(String gtime) {
        this.gtime = gtime;
    }

    public int getGputaway() {
        return gputaway;
    }

    public void setGputaway(int gputaway) {
        this.gputaway = gputaway;
    }

    public String getGstate() {
        return gstate;
    }

    public void setGstate(String gstate) {
        this.gstate = gstate;
    }

    public int getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(int user_id) {
        this.user_uid = user_id;
    }
}
