package com.jt.rms.common.util.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Bing.Wu on 2017-01-12.
 */
public abstract class RequestUtils {

    public static String getRealIP(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Clint-IP");
        }
        if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        if (ip!=null&&ip.contains(",")) {
            String[] split = ip.split(",");
            for (int i = 0; i < split.length; i++) {
                ip = split[i];
                if (!ip.equalsIgnoreCase("unknown")) {
                    return ip;
                }
            }
        }
        //获取本地IP可有可无
        if(ip.equals("127.0.0.1")||ip.endsWith("0:0:0:0:0:0:1")){
            InetAddress inet = null;
            try{
                inet = InetAddress.getLocalHost();
            }catch(UnknownHostException e){
                e.printStackTrace();
            }
            ip= inet.getHostAddress();
        }
        return ip;
    }

    public static boolean isAjax(HttpServletRequest request){
        String requestWith = request.getHeader("x-requested-with");
        if(null != requestWith && "XMLHttpRequest".equals(requestWith)) {
            return true;
        }
        String requestType = request.getHeader("X-Request-Type");
        String userAgent = request.getHeader("User-Agent");
        boolean isAjax = false;
        if ("ajax".equals(requestType) || "PCAssistant".equals(userAgent)) {
            isAjax = true;
        }
        return isAjax;
    }
}
