package com.xushuzhan.quiltnews.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.presenter.SignUpPresenter;
import com.xushuzhan.quiltnews.ui.iview.ISignUpView;

public class SignUpActivity extends AppCompatActivity implements ISignUpView {
    public static final String TAG = "SignUpActivity";
    ImageView Back;
    TextView Title;
    EditText account;
    EditText password;
    SignUpPresenter signUpPresenter;
    RelativeLayout signUp;
    ImageButton ReadMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        signUpPresenter = new SignUpPresenter(this);
    }

    private void initView() {
        Back= (ImageView) findViewById(R.id.ib_toolbar_back);
        Back.setImageResource(R.drawable.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Title = (TextView) findViewById(R.id.tv_title_toolbar);
        Title.setText("注册");

        account = (EditText) findViewById(R.id.sign_up_account);
        //account.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        password = (EditText) findViewById(R.id.sign_up_password);

        signUp = (RelativeLayout) findViewById(R.id.sign_up_now);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpPresenter.signUp();
                Log.d("987654321", "onClick: ");
            }
        });

        ReadMode = (ImageButton) findViewById(R.id.ib_toobar_read_mode);
        ReadMode.setVisibility(View.INVISIBLE);
    }

    @Override
    public String getAccount() {
        return  account.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void showDialog(String content) {

    }

    @Override
    public void moveToMainActivity() {
        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(SignUpActivity.this, content, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showError(EditText editText, String content){
        editText.setError(content);
    }
    @Override
    public EditText getEditTextAccount(){
        return account;
    }
    @Override
    public EditText getEditTextPassword(){
        return password;
    }

    @Override
    public void setError(EditText editText, String content) {
        editText.setError(content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signUpPresenter = null;
    }
}
