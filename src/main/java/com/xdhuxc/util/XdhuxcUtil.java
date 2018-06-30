package com.xdhuxc.util;

import com.xdhuxc.marathon.MarathonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 其他工具类
 * @author xdhuxc
 * @date 2018-06-30
 **/

public class XdhuxcUtil {

    /**
     * 优先从环境变量中取值，然后从 resource/application.properties 配置文件中取值。
     * @param paramName 待取值的变量
     * @return null，如果环境变量和配置文件中都没有该参数的值。
     */
    public static String getParamFromEnvFirstly(String paramName) {
        Properties props = new Properties();
        // 使用 ClassLoader 加载 properties 配置文件生成对应的输入流
        InputStream is = XdhuxcUtil.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            // 使用 properties 对象加载输入流
            props.load(is);
            // 从环境变量中获取 paramName 的值
            String paramValueInEnv = System.getenv(paramName);
            // 从配置文件中读取 paramName 的值
            String paramValueInProps = props.getProperty(paramName);
            // 优先使用从环境变量中读取的值
            if (StringUtil.isNotBlank(paramValueInEnv)) {
                return paramValueInEnv;
            } else if (StringUtil.isNotBlank(paramValueInProps)) {
                return paramValueInProps;
            } else {
                return null;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}
