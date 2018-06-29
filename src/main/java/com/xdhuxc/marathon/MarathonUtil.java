package com.xdhuxc.marathon;

import com.xdhuxc.util.StringUtil;
import mesosphere.marathon.client.Marathon;
import mesosphere.marathon.client.MarathonClient;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Marathon 工具类
 *
 * @author xdhuxc
 * @date 2018/6/28
 **/

public class MarathonUtil {

    /**
     * 创建 marathon 客户端实例，优先使用 token
     * @return
     */
    private static Marathon createMarathonClient() {
        Properties properties = new Properties();
        // 使用 ClassLoader 加载 properties 配置文件生成对应的输入流
        InputStream is = MarathonUtil.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            // 使用 properties 对象加载输入流
            properties.load(is);
            // 获取配置值
            String marathonUrl = System.getenv("marathon_url");
            String s = properties.getProperty("marathon_url");
            String marathonUsername = properties.getProperty("marathon_username");
            String marathonPassword = properties.getProperty("marathon_password");
            String marathonToken = properties.getProperty("marathon_token");

            // 创建 Marathon 对象
            if (StringUtil.isNotBlank(marathonUrl)) {

            }


        } catch (IOException e) {
            e.printStackTrace();
        }





        return MarathonClient.getInstance(marathonUrl);
    }

    /**
     *
     *
     */
    public void getAllApps() {



    }

}
