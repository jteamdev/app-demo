Communication between H5 and the App-Shell involves inserting the jsBridge object into the window under the webview. The App-Shell is expected to provide callback functions for postMessage.

The callback code in H5 is as follows:

```
window.jsBridge?.postMessage(eventName, params)
```

Passing Parameters:

| Name      | Type   | Descriptions                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| --------- | ------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| eventName | String | Page view: "page_view"<br>Open page: "open_page"<br>Close<br>page: "close_page"<br>Click register: "click_register"<br>Click OTP verification: "click_OTP_verify"<br>Click binding<br>bank: "click_binding_bank"<br>Click to receive free credit:<br>"click_free_credit"<br>Click promo code: "click_promo_code"<br>Sign-up success: "sign_up_success"<br>OTP verification<br>success: "OTP_verification_success"<br>Bank verification<br>success: "bank_verification_success"<br>Login success:<br>"login_success"<br>Click deposit button: "click_deposit"<br>Submit<br>deposit: "submit_deposit"<br>Deposit success: "purchase"<br>First deposit success: "First Deposit"<br>Click activity: "activity_click"<br>Click activity details: "activity_detail"<br>Click enter<br>game: "game_click"<br>Lobby click event: "lobby_click"<br>Search event: "search"<br>Pop-up error window: "error_event" |
| params    | Json   | Parameters：currency（Transaction currency）<br>value<br>（Transaction amount）<br>uidSite（User ID）                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |

# Usage Guidelines

- Redirect URL Configuration

```
// To test, you need to change the loadUrl variable in MainActivity.java to your own redirect URL.
private String loadUrl = "http://localhost:3000/";
```

- webView userAgent 設置

```
The userAgent must include the version number 'th1games_version'.
setting.setUserAgentString(setting.getUserAgentString() + " th1games_v" + BuildConfig.VERSION_NAME);
```

# Tracking Events

## User Attributes

| User Information     | Parameter Description                                                                                 |
| -------------------- | ----------------------------------------------------------------------------------------------------- |
| user_id              | User Account                                                                                          |
| channel              | User's Channel                                                                                        |
| subChannel           | User's Subchannel                                                                                     |
| level                | User Account Level                                                                                    |
| strategy             | Verification devices, such as phone, email, etc., may vary based on your site settings.               |
| verification_status  | User Verification Status (1: Verified; 0: Not Verified)                                               |
| depositStatus(topup) | User Deposit Status (1: Deposited; 0: Not Deposited)                                                  |
| scope                | User Account Status (test: Test Account; user: Formal User; guest: Not Registered Experience Account) |
| affiliateCode        | Affiliate Code when the user enters the website                                                       |
| user_tag_RFM         | User's RFM Tags                                                                                       |
| agent_id             | User's Agent                                                                                          |

## Events

**page_view**

- **Trigger Conditions**
  - Browsing history during page transition
- **Parameters**
  - location - Page title of the current location
  - page_title - Page title of the clicked page

**open_page**

- **Trigger Conditions**
  - Open Page
- **Parameters**
  - location - Page title of the current location
  - page_title - Page title of the clicked page
  - window_type - Type of window or modal
    - page - Full page
    - modal - Modal popup
    - modal_view - Switching within a modal
  - action_source - Action source triggering the opening of the page
    - click - Click
    - background - Background program invocation

**close_page**

- **Trigger Conditions**
  - Close Page
- **Parameters**
  - page_title - Page title of the closed page
  - window_type - Type of window or modal
    - page - Full page
    - modal - Modal popup
  - action_source - Action source triggering the closing
    - click - Click to close

## Registration Process Event - Click

**click_register**

- **Trigger Conditions**
  - Triggered when clicking the "Signup" button on the page for filling out registration information.
- **Parameters**
  - checkPoint - Clicked location for registration
    - loginPage - Login page
    - lobby - Lobby
    - deposit - Deposit
    - withdraw - Withdraw
    - enterGame_GameName - Enter game
    - promotion_ActivityName - Enter from the activity point

**click_OTP_verify**

- **Trigger Conditions**
  - Triggered when clicking the "Verify" button on the OTP verification page.
- **Parameters**
  - checkPoint - Time point when OTP verification is triggered
    - register - Registration
    - getReward - Reward redemption
    - deposit - Deposit
    - withdraw - Withdraw
    - enterGame - Enter game
    - bindBank - Bank verification
    - promoCode - Enter promo code

**click_binding_bank**

- **Trigger Conditions**
  - Click on Bank Information

**click_free_credit**

- **Trigger Conditions**
  - Click to receive free credit
  - This event may not occur if free rewards are automatically distributed.
- **Parameters**
  - activityName - Activity name (corresponding to the name set in the backend)

**click_promo_code**

- **Trigger Conditions**
  - Triggered when clicking the "Confirm" button on the Mission - Promo Code page.

## Registration Process Event - Success

**sign_up_success**

- **Trigger Conditions**
  - Callback event for successful registration.
- **Parameters**
  - checkPoint - Location where registration was clicked before successful registration
    - loginPage - Login page
    - lobby - Lobby
    - deposit - Deposit
    - withdraw - Withdraw
    - enterGame_GameName - Enter game
    - promotion_ActivityName - Enter from the activity point

**OTP_verification_success**

- **Trigger Conditions**
  - Callback event for successful OTP verification.
- **Parameters**
  - checkPoint - Location where OTP verification was triggered before successful verification
    - register - Registration
    - getReward - Reward redemption
    - deposit - Deposit
    - withdraw - Withdraw
    - enterGame - Enter game
    - bindBank - Bank verification
    - promoCode - Enter promo code

**bank_verification_success**

- **Trigger Conditions**
  - Callback event for successful bank verification.

## **Login Success**

**login_success**

- **Trigger Conditions**
  - Successful login.
- **Parameters**
  - checkPoint - Location where login was clicked before successful login
    - loginPage - Login page
    - lobby - Lobby
    - deposit - Deposit
    - withdraw - Withdraw
    - enterGame_GameName - Enter game
    - promotion_ActivityName - Enter from the activity point

## **Deposit-related**

**click_deposit**

- **Trigger Conditions**
  - Click on the deposit button.
- **Parameters**
  - location - Location of the click
    - depositIcon - Click on the deposit button below the lobby
    - coin - Click on the coin deposit button at the top left of the homepage
    - mission - Deposit button in the deposit activity on the activity page

**submit_deposit**

- **Trigger Conditions**
  - Event triggered when the player completes the deposit process information and clicks "Submit" to send the deposit request.
- **Parameters**
  - page_title - Deposit page title
  - currency - Currency
  - value - Numeric value

**purchase**

- **Trigger Conditions**
  - Successful deposit.
- **Parameters**
  - page_title - Deposit page title
  - currency - Currency
  - value - Numeric value

**First Deposit**

- **Trigger Conditions**
  - First deposit.
- **Parameters**
  - page_title - Deposit page title
  - currency - Currency
  - value - Numeric value

## **Activity-related Clicks**

**activity_click**

- **Trigger Conditions**
  - Click on an activity.
- **Parameters**
  - location - Location of the click
    - mission - Click on the activity in the mission
    - ad - Click on the activity in the left ad
  - template - Activity type
  - activityName - Activity name

**activity_detail**

- **Trigger Conditions**
  - Click on the activity details.
- **Parameters**
  - location - Location of clicking on activity information
    - mission - Click on the activity in the mission
  - template - Activity type
  - activityName - Activity name

## **Game Clicks**

**game_click**

- **Trigger Conditions**
  - Click to enter the game.
- **Parameters**
  - location - Location of clicking on the game
    - mission - Click to enter the game in the mission
    - ad - Click to enter the game in the left ad
    - Dynamic location name - Dynamic location name, such as slot, Hot, etc.
  - locationId
    - Dynamic location Id (This parameter will only exist when the location is a category)
  - provider - Game vendor name when clicking on the game
  - gameId - Game Id when clicking on the game
  - gameName - Game name when clicking on the game

## **Lobby Clicks**

**lobby_click**

- **Trigger Conditions**
  - Lobby click event.
- **Parameters**
  - location
    - lobby - Click on functional buttons
    - Dynamic location name - Dynamic location name, such as slot, Hot, etc.
  - locationId
    - Dynamic location Id (This parameter will only exist when the location is a category)
  - icon
    - Various button names
  - iconId
    - Dynamic location Id (This parameter will only exist when the icon is a category)

## **Search Events**

**search**

- **Trigger Conditions**
  - Search event.
- **Parameters**
  - searchTag
    - Clicked search tag

## **Error Message Records**

**error_event**

- **Trigger Conditions**
  - Recording error messages when the website displays an error window.
- **Parameters**
  - error_message
    - Error message
  - error_message_userVisible
    - Error message visible to the user in the frontend
  - error_code
    - Error code
  - page_title
    - Page where the error message occurred
