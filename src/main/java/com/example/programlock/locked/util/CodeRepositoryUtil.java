package com.example.programlock.locked.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CodeRepositoryUtil {
    private static final Logger logger = LoggerFactory.getLogger(CodeRepositoryUtil.class);

    public static String getSysConfigValue(String key) {
        Properties properties = loadProperties();//.getProperty(key);
        System.out.println("properties:==="+ JSON.toJSONString(properties));
        String value= properties.getProperty(key);
        if(!StringUtils.isEmpty(value)){
            PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${","}");
            value = helper.replacePlaceholders(value, properties);
        }
        return value;
    }

    /**
     * 获取System.properties文件属性值
     * @return System.properties文件属性值
     */

    private static Properties loadProperties() {
        /*try{
            return PropertiesLoaderUtils.loadAllProperties("system.properties");
        } catch (IOException e) {*/
        Properties prop = new Properties();
        try (InputStream resource = CodeRepositoryUtil.class.getResourceAsStream("/application.yml")){///yml文件也能读，但最好使用properties文件
            if(resource == null) {
                try(InputStream resource2 = ClassLoader.getSystemResourceAsStream("/application.yml")){
                    if(resource2 != null) {
                        prop.load(resource2);
                    }
                }
            }else {
                prop.load(resource);
            }
        } catch (IOException e2) {
            logger.error("获取系统参数出错！",e2);
        }
        return prop;
        //}
    }


    public static void main(String[] args) {
       String ss= getSysConfigValue("server.port");
        System.out.println(ss);
    }

}
