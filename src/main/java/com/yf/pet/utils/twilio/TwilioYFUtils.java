/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.utils.twilio;

import com.twilio.sdk.LookupsClient;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.lookups.PhoneNumber;
import com.yf.pet.utils.YFResourceUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * twilio 短信发送工具类
 *
 * @author Infi
 */
public class TwilioYFUtils {
    private static final String ACCOUNT_SID = YFResourceUtil.getValueByKey("app-coros.properties", "twilio.account.sid");

    private static final String AUTH_TOKEN = YFResourceUtil.getValueByKey("app-coros.properties", "twilio.auth.token");

    private static TwilioRestClient client;

    /**
     * 初始化twilio连接
     *
     * @return TwilioRestClient
     */
    private static TwilioRestClient geTwilioRestClient() {
        if (client == null) {
            client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        }
        return client;
    }

    /**
     * twilio 发送短信
     *
     * @param toNumber   收件人电话号码
     * @param fromNumber twilio 账户电话号码
     * @param body       短信内容
     * @throws TwilioRestException 排除twilio短信发送异常
     */
    public static void sendSMS(String toNumber, String fromNumber, String body) throws TwilioRestException {
        TwilioRestClient twilioRestClient = geTwilioRestClient();
        final Account mainAccount = twilioRestClient.getAccount();

        // 发短信
        final SmsFactory messageFactory = mainAccount.getSmsFactory();
        final List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
        messageParams.add(new BasicNameValuePair("To", toNumber)); // Replace with a valid phone number
        messageParams.add(new BasicNameValuePair("From", fromNumber)); // Replace with a valid phone number in your account
        messageParams.add(new BasicNameValuePair("Body", body));
        messageFactory.create(messageParams);

        // System.out.println(JSON.toJSON(sms));
        // 返回状态：
        // Status The status of this SMS message. Either queued, sending, sent,failed, or received.

        // 另外一种接受返回结果的方法
        // final MessageFactory messageFactory = mainAccount.getMessageFactory();
        // final List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
        // messageParams.add(new BasicNameValuePair("To", toNumber)); // Replace with a valid phone number
        // messageParams.add(new BasicNameValuePair("From", fromNumber)); // Replace with a valid phone number in your account
        // messageParams.add(new BasicNameValuePair("Body", body));
        // Message message = messageFactory.create(messageParams);
        // System.out.println(JSON.toJSONString(message));
    }

    /**
     * lookups检查电话号码的正确性
     *
     * @param number 电话号码
     * @return 电话号码的信息
     */
    public static PhoneNumber lookupsCheckPhoneNumber(String number) {
        LookupsClient lookupsClient = new LookupsClient(ACCOUNT_SID, AUTH_TOKEN);
        try {
            PhoneNumber phoneNumber = lookupsClient.getPhoneNumber(number, true);
            phoneNumber.getType();
            return phoneNumber;
        } catch (Exception e) {
            return null;
        }
    }
}
