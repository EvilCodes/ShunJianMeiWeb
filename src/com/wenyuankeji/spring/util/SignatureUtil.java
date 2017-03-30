package com.wenyuankeji.spring.util;

import com.oruit.weixin.bean.SharingSign;
import com.oruit.weixin.paymch.bean.MchPayNotify;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import weixin.popular.util.MapUtil;

public class SignatureUtil {

    /**
     * Hex散列
     *
     * @param map
     * @param paternerKey
     * @return
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 生成 随机 字符串
     *
     * @param map
     * @param paternerKey
     * @return
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成 时间戳 字符串
     *
     * @param map
     * @param paternerKey
     * @return
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    /**
     * 生成 分享签名
     *
     * @param map
     * @param paternerKey
     * @return
     */
    public static SharingSign sign(String appId, String jsapi_ticket, String url) {
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        SharingSign ss = new SharingSign();
        ss.setAppId(appId);
        ss.setNonceStr(nonce_str);
        ss.setSignature(signature);
        ss.setTimestamp(timestamp);
        return ss;
    }
    
    /**
     * 生成 flowSingn 流量包 加密认证
     *
     * @param map
     * @return
     */
    public static String generateFlowSign(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            if (flag) {
                builder.append(",").append(value);
            } else {
                builder.append(value);
                flag = true;
            }
        }
        return DigestUtils.md5Hex(builder.toString()).toUpperCase();
    }

    /**
     * 生成事件消息接收签名
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String generateEventMessageSignature(String token, String timestamp, String nonce) {
        String[] array = new String[]{token, timestamp, nonce};
        Arrays.sort(array);
        String s = StringUtils.arrayToDelimitedString(array, "");
        return DigestUtils.shaHex(s);
    }

    /**
     * 生成 package 字符串
     *
     * @param map
     * @param paternerKey
     * @return
     */
    public static String generatePackage(Map<String, String> map, String paternerKey) {
        String sign = generateSign(map, paternerKey);
        Map<String, String> tmap = MapUtil.order(map);
        String s2 = MapUtil.mapJoin(tmap, false, true);
        return s2 + "&sign=" + sign;
    }

    /**
     * 生成sign MD5 加密 toUpperCase
     *
     * @param map
     * @param paternerKey
     * @return
     */
    public static String generateSign(Map<String, String> map, String paternerKey) {
        Map<String, String> tmap = MapUtil.order(map);
        if (tmap.containsKey("sign")) {
            tmap.remove("sign");
        }
        String str = MapUtil.mapJoin(tmap, false, false);
        return DigestUtils.md5Hex(str + "&key=" + paternerKey).toUpperCase();
    }

    /**
     * 生成 paySign
     *
     * @param map
     * @param paySignKey
     * @return
     */
    public static String generatePaySign(Map<String, String> map, String paySignKey) {
        if (paySignKey != null) {
            map.put("appkey", paySignKey);
        }
        Map<String, String> tmap = MapUtil.order(map);
        String str = MapUtil.mapJoin(tmap, true, false);
        return DigestUtils.shaHex(str);
    }

    /**
     * 验证 mch pay notify sign 签名
     *
     * @param mchPayNotify
     * @param key mch key
     * @return
     */
    public static boolean validateAppSignature(MchPayNotify mchPayNotify, String key) {
        Map<String, String> map = MapUtil.objectToMap(mchPayNotify);
        return mchPayNotify.getSign().equals(generateSign(map, key));
    }
}
