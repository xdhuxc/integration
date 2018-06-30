package com.xdhuxc.marathon;

import com.xdhuxc.util.StringUtil;
import com.xdhuxc.util.XdhuxcUtil;
import mesosphere.marathon.client.Marathon;
import mesosphere.marathon.client.MarathonClient;
import mesosphere.marathon.client.model.v2.GetAppsResponse;
import mesosphere.marathon.client.model.v2.VersionedApp;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
        // 获取 marathon 配置信息
        String marathonUrl = XdhuxcUtil.getParamFromEnvFirstly("marathon_url");
        String marathonUsername = XdhuxcUtil.getParamFromEnvFirstly("marathon_username");
        String marathonPassword = XdhuxcUtil.getParamFromEnvFirstly("marathon_password");
        String marathonToken = XdhuxcUtil.getParamFromEnvFirstly("marathon_token");

        // 创建 Marathon 对象，优先使用 token验证，其次使用用户名/密码验证，最后直接裸用
        if (StringUtil.isNotBlank(marathonUrl) && StringUtil.isNotBlank(marathonToken)) {
            return MarathonClient.getInstanceWithTokenAuth(marathonUrl, marathonToken);
        } else if (StringUtil.isNotBlank(marathonUrl) && StringUtil.isNotBlank(marathonUsername) && StringUtil.isNotBlank(marathonPassword)) {
            return MarathonClient.getInstanceWithBasicAuth(marathonUrl, marathonUsername, marathonPassword);
        } else if (StringUtil.isNotBlank(marathonUrl)) {
            return MarathonClient.getInstance(marathonUrl);
        } else {
            return null;
        }
    }

    /**
     *
     *
     */
    public static void getAllApps() {
        Marathon marathon = MarathonUtil.createMarathonClient();
        List<VersionedApp> listApps = marathon.getApps().getApps();
        for (VersionedApp app : listApps) {
            System.out.println(app.toString());
        }
        

    }

}
