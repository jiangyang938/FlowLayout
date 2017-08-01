package com.flowlayout.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/1.
 */
public class MainActivity extends Activity {
    @Bind(R.id.flowlayout)
    FlowLayout flowLayout;
    @Bind(R.id.add)
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flowlayout);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.add)
    public void onClick(View v){
        TextView tv=new TextView(MainActivity.this);
        tv.setText("增加TextView");
        tv.setBackground(getResources().getDrawable(R.drawable.shape_flow_layout));
        FlowLayout.LayoutParams lp=new  FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,120);
        lp.setMargins(30,30,30,30);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(15,15,15,15);
        tv.setLayoutParams(lp);
        flowLayout.addView(tv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
