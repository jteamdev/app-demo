package com.demo.app;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 頁面瀏覽
 * 1. 页面转换时的浏览纪录："page_view"
 * 2. 开启页面："open_page"
 * 3. 关闭页面："close_page"
 * 注册流程事件
 * 1. 点击注册："click_register"
 * 2. 点击OTP驗證："click_OTP_verify"
 * 3. 点击银行资讯："click_binding_bank"
 * 4. 点击领取freeCredit："click_free_credit"
 * 5. 点击promoCode："click_promo_code"
 * 注册流程事件 - 成功
 * 1. 成功註册："sign_up_success"
 * 2. 成功验证OTP："OTP_verification_success"
 * 3. 成功验证银行资料："bank_verification_success"
 * 登入成功："login_success"
 * 存款相关
 * 1. 点击充值按钮："click_deposit"
 * 2. 提交充值："submit_deposit"
 * 3. 充值成功："purchase"
 * 4. 首充成功："First Deposit"
 * 活动相关点击
 * 1. 点击活动："activity_click"
 * 2. 点击活动说明："activity_detail"
 * 点击进入游戏："game_click"
 * 大厅点击事件："lobby_click"
 * 搜寻事件："search"
 * 弹出错误视窗："error_event"

 */
public class AppsFlyerLibUtil {
    private static final String TAG = "AppsFlyerLibUtil";
    private static final String APPSFLYER_KEY = "bhaX8sZaAVbXc28VHtwsDf";

    /**
     * 初始化AppsFlyer
     */
    public static void init(Context context) {
        // AppsFlyer初始化
        AppsFlyerLib.getInstance().start(context, APPSFLYER_KEY, new AppsFlyerRequestListener() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "Launch sent successfully, got 200 response code from server");
            }

            @Override
            public void onError(int i, @NonNull String s) {
                Log.e(TAG, "Launch failed to be sent:\n" + "Error code: " + i + "\n" + "Error description: " + s);
            }
        });
        AppsFlyerLib.getInstance().setDebugLog(true);
    }

    /***
     * 上报AppsFlyer数据
     */
    public static void event(Activity context, String name, String data) {
        Map<String, Object> eventValue = new HashMap<String, Object>();
        eventValue.put(name, data);
        AppsFlyerLib.getInstance().logEvent(context, name, eventValue);
    }
}
