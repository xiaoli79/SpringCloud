package common.xiaoli.common.utils;

import com.alibaba.fastjson2.JSON;
import org.springframework.util.StringUtils;

public class JsonUtil<T> {


    /**
     * 字符串转对象！！
     * @param json 代表要解析字符串
     * @param clazz 代表要转换的目标对象~~
     * @return
     * @param <T>
     */

    //第一个<T>表示是一个泛型方法
    public static <T> T parseJson(String json,Class<T> clazz){
        if(!StringUtils.hasLength(json) || clazz == null){
            return null;
        }
        try{
            return JSON.parseObject(json,clazz);
        }catch (Exception e){
            //TODO
            //异常处理逻辑，看需求决定是否抛出异常~~
            return null;
        }
    }



    /**
     * 对象转字符串
     * @param o
     * @return
     */
    public static String toJson(Object o){
        try{
            return o==null ? null : JSON.toJSONString(o);
        }catch (Exception e){
            //TODO
            //异常处理逻辑，看需求决定是否抛出异常
            return null;
        }
    }


}
