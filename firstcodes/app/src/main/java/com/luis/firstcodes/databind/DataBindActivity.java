package com.luis.firstcodes.databind;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.luis.firstcodes.R;
import com.luis.firstcodes.databinding.ActivityDataBindBinding;

public class DataBindActivity extends AppCompatActivity {

    private static final String TAG = DataBindActivity.class.getSimpleName();
    private MyViewModel viewModel;
    ActivityDataBindBinding dataBindBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBindBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);

        //setContentView(R.layout.activity_data_bind);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        /**
         * ①使用ViewModel的observe来实现更新UI
         */
        //TextView textView = findViewById(R.id.tv_score);
//        viewModel.getScore().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer score) {
//                Log.i(TAG, "onChanged called");
//                //textView.setText(String.valueOf(integer));
//                dataBindBinding.tvScore.setText(String.valueOf(score));
//            }
//        });
//

        /**
         * ②使用dataBinding更新UI
         * 使用 activie_data_bind.xml -> android:text="@{String.valueOf(score.getScore())}"
         * 替换上面的observers
         */
        dataBindBinding.setMyViewModel(viewModel);
        dataBindBinding.setLifecycleOwner(this);

        /**
         * 反向绑定
         * 使用 activie_data_bind.xml -> android:text="@{String.valueOf(score.getScore())}"
         * 代替下面的代码
         */
//        //findViewById(R.id.btn_incr)
//        dataBindBinding.btnIncr.setOnClickListener(v -> {
//            viewModel.increase();
//        });
//        //findViewById(R.id.btn_decr)
//        dataBindBinding.btnDecr.setOnClickListener(v -> {
//            viewModel.decrease();
//        });

    }
}
