package zzu.renyuzhuo.win.common;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.win.main.WebIndex;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class ServiceInternetActivity extends Activity {
	public static ServiceInternetActivity instance = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_internet_web);
		instance = this;
	}

	public void ips(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "新校区学生宿舍IP分配总表");
		intent.putExtra("url", "http://service.zzu.edu.cn/ip.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void macSezrch(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "MAC查询方法");
		intent.putExtra("url", "http://service.zzu.edu.cn/mobile/mac.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void internetRegist(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "新校区网络自助服务");
		intent.putExtra("url",
				"http://202.196.112.253:8080/selfservice/index.jsp");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void ruijieDownload(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "认证客户端软件下载");
		intent.putExtra("url", "http://service.zzu.edu.cn/cyxz.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void wlanIntruction(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "校园网无线网络使用说明");
		intent.putExtra("url", "http://service.zzu.edu.cn/mobile/wxsm.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void serviceInternet(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "校园网服务中心");
		intent.putExtra("url", "http://service.zzu.edu.cn/mobile/index.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void howToRegist(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "校园网学生用户注册流程");
		intent.putExtra("url", "http://service.zzu.edu.cn/mobile/zc.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void ruijieInstall(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "锐捷客户端安装流程");
		intent.putExtra("url", "http://service.zzu.edu.cn/mobile/rjaz.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void troubleFAQ(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "网络故障FAQ查询");
		intent.putExtra("url", "http://service.zzu.edu.cn/mobile/faq.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void serviceCommopnPhone(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "常用服务电话");
		intent.putExtra("url", "http://service.zzu.edu.cn/mobile/fwdh.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void servicePhone(View view) {
		Intent intent = new Intent(ServiceInternetActivity.this, WebIndex.class);
		intent.putExtra("title", "网络中心常用服务电话");
		intent.putExtra("url", "http://service.zzu.edu.cn/mobile/fwdh.html");
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
		}
		return super.onKeyDown(keyCode, event);
	}

	public void back(View view) {
		finish();
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	}
}
