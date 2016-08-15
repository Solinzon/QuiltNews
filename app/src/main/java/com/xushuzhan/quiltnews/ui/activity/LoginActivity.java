package com.xushuzhan.quiltnews.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.presenter.LoginPresenter;
import com.xushuzhan.quiltnews.ui.iview.IloginView;

public class LoginActivity extends AppCompatActivity implements IloginView{
    public static final String TAG = "LoginActivity";
    EditText account;
    EditText password;
    RelativeLayout login;
    TextView signUpNow;
    LoginPresenter loginPresenter;
    TextView title;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        loginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.tv_title_toolbar);
        title.setText("登录");
        back = (ImageButton) findViewById(R.id.ib_toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        account = (EditText) findViewById(R.id.login_in_account);
        password = (EditText) findViewById(R.id.login_in_password);
        login = (RelativeLayout) findViewById(R.id.login_in_now);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
            }
        });
        signUpNow = (TextView) findViewById(R.id.tv_login_sign_up_now);
        signUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.signUp();
                Log.d(TAG, "onClick: ");
            }
        });
    }

    @Override
    public String getAccount() {
        return account.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hintLoading() {

    }

    @Override
    public void toMainActivity() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(LoginActivity.this,content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public EditText getEditAccount() {
        return account;
    }

    @Override
    public EditText getEditPassword() {
        return password;
    }

    @Override
    public void setError(EditText editText, String content) {
        editText.setError(content);
    }

    @Override
    public void toSignUpActivity(){
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter = null;
    }
}
