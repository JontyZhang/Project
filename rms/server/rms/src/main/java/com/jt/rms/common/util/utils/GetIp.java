package com.jt.rms.common.util.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIp {
    /*
     * 获取客户端访问的真实IP
     * @author: yeliu
     * */
    public static String getRealIP(HttpServletRequest request) {
        String ip = request.getHeader("X_Real_IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X_Forwarded_For");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Clint-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.contains(",")) {
            String[] split = ip.split(",");

            for(int i = 0; i < split.length; ++i) {
                ip = split[i];
                if (!ip.equalsIgnoreCase("unknown")) {
                    return ip;
                }
            }
        }

        if (ip.equals("127.0.0.1") || ip.endsWith("0:0:0:0:0:0:1")) {
            InetAddress inet = null;

            try {
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException var4) {
                var4.printStackTrace();
            }

            ip = inet.getHostAddress();
        }

        return ip;
    }

}
