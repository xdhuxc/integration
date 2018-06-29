package com.xdhuxc.marathon;

import mesosphere.marathon.client.Marathon;
import mesosphere.marathon.client.MarathonClient;

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

        String marathonUrl = "";
        String marathonUsername = "";
        String marathonPassword = "";
        String marathonToken = "";




        return MarathonClient.getInstance(marathonUrl);
    }

    /**
     *
     *
     */
    public void getAllApps() {



    }

}
