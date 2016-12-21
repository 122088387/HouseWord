package com.fanziwen.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fanziwen.base.BaseActivity;
import com.fanziwen.base.HttpRequestBase;
import com.fanziwen.base.MyApplication;
import com.fanziwen.constant.Const;
import com.fanziwen.houseword.R;
import com.fanziwen.main.MainActivity;
import com.fanziwen.utils.AnimationUtil;
import com.fanziwen.utils.KeyBoardUtils;
import com.fanziwen.utils.SPUtils;
import com.fanziwen.utils.T;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 登陆页面
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    public static final int FINISH = 0x0004;

    private final String TAG = "LoginActivity";

    //上下文
    private Activity context;

    // Logo
    @ViewInject(R.id.login_image_logo)
    private ImageView logoIv;

    // 输入框父布局
    @ViewInject(R.id.login_ll)
    private FrameLayout inputLL;

    // 用户名输入框
    @ViewInject(R.id.login_et_username)
    private EditText userET;

    // 用户名删除
    @ViewInject(R.id.login_username_clear)
    private ImageView userClearIv;

    // 密码输入框
    @ViewInject(R.id.login_et_password)
    private EditText passwordET;

    // 密码删除
    @ViewInject(R.id.login_password_clear)
    private ImageView passwordClearIv;

    // 登陆按钮
    @ViewInject(R.id.login_btn_go)
    private Button goBtn;

    // 是否可以点击
    private boolean isGoClick = true;

    // 解析器
//    private LoginPresenter presenter;

    //默认是自动登录
//    private boolean autoLogin = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        context = this;

        executeAmin();
        createEditListener();
    }

    /**
     * 点击父布局取消软键盘
     */
    @Event(value = R.id.login_root)
    private void onRoot(View v) {
        KeyBoardUtils.closeKeybord(userET, this);
    }

    /**
     * 用户名删除按钮监听
     */
    @Event(value = R.id.login_username_clear)
    private void onUserClear(View v) {
        userET.setText("");
    }

    /**
     * 密码删除按钮监听
     */
    @Event(value = R.id.login_password_clear)
    private void onPasswordClear(View v) {
        passwordET.setText("");
    }

    /**
     * 登陆按钮点击事件
     *
     * @param v
     */
    @Event(value = R.id.login_btn_go)
    private void go(View v) {
        KeyBoardUtils.closeKeybord(userET, this);
        if (isGoClick) {
            isGoClick = false;
            String userName = userET.getText().toString();
            String password = passwordET.getText().toString();
            // 登陆操作
            login(userName, password);
        }
    }

    private void login(String userName, String userPassWord) {
        if (userName.length() == 11) {
            if (!userPassWord.isEmpty()) {
                try {
                    //正在登录时不让做任何操作
                    goBtn.setEnabled(false);
                    userET.setEnabled(false);
                    passwordET.setEnabled(false);
                    goBtn.setText(getResources().getText(R.string.is_login));//正在登陆
//                     获取手机token
//                    TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//                    String szImei = TelephonyMgr.getDeviceId();
                    RequestParams params = new RequestParams(Const.House.URL_HOUSE_USER_APP_LOGIN);
//                    loginName= 登录名称（手机号）&password=密码
                    params.addParameter("loginName", userName);
                    params.addParameter("password", userPassWord);
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            HttpRequestBase requestBase = MyApplication.gson.fromJson(result, HttpRequestBase.class);
//                            if (requestBase.getCode() == 1001) {
                                // 账号密码存入数据库
                                spStore(requestBase);
                                //跳转到主界面
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
//                            } else {
////                                T.showShort(this, requestBase.respMsg);
//                                goBtn.setEnabled(true);
//                                userET.setEnabled(true);
//                                passwordET.setEnabled(true);
//                                isGoClick = true;
//                                goBtn.setText(getResources().getText(R.string.login));
//                            }
                            T.showShort(LoginActivity.this, requestBase.getDesc());
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            goBtn.setEnabled(true);
                            userET.setEnabled(true);
                            passwordET.setEnabled(true);
                            isGoClick = true;
                            goBtn.setText("登陆");
                            ex.printStackTrace();
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
                } catch (Exception e) {
                    T.showShort(this, "请开启手机应用的权限");
                    goBtn.setEnabled(true);
                    userET.setEnabled(true);
                    passwordET.setEnabled(true);
                    isGoClick = true;
                    goBtn.setText("登陆");
                }
            } else {
                T.showShort(this, "密码不能为空");
                isGoClick = true;
            }
        } else {
            T.showShort(this, "请输入正确的手机号");
            isGoClick = true;
        }
    }

    /**
     * 初始化输入框监听
     */
    private void createEditListener() {
        if (userET.getText().length() != 0) {
            userClearIv.setVisibility(View.VISIBLE);
        }
        if (passwordET.getText().length() != 0) {
            passwordClearIv.setVisibility(View.VISIBLE);
        }
        userET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (userET.getText().length() != 0) {
                    AnimationUtil.setAlphaVisibility(userClearIv);
                } else {
                    AnimationUtil.setAlphaGone(userClearIv);
                }
                passwordET.setText("");
                AnimationUtil.setAlphaGone(passwordClearIv);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (userET.getText().length() == 0) {
                    KeyBoardUtils.openKeybord(userET, context);
                }
                if (passwordET.getText().length() != 0) {
                    AnimationUtil.setAlphaVisibility(passwordClearIv);
                } else {
                    AnimationUtil.setAlphaGone(passwordClearIv);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 执行动画
     */
    private void executeAmin() {
        // Logo弹出
        handler.sendEmptyMessageDelayed(0x0001, 100);
        // 输入框弹出
        handler.sendEmptyMessageDelayed(0x0002, 100);
        // 登陆按钮弹出
        handler.sendEmptyMessageDelayed(0x0003, 200);
    }

    // 此handler主要用于动画
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.login_logo);
            Animation animationInput = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.login_logo_input);
            switch (msg.what) {
                case 0x0001:
                    logoIv.setVisibility(View.VISIBLE);
                    logoIv.startAnimation(animation);
                    break;
                case 0x0002:
                    inputLL.setVisibility(View.VISIBLE);
                    inputLL.startAnimation(animationInput);
                    break;
                case 0x0003:
                    goBtn.setVisibility(View.VISIBLE);
                    goBtn.startAnimation(animationInput);
                    break;
                case FINISH:
                    finish();
                    break;
            }
        }
    };

    // 存入数据库账号密码信息
    private void spStore(HttpRequestBase requestBase) {
        SPUtils.remove(this, Const.SPDate.ID);
        SPUtils.remove(this, Const.SPDate.LONGING_NAME);
        SPUtils.remove(this, Const.SPDate.PASS_WORD);

        SPUtils.remove(LoginActivity.this, Const.SPDate.LOGIN_AUTO);


        SPUtils.put(this, Const.SPDate.LONGING_NAME, userET.getText().toString());
        SPUtils.put(this, Const.SPDate.PASS_WORD, passwordET.getText().toString());

        SPUtils.put(LoginActivity.this, Const.SPDate.LOGIN_AUTO, true);
    }

    private boolean spGet() {
        String un = (String) SPUtils.get(this, "loginName", "");
        String pw = (String) SPUtils.get(this, Const.SPDate.PASS_WORD, "");
        boolean isAuto = (boolean) SPUtils.get(this, Const.SPDate.LOGIN_AUTO, false);
        userET.setText(un);
        passwordET.setText(pw);
        if (un != null && pw != null && isAuto) {
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}