package model;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/30 17:14
 * @description 描述：关注实体
 */
public class Focus {
    private int id;
    private int goods_id;
    private int user_uid;
    private String ftime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(int user_uid) {
        this.user_uid = user_uid;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }
}
