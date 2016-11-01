package cn.renyuzhuo.rgithub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.UserInfoBean;

public class OtherUserInfoActivity extends Activity {

    public static Map<String, OtherUserInfoBean> map = new HashMap<>();
    private Intent intent;
    OtherUserInfoBean otherUserInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_info);

        intent = getIntent();
        String login = intent.getStringExtra("login");
        otherUserInfoBean = map.get(login);

    }

    public static void startOtherUserInfoActivity(Context context, OtherUserInfoBean otherUserInfoBean) {
        Intent intent = new Intent(context, OtherUserInfoBean.class);
        map.put(otherUserInfoBean.getLogin(), otherUserInfoBean);
        intent.putExtra("login", otherUserInfoBean.getLogin());
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void clear() {
        map.clear();
    }
}
