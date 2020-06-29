package model;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/28 16:28
 * @description 描述：
 */
public class Notice {
    private int id;
    private String ntitle;
    private String ncontent;
    private String ntime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNcontent() {
        return ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }

    public String getNtime() {
        return ntime;
    }

    public void setNtime(String ntime) {
        this.ntime = ntime;
    }
}
