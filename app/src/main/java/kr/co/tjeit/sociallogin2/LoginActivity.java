package kr.co.tjeit.sociallogin2;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends BaseActivity {

    CallbackManager cm;
    ProfileTracker pt;
    private com.facebook.login.widget.LoginButton fbLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        setupEvents();
        setValues();

//        XML에 설치된 페이스북 로그인 버튼 누르면
//        1. 실제로 로그인 반영 (버튼의 Text가 "로그아웃" 으로 바뀌는걸 확인)
//        2. 로그인 된 프로필을 추적후 저장 -> ContextUtil 활용  -> MainActivity로 이동




    }

    @Override
    public void setupEvents() {


    }

    @Override
    public void setValues() {
        cm = CallbackManager.Factory.create();
        fbLoginBtn.registerCallback(cm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
//                로그인 프로필 작업

                if (currentProfile != null) {
//                    누군가 로그인 했다.
                    currentProfile.getId();
                    currentProfile.getName();
                    currentProfile.getProfilePictureUri(500,500).toString();

                }
                else {
//                    로그아웃 했다.
                }

            }
        };

    }

//    화면이 완전히 종료되면, 프로필 추적 중지.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        pt.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cm.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void bindViews() {

        this.fbLoginBtn = (LoginButton) findViewById(R.id.fbLoginBtn);
    }
}
