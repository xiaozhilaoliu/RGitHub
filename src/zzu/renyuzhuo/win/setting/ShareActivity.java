package zzu.renyuzhuo.win.setting;

import zzu.renyuzhuo.score.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

@SuppressLint("InflateParams")
public class ShareActivity extends Activity {

	private static final String APP_ID = "";
	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
		regtoWx();
	}

	private void regtoWx() {
		api = WXAPIFactory.createWXAPI(this, APP_ID, true);
		api.registerApp(APP_ID);
	}

}
