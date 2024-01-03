# 马甲包推送事件 

H5与马甲包的沟通，请马甲包在webview下的window插入对象jsBridge，内容提供回调函数postMessage
```kotlin
webView.addJavascriptInterface(new JsInterface(), "jsBridge");
```
接收事件
```kotlin
public class JsInterface {
    // Android 调用 Js 方法1 中的返回值
    @JavascriptInterface
    public void postMessage(String name, String data) {
        Log.e(TAG, "name = " + name + "    data = " + data);
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(data)) {
            return;
        }
        AppsFlyerLibUtil.event(MainActivity.this, name, data);
    }
}

```

H5回调代码如下 : 

```jsx
window.jsBridge?.postMessage(eventName, params)
```

传递参数  : 

| 名称 | 类型 | 说明 |
| ---  | --- | --- |
| eventName | String | 页面转换时的浏览纪录："page_view"<br>开启页面："open_page"<br>关闭页面："close_page"<br>点击注册："click_register"<br>点击OTP驗證："click_OTP_verify"<br>点击银行资讯："click_binding_bank"<br>点击领取freeCredit："click_free_credit"<br>点击promoCode："click_promo_code"<br>成功註册："sign_up_success"<br>成功验证OTP："OTP_verification_success"<br>成功验证银行资料："bank_verification_success"<br>登入成功："login_success"<br>点击充值按钮："click_deposit"<br>提交充值："submit_deposit"<br>充值成功："purchase"<br>首充成功："first_deposit"<br>点击活动："activity_click"<br>点击活动说明："activity_detail"<br>点击进入游戏："game_click"<br>大厅点击事件："lobby_click"<br>搜寻事件："search"<br>弹出错误视窗："error_event" |
| params | Json | 参数：<br>currency（交易貨幣）<br>value（金额）<br>uidSite（用户id） |


## Firebase 傳送存款事件
purchase 事件需以 double 型態傳送 value 參數:

```jsx
Bundle params = new Bundle();
params.putDouble("value", 3.99);
params.putString("currency", "USD" );
```


# Documents
[Documents](https://github.com/jteamdev/app-demo/blob/main/README_EN.md)

[wiki](https://github.com/jteamdev/app-demo/wiki)
