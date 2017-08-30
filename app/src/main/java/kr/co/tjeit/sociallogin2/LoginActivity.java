package kr.co.tjeit.sociallogin2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import kr.co.tjeit.sociallogin2.util.ContextUtil;

public class LoginActivity extends BaseActivity {

    SessionCallback callback;

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

        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                Toast.makeText(mContext, "로그아웃.", Toast.LENGTH_SHORT).show();
            }
        });

//        XML에 설치된 페이스북 로그인 버튼 누르면
//        1. 실제로 로그인 반영 (버튼의 Text가 "로그아웃" 으로 바뀌는걸 확인)
//        2. 로그인 된 프로필을 추적후 저장 -> ContextUtil 활용  -> MainActivity로 이동

    }

    @Override
    public void setupEvents() {


    }

    @Override
    public void setValues() {

        callback = new SessionCallback();

        Session.getCurrentSession().addCallback(callback);
//        Session.getCurrentSession().checkAndImplicitOpen();



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

                    ContextUtil.login(mContext,
                            currentProfile.getId(),
                            currentProfile.getName(),
                            currentProfile.getProfilePictureUri(500,500).toString());

                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();


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
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }


        super.onActivityResult(requestCode, resultCode, data);
//        페이스북 로그인 콜백
        cm.onActivityResult(requestCode, resultCode, data);
//        카카오톡 로그인 처리


    }

    @Override
    public void bindViews() {

        this.fbLoginBtn = (LoginButton) findViewById(R.id.fbLoginBtn);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Toast.makeText(mContext, "로그인에 성공했다.", Toast.LENGTH_SHORT).show();

            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {

                }

                @Override
                public void onNotSignedUp() {

                }

                @Override
                public void onSuccess(UserProfile result) {

                    Toast.makeText(mContext, result.getNickname() + "님 로그인.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {

            Toast.makeText(mContext, "로그인에 실패했다.", Toast.LENGTH_SHORT).show();
        }
    }

}
