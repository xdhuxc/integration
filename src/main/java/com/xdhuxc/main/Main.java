package com.xdhuxc.main;

import com.xdhuxc.util.AESUtils;

public class Main {

    public static void main(String[] args) {

        String mailPassword = "yonyou@1988";
        System.out.println(AESUtils.encrypt(mailPassword));

    }


}
