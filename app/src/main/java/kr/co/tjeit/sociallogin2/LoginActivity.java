package kr.co.tjeit.sociallogin2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends BaseActivity {

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

    }

    @Override
    public void bindViews() {

    }
}
