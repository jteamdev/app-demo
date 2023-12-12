
### 马甲包推送事件

---

H5与马甲包的沟通，请马甲包在webview下的window插入对象jsBridge，内容提供回调函数postMessage

h5回调代码如下 : 

```jsx
window.jsBridge?.postMessage(eventName, params)
```

传递参数  : 

| 名称 | 类型 | 说明 |
| --- | --- | --- |
| eventName | String | 页面转换时的浏览纪录："page_view"
|  |  | 开启页面："open_page"
|  |  | 关闭页面："close_page"
|  |  | 点击注册："click_register"
|  |  | 点击OTP驗證："click_OTP_verify"
|  |  | 点击银行资讯："click_binding_bank"
|  |  | 点击领取freeCredit："click_free_credit"
|  |  | 点击promoCode："click_promo_code"
|  |  | 成功註册："sign_up_success"
|  |  | 成功验证OTP："OTP_verification_success"
|  |  | 成功验证银行资料："bank_verification_success"
|  |  | 登入成功："login_success"
|  |  | 点击充值按钮："click_deposit"
|  |  | 提交充值："submit_deposit"
|  |  | 充值成功："purchase"
|  |  | 首充成功："First Deposit"
|  |  | 点击活动："activity_click"
|  |  | 点击活动说明："activity_detail"
|  |  | 点击进入游戏："game_click"
|  |  | 大厅点击事件："lobby_click"
|  |  | 搜寻事件："search"
|  |  | 弹出错误视窗："error_event"



### 詳細查閱

---

[wiki](https://github.com/jteamdev/app-demo/wiki)
