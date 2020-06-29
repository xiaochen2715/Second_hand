package dao;
import	java.util.ArrayList;
import	java.sql.SQLException;
import	java.sql.ResultSet;

import model.*;

import java.sql.*;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/17 15:14
 * @description 描述：
 */
public class BaseDao {

    // 存放Connection对象的数组，数组被看成连接池
    static ArrayList<Connection> list = new ArrayList<Connection>();
    /**
     * @description 从连接池中获得连接对象
     */
    public synchronized static Connection getConnection() {
        Connection con = null;
        // 如果连接池中有连接对象
        if (list.size() > 0) {
            return list.remove(0);
        }
        // 连接池没有连接对象创建连接放到连接池中
        else {
            for (int i = 0; i < 5; i++) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                // 创建连接
                try {
                    con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/secondhand?characterEncoding=utf-8","root","123456");
                    list.add(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list.remove(0);
    }
    /**
     * @description 关闭结果集
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @description 关闭预处理
     */
    public static void close(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @description 关闭预处理
     */
    public static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @description 关闭连接
     */
    public synchronized static void close(Connection con) {
        if (con != null)
            list.add(con);
    }
    /**
     * @description 关闭所有连接有关的对象
     */
    public static void close(ResultSet rs, PreparedStatement ps, Connection con) {
        close(rs);
        close(ps);
        close(con);
    }

    /**
     * @description  数据库“添加”操作
     * @param sql--SQL语句
     * @param str--预处理SQL语句中的“？”数组
     */
    public static void toAddition(String sql,String[] str){
        Connection con = getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            if (str.length > 0 ){
                for (int i = 1; i <= str.length;i++){
                    ps.setString(i,str[i-1]);
                }
            }
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,con);
        }
    }
    /**
     * @description  数据库“删除”操作
     * @param sql--SQL语句
     * @param str--预处理SQL语句中的“？”数组
     */
    public static void toDelete(String sql,String[] str) {
        toAddition(sql, str);
    }

    /**
     * @description 删除物品ID为goodsID的物品（使用中）
     * @param goodsID   物品ID
     */
    public static void deleteGoods(int goodsID){
        Connection con = getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("delete from goods_tb where id=?");
            ps.setInt(1,goodsID);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,con);
        }
    }
    /**
     * @description 根据物品ID查询该物品的所有信息(使用中)
     * @param goods_id 物品你ID
     * @return Goods类型
     */
    public static Goods goodsDetails(int goods_id){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Goods g = null;
        try {
            ps = con.prepareStatement("select * from goods_tb where id=?");
            //goodsID商品的id值
            ps.setInt(1,goods_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                g = new Goods();
                goodsAllField(rs,g);
            }
            return g;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }

    /**
     * @description 查询物品人气值从大到小排名（使用中）
     * @return Goods集合
     */
    public static ArrayList<Goods> goodsPV(){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Goods> allpvGoods;
        try {
            ps = con.prepareStatement("select * from goods_tb order by gpv desc");
            rs = ps.executeQuery();
            allpvGoods = new ArrayList<> ();
            while (rs.next()){
                Goods g = new Goods();
                goodsAllField(rs,g);
                allpvGoods.add(g);
            }
            return allpvGoods;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }

    /**
     * @description  （使用中）
     * @param rs 结果集ResultSet
     * @param g Goods类型
     * @throws SQLException
     */
    public static void goodsAllField(ResultSet rs,Goods g) throws SQLException{
        g.setId(rs.getInt("id"));
        g.setGname(rs.getString("gname"));
        g.setGdegree(rs.getString("gdegree"));
        g.setGprice(rs.getDouble("gprice"));
        g.setGtype(rs.getString("gtype"));
        g.setGsite(rs.getString("gsite"));
        g.setGpicture(rs.getString("gpicture"));
        g.setGintro(rs.getString("gintro"));
        g.setGtime(rs.getString("gtime"));
        g.setGputaway(rs.getInt("gputaway"));
        g.setGstate(rs.getString("gstate"));
        g.setUser_uid(rs.getInt("user_uid"));
        g.setGpv(rs.getInt("gpv"));
    }
    /**
     * @description  数据库“查询全部商品”操作(使用中)
     * @return Goods集合
     */
    public static ArrayList<Goods> toFindAllGoods(){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Goods> allGoods;
        try {
            ps = con.prepareStatement("select * from goods_tb;");
            rs = ps.executeQuery();
            allGoods = new ArrayList<> ();
            while (rs.next()){
                Goods g = new Goods();
                goodsAllField(rs,g);
                allGoods.add(g);
            }
            return allGoods;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }

    /**
     * @description 查询某个表数据的总数(使用中)
     * @param tableName 数据库表名
     * @return 该表的数据总条数
     */
    public static int totalCount(String tableName) {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs = null;
        int count = 0;
        try {
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            if(tableName.equals("goods_tb")){
                rs = st.executeQuery("select * from goods_tb");
                rs.last();
                count = rs.getRow();
            }else if (tableName.equals("user_tb")){
                rs = st.executeQuery("select * from user_tb");
                rs.last();
                count = rs.getRow();
            }
            return count;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            close(rs);
            close(st);
            close(con);
        }
    }

    /**
     * @description 查询某个用户id对应的所有商品的所有信息(使用中)
     * @param uid   用户id
     * @return  Goods类型对象的集合
     */
    public static ArrayList<Goods> toFindMyGoods(int uid){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Goods> myGoods;
        try {
            ps = con.prepareStatement("select * from goods_tb where user_uid=?");
            ps.setInt(1,uid);
            rs = ps.executeQuery();
            myGoods = new ArrayList<Goods> ();
            while (rs.next()){
                Goods g = new Goods();
                goodsAllField(rs, g);
                myGoods.add(g);
            }
            return myGoods;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }

    /**
    *@description 查询所有用户的所有资料(使用中)
     */
    public static ArrayList<User> toFindAllusers() {
        ArrayList<User> allUsers ;
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("select * from user_tb;");
            rs = ps.executeQuery();
            allUsers = new ArrayList<> ();
            while (rs.next()){
                User u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUemail(rs.getString("uemail"));
                u.setUpwd(rs.getString("upwd"));
                u.setUname(rs.getString("uname"));
                u.setUsex(rs.getString("usex"));
                u.setUclass(rs.getString("uclass"));
                u.setUcontentway(rs.getString("ucontentway"));
                u.setUdesc(rs.getString("udesc"));
                allUsers.add(u);
            }
            return allUsers;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }
    /**
     * @description 查询全部公告的全部信息(使用中)
     * @return  Notice公告对象的集合
     */
    public static ArrayList<Notice> toFindAllNotice(){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Notice> allNotice = new ArrayList<> ();
        try {
            ps = con.prepareStatement("select * from notice_tb order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                Notice n = new Notice();
                n.setId(rs.getInt("id"));
                n.setNtitle(rs.getString("ntitle"));
                n.setNcontent(rs.getString("ncontent"));
                n.setNtime(rs.getString("ntime"));
                allNotice.add(n);
            }
            return allNotice;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }

    /**
     * @description 查询某公告ID的所有信息（使用中）
     * @param noticeID  公告id
     * @return  Notice对象
     */
    public static Notice noticeDetails(int noticeID){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Notice n = null;
        try {
            ps = con.prepareStatement("select * from notice_tb where id=?");
            ps.setInt(1,noticeID);
            rs = ps.executeQuery();
            while (rs.next()){
                n = new Notice();
                n.setId(rs.getInt("id"));
                n.setNtitle(rs.getString("ntitle"));
                n.setNcontent(rs.getString("ncontent"));
                n.setNtime(rs.getString("ntime"));
            }
            return n;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }
    /**
     * @description 查询全部需求的全部信息(使用中)
     * @return  Demand需求对象的集合
     */
    public static ArrayList<Demand> toFindAllDemand(){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Demand> allDemand = new ArrayList<> ();
        try {
            ps = con.prepareStatement("select * from demand_tb order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                Demand d = new Demand();
                d.setId(rs.getInt("id"));
                d.setDtitle(rs.getString("dtitle"));
                d.setDcontent(rs.getString("dcontent"));
                d.setDtime(rs.getString("dtime"));
                d.setUser_uid(rs.getInt("user_uid"));
                allDemand.add(d);
            }
            return allDemand;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }
    /**
     * @description 查询某用户id下的全部需求的全部信息(使用中)
     * @return  Demand需求对象的集合
     */
    public static ArrayList<Demand> toFindMyDemand(int user_uid){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Demand> myDemand = new ArrayList<> ();
        try {
            ps = con.prepareStatement("select * from demand_tb where user_uid=?");
            ps.setInt(1,user_uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Demand d = new Demand();
                d.setId(rs.getInt("id"));
                d.setDtitle(rs.getString("dtitle"));
                d.setDcontent(rs.getString("dcontent"));
                d.setDtime(rs.getString("dtime"));
                d.setUser_uid(rs.getInt("user_uid"));
                myDemand.add(d);
            }
            return myDemand;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }
    /**
     * @description 查询某需求ID的所有信息（使用中）
     * @param demandID  需求id
     * @return  Demand需求对象
     */
    public static Demand demandDetails(int demandID){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Demand d = null;
        try {
            ps = con.prepareStatement("select * from demand_tb where id=?");
            ps.setInt(1,demandID);
            rs = ps.executeQuery();
            while (rs.next()){
                d = new Demand();
                d.setId(rs.getInt("id"));
                d.setDtitle(rs.getString("dtitle"));
                d.setDcontent(rs.getString("dcontent"));
                d.setDtime(rs.getString("dtime"));
                d.setUser_uid(rs.getInt("user_uid"));
            }
            return d;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }

    /**
     * @description 删除某需求ID的全部信息（使用中）
     * @param demandID 需求ID
     */
    public static void deleteDemand(int demandID){
        Connection con = getConnection();
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("delete from demand_tb where id=?");
            ps.setInt(1,demandID);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,con);
        }
    }
    /**
     * @description 判断用户邮箱密码是否正确(使用中)
     */
    public static boolean userValidate(User user){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("select uemail,upwd from user_tb where uemail=? and upwd=?");
            ps.setString(1,user.getUemail());
            ps.setString(2,user.getUpwd());
            rs = ps.executeQuery();
            String s = null;
            while (rs.next()) {
                s = rs.getString("uemail");
            }
            if (s != null){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            close(rs,ps,con);
        }
    }

    /**
     * @description 判断管理员账号密码是否正确(使用中)
     */
    public static boolean admiValidate(Admi admi){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("select aid,apwd from admi_tb where aid=? and apwd=?");
            ps.setInt(1,admi.getAid());
            ps.setString(2,admi.getApwd());
            rs = ps.executeQuery();
            String s = null;
            while (rs.next()) {
                s = rs.getString("aid");
            }
            if (s != null){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            close(rs,ps,con);
        }
    }

    /**
    * @description 通过Email获取当前用户id(使用中)
     */
    public static int  currentUserID(String uemail){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("select uid from user_tb where uemail=?;");
            ps.setString(1,uemail);
            rs = ps.executeQuery();
            int uid = 0;
            while (rs.next()) {
                uid = rs.getInt("uid");
            }
            return uid;
        }catch (SQLException e) {
            e.printStackTrace();
            return - 1;
        }finally {
            close(rs,ps,con);
        }
    }
    /**
     *@description 通过id值查询某一个用户的所有个人资料(使用中)
     */
    public static User toFindUser(int uid) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            ps = con.prepareStatement("select * from user_tb where uid=?");
            ps.setInt(1,uid);
            rs = ps.executeQuery();
            while (rs.next()){
                u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUemail(rs.getString("uemail"));
                u.setUname(rs.getString("uname"));
                u.setUsex(rs.getString("usex"));
                u.setUclass(rs.getString("uclass"));
                u.setUcontentway(rs.getString("ucontentway"));
                u.setUdesc(rs.getString("udesc"));
            }
            return u;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }
    /**
    *@description 关键字搜索 和 类型搜索(使用中)
     */
    public static ArrayList<Goods> searchGoods(String fieldName,String str){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Goods> allGoods ;
        try {
            String sql= "select gpicture,gname,gprice,gdegree,id from goods_tb where 1=1 and gputaway=1";
            if (fieldName.equals("gnamechip")){
                sql = sql + " and gname like ? order by id DESC";
            }else if(fieldName.equals("gtype")){
                sql = sql + " and gtype like ? order by id DESC";
            }
            ps = con.prepareStatement(sql);
            ps.setString(1,"%"+str+"%");
            rs = ps.executeQuery();
            allGoods = new ArrayList<Goods>();
            while (rs.next()) {
                Goods g = new Goods();
                g.setGpicture(rs.getString("gpicture"));
                g.setGname(rs.getString("gname"));
                g.setGprice(rs.getDouble("gprice"));
                g.setGdegree(rs.getString("gdegree"));
                g.setId(rs.getInt("id"));
                allGoods.add(g);
            }
            return allGoods;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            close(rs,ps,con);
        }
    }
}
