package com.demo.app

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import org.json.JSONException
import org.json.JSONObject

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
 * 3. 充值成功："purchase" - value:double
 *    存款的事件内容有额外带上金额以及币种
      例: {"value":200,"currency":"PHP","isFirst":0}
 * 4. 首充成功："First Deposit"
 * 活动相关点击
 * 1. 点击活动："activity_click"
 * 2. 点击活动说明："activity_detail"
 * 点击进入游戏："game_click"
 * 大厅点击事件："lobby_click"
 * 搜寻事件："search"
 * 弹出错误视窗："error_event"

 */



object FirebaseLibUtil {
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    fun init(context: Context) {
        try {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /***
     * 上报Firebase数据
     */
    fun logEvent(jsonString: String? = null) {
        val jsonObject = JSONObject(jsonString)
        jsonObject?.let { params->
            val eventName = params.optString("event")
            // data型别转换
            val paramsData = BundleUtils.convertJsonToBundle(jsonObject)
            // firebase setUserProperty
            setUserProperties(jsonObject)
            // firebase send event
            mFirebaseAnalytics.logEvent(eventName, paramsData)
        }
    }

    private fun setUserProperties(jsonObject: JSONObject? = null){
        jsonObject?.getJSONObject("user_properties")?.let { json->
            try {
                val iterator = json.keys()
                while (iterator.hasNext()) {
                    val key = iterator.next() as String
                    val value = json[key]
                    mFirebaseAnalytics.setUserProperty(key, value?.toString() ?: "")
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}