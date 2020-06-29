package util;
import java.text.SimpleDateFormat;
import	java.util.Date;

import javax.servlet.http.Part;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/21 10:23
 * @description 描述：
 */
public class Myutil {

    /*
     * @description 获取上传文件的名称
     */
    public static String getFileName(Part part){
        if(part == null){
            return null;
        }
        //fileName形式为：form-data;name="xxx";filename="xxx.xxx"
        String fileName = part.getHeader("content-disposition");
        //如果文件名为空（没有选择文件）
        if (fileName.lastIndexOf("=") + 2 == fileName.length()-1){
            return null;
        }
        return fileName.substring(fileName.lastIndexOf("=")+2,fileName.length()-1);
    }

    /*获取当前时间的字符串形式*/
    public static String nowTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        return format.format(date);
    }
}
