package kr.co.tjeit.sociallogin2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.sociallogin2.data.User;
import kr.co.tjeit.sociallogin2.util.ContextUtil;

public class MainActivity extends BaseActivity {

    User myUserInfo = null;
    private de.hdodenhof.circleimageview.CircleImageView profileImg;
    private android.widget.TextView nameTxt;
    private android.widget.Button logoutBtn;

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
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                앱 자체 로그아웃
                ContextUtil.logout(mContext);

//                페이스북 로그아웃 처리
                LoginManager.getInstance().logOut();

                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void setValues() {
        myUserInfo = ContextUtil.getLoginUserData(mContext);

        nameTxt.setText(myUserInfo.getName());

        Glide.with(mContext).load(myUserInfo.getProfileURL()).into(profileImg);


    }

    @Override
    public void bindViews() {
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);

    }
}
