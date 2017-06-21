package com.example.administrator.im.controller.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.im.R;
import com.example.administrator.im.controller.adapter.PickContactAdapter;
import com.example.administrator.im.model.Model;
import com.example.administrator.im.model.bean.PickContactInfo;
import com.example.administrator.im.model.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

//选择联系人页面
public class PickContactActivity extends AppCompatActivity {
    private PickContactAdapter pickContactAdapter;
    private List<PickContactInfo> mPicks;
private TextView tv_pick_save;
    private ListView lv_pick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_contact);
        initView();

        initData();
        
        initListener();
    }

    private void initListener() {
        //listview条目的点击事件
        lv_pick.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //checkbox的切换
                CheckBox cb_pick = (CheckBox) view.findViewById(R.id.cb_pick);
                cb_pick.setChecked(!cb_pick.isChecked());
                //修改数据
                mPicks.get(i).setChecked(cb_pick.isChecked());
                //刷新界面
                pickContactAdapter.notifyDataSetChanged();

            }
        });

        //保存按钮点击事件
        tv_pick_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取选择到的联系人
                List<String> names=pickContactAdapter.getPickContacts();
                //给启动页面返回数据
                Intent intent = new Intent();
                intent.putExtra("members",names.toArray(new String[0]));

                //设置返回的结果码
                setResult(RESULT_OK,intent);

                //结束当前页面
                finish();
            }
        });
    }

    private void initData() {
        //从本地数据库中获取所有联系人
        List<UserInfo> contacts = Model.getInstance().getDbManager().getContactTableDao().getContacts();
        mPicks=new ArrayList<>();
        if (contacts!=null&&contacts.size()>=0){
            //转换
            for (UserInfo contact : contacts){
                PickContactInfo pickContactInfo = new PickContactInfo(contact, false);
                mPicks.add(pickContactInfo);
            }
        }
        //初始化listview
        pickContactAdapter = new PickContactAdapter(this,mPicks);
        lv_pick.setAdapter(pickContactAdapter);
    }

    private void initView() {
        tv_pick_save= (TextView) findViewById(R.id.tv_pick_save);
        lv_pick= (ListView) findViewById(R.id.lv_pick);
    }
}
