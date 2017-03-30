/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wenyuankeji.spring.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SmsHelper {

    private final static String userId = "shunjm";
    private final static String account = "shunjm";
    private final static String password = "shunjm123456";
    private final static String admin1Mobile = "15810715503";
    private final static String admin2Mobile = "15101175503";

    public final static String SMS_TEMPLATE = "【顺间】%s（顺间手机动态验证码，请完成验证），如非本人操作，请忽略本短信。";

    // 用户预约成功之后，通知美发师的短信模板
    public final static String SMS_ORDER_SUC_TEMPLATE = 
    		"【顺间】尊敬的美发师您好，有用户预约您的服务，时间是：%s，请准时到达并完成服务。用户联系方式：%s。谢谢。";
    
 // 用户预约成功之后，通知管理员的短信模板
    public final static String SMS_ORDER_SUC_ADMIN_TEMPLATE = 
    		"【顺间】提醒管理员：有用户于%s预约美发师:%s，用户联系方式：%s，美发师联系方式：%s。";
    
    // add by jiazhoahui 
    // 得到美发师预约成功的内容
    public static String getOrderSucMsg(String stime, String cusMobile)
    {
    	String content = "";
        content = String.format(SMS_ORDER_SUC_TEMPLATE, stime, cusMobile);
        return content;
    }
    
    // 预约成功的时候，得到管理员发送的短信内容
    public static String getOrderSucAdminMsg(String stime, String nickname, String cusMobile, 
    		String masterMobile)
    {
    	String content = "";
        content = String.format(SMS_ORDER_SUC_ADMIN_TEMPLATE, stime, nickname, cusMobile, masterMobile);
        return content;
    }
    
    // 发送内容content到mobile
    public static void sendNormalMessage(String mobile, String content)
    {
    	sendMessage(userId, account, password, mobile, content);
    }
    
    // 发送短信给管理员
    public static void sendMessageToAdmin(String content)
    {
    	sendMessage(userId, account, password, admin1Mobile, content);
    	sendMessage(userId, account, password, admin2Mobile, content);
    }
    
    public static void sendMessage(String mobile, String vCode) {
        String content = "";
        content = String.format(SMS_TEMPLATE, vCode);
        sendMessage(userId, account, password, mobile, content);
    }

    // 发送短信
    private static StringBuffer sendMessage(String userid, String account,
            String password, String mobile, String content) {
        StringBuffer buffer = new StringBuffer();
        try {
            // 设置发送内容的编码方式
            String send_content = URLEncoder.encode(
                    content.replaceAll("<br/>", " "), "UTF-8");// 发送内容

            URL url = new URL("http://dx.ipyy.net/sms.aspx?action=send&userid="
                    + userid + "&account=" + account + "&password=" + password
                    + "&mobile=" + mobile + "&content=" + send_content);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),
                    "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        readStringXml(buffer.toString());
        return buffer;
    }

    private static void readStringXml(String xml) {
        Document doc = null;

        try {
            //将字符转化为XML
            doc = DocumentHelper.parseText(xml);
            //获取根节点
            Element rootElt = doc.getRootElement();

            //拿到根节点的名称
            //获取根节点下的子节点的值
            String returnstatus = rootElt.elementText("returnstatus").trim();
            String message = rootElt.elementText("message").trim();
            String payinfo = rootElt.elementText("taskID").trim();
            String overage = rootElt.elementText("remainpoint").trim();
            String sendTotal = rootElt.elementText("successCounts").trim();

//            log.info("返回状态为：" + returnstatus);
//            log.info("返回信息提示：" + message);
//            log.info("返回支付方式：" + payinfo);
//            log.info("返回剩余短信条数：" + overage);
//            log.info("返回总条数：" + sendTotal);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        SmsHelper.sendMessage("15829274489", "0217");
    }

}
