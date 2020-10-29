package com.example.wzp109761.imitateclick;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_click_dot)
    TextView tvClickDot;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.et_x)
    EditText etX;
    @BindView(R.id.et_y)
    EditText etY;
    @BindView(R.id.et_test_count)
    EditText etTestCount;
    @BindView(R.id.et_per_time)
    EditText etPerTime;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.line_x)
    LinearLayout lineX;
    @BindView(R.id.line_y)
    LinearLayout lineY;
    boolean flag = false;

//    Intent in;
//    @BindView(R.id.btn_unbind)
//    Button btnUnbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        in = new Intent(this, BindService.class);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 每次 setChecked 时会触发onCheckedChanged 监听回调，而有时我们在设置setChecked后不想去自动触发 onCheckedChanged 里的具体操作, 即想屏蔽掉onCheckedChanged;加上此判断
                    Log.d("====", "onCheckedChanged: " + "true");
                    lineX.setVisibility(View.GONE);
                    lineY.setVisibility(View.GONE);
                    flag = true;
                } else {
                    Log.d("====", "onCheckedChanged: " + "false");
                    flag = false;
                    lineX.setVisibility(View.VISIBLE);
                    lineY.setVisibility(View.VISIBLE);
                }
            }
        });
        isClickable();

    }

    //    private BindService mService;
    private String TAG = "MainActivity";
//    ServiceConnection conn = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            Log.d(TAG, "onServiceConnected: service");
//            BindService.LocalBinder ser = (BindService.LocalBinder) service;
//            mService = ser.getService();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            mService = null;
//        }
//    };

    @OnClick({R.id.btn_start})
    public void onViewClicked() {
//        switch (view.getId()) {
//            case R.id.btn_start:
//                bindService(in, conn, Service.BIND_AUTO_CREATE);
//                break;
//            case R.id.btn_unbind :
//                if(mService!=null){
//                    Log.d(TAG, "onViewClicked: count"+mService.getCount());
//                }else {
//                    Log.d(TAG, "onViewClicked: 先绑定");
//                }
//                break;
//
//        }


        Intent intent = new Intent(getApplication(), ImitClickService.class);
        intent.putExtra("x", etX.getText().toString());
        intent.putExtra("y", etY.getText().toString());
        intent.putExtra("test_count", etTestCount.getText().toString());
        intent.putExtra("test_per_time", etPerTime.getText().toString());
        intent.putExtra("flag", true);
        startService(intent);
        this.setContentView(new MySurfaceView(this));
    }

    public void isClickable() {
        etTestCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                isEtEmpty();
            }
        });
        etPerTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                isEtEmpty();
            }
        });
        etX.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isEtEmpty();
            }
        });
        etY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isEtEmpty();
            }
        });

    }

    public void isEtEmpty() {
        if (flag) {
            if (etTestCount.getText().toString().equals("") || etPerTime.getText().toString().equals("")) {
                btnStart.setBackground(getResources().getDrawable(R.drawable.btn_start2));
                btnStart.setEnabled(false);
            } else {
                btnStart.setBackground(getResources().getDrawable(R.drawable.btn_start));
                btnStart.setEnabled(true);
            }
        } else {
            if (etTestCount.getText().toString().equals("") || etPerTime.getText().toString().equals("") || etX.getText().toString().equals("") || etY.getText().toString().equals("")) {
                btnStart.setBackground(getResources().getDrawable(R.drawable.btn_start2));
                btnStart.setEnabled(false);
            } else {
                btnStart.setBackground(getResources().getDrawable(R.drawable.btn_start));
                btnStart.setEnabled(true);
            }
        }
    }


}
