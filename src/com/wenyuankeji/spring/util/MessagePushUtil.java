/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wenyuankeji.spring.util;

import java.util.List;

import com.wenyuankeji.spring.util.BaseException;

import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.*;

/**
 *
 * @author Liuk
 */
public class MessagePushUtil {

    // demo App defined in resources/jpush-api.conf 
    // 用户推送
    private static final String appKey = "c6dbb3629ed1bf9ed8ad1f39";
    private static final String masterSecret = "a2f54dacb6391052761e9eca";
    
    // 美发师推送
    private static final String h_appKey = "5405406c50dd4141a209884b";
    private static final String h_masterSecret = "2ecaab23680128ddb6af0120";
    
    public static String TITLE = "顺间";

//    public static void main(String[] args) {
////        testSendPushWithCustomConfig();
//        List<String> userList = new ArrayList<>();
//        userList.add("171");
//        String jsontring = "\"Id\": \"1\",\n"
//                + "\"Title\": \"收徒奖励\",\n"
//                + "\"Content\": \"您的徒弟\"xxx\"完成了《新手任务》，为我贡献2000金币\",\n"
//                + "\"CreateTime\": \"2015/8/23 11:08:35\",\n"
//                + "            \"Photo\": \"http://ykssdservice.ruitei.com/Resource/Photo/raw_1433213237.jpeg\",\n"
//                + "\"From\": \"1\",\n"
//                + "\"IsRead\": \"1\"";
//        SendPush(userList, JSON.toJSONString(jsontring), "消息");
//    }

    /**
     * 推送消息
     *
     * @param userList
     * @param jsonString
     * @param Content
     * @param userType 1:推送目标用户   2：推送目标美发师
     */
    public static void SendPush(List<String> userList, Map<String,Object> jsonString, String Content,int userType ) {
        JPushClient jpushClient ;
        if(userType == 1)
        {
        	TITLE = "顺间";
        	jpushClient= new JPushClient(masterSecret, appKey, 3);
        }
        else
        {
        	TITLE = "顺间美发师端";
        	jpushClient= new JPushClient(h_masterSecret, h_appKey, 3);
        }
        PushPayload payload = PushObject_android_and_ios(userList, jsonString, Content);
        try {
            PushResult result = jpushClient.sendPush(payload);
        } 
        catch (APIConnectionException e) {
        	String xx = e.getMessage();

        }
        catch(APIRequestException e) {
        	String xx = e.getMessage();

        }

    }

    /**
     * 消息推送
     *
     * @param userList 推个用户 id
     * @param jsonString
     * @param Content 内容
     * @return
     */
    public static PushPayload PushObject_android_and_ios(List<String> userList, Map<String,Object> jsonString, String Content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.alias(userList))
                        .build())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(Content)
                                .setBadge(1)
                                .setSound("happy.caf")
                                .addExtra("dealid",jsonString.get("dealid").toString())
                                .addExtra("type", jsonString.get("type").toString())
                                .build())
                        .build())
                .setMessage(Message.newBuilder().setTitle(TITLE)
                        .setMsgContent(Content)
                        .addExtra("dealid",jsonString.get("dealid").toString())
                        .addExtra("type", jsonString.get("type").toString())
                        .build())
                .setOptions(Options.newBuilder().setApnsProduction(true).build())
                .build();
    }

}
