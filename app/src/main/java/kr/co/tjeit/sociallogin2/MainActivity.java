package kr.co.tjeit.sociallogin2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.co.tjeit.sociallogin2.data.User;

public class MainActivity extends BaseActivity {

    User myUserInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();

//        메인액티비티는 로그인이 되어있는 상태에서만 들어올 수 있는 화면.
//        1. 로그인한 사용자의 이름 / 프로필 사진 표시.
//           => 멤버변수 myUserInfo를 활용
//        2. 로그아웃 버튼 설치 => 버튼 누르면 LoginActivity로 이동후 Main종료
//        3. ContextUtil 활용 실제로 로그아웃 (앱을 다시 키면 LoginActivity가 나타나게)
//        4. 페이스북 로그아웃

//        ※ 로그인 한 사용자의 이메일을 알아내자?
//          -> TextView 1개 추가설치, 이메일을 표시.

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
