package zzu.renyuzhuo.win.main;

import java.util.ArrayList;

import zzu.renyuzhuo.score.R;
import zzu.renyuzhuo.score.ScoreApplication;
import zzu.renyuzhuo.win.common.MyInformationActivity;
import zzu.renyuzhuo.win.score.MyScoreMainActivity;
import zzu.renyuzhuo.win.zzu.DivideIntoClassActivity;
import zzu.renyuzhuo.win.zzu.ZzuHistoricalEvolutionActivity;
import zzu.renyuzhuo.win.zzu.ZzuIntructionActivity;
import zzu.renyuzhuo.win.zzu.ZzuMapActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

@SuppressLint("InflateParams")
public class MainActivity extends Activity {

	public static MainActivity instance = null;
	/**
	 * 主内容窗口
	 */
	private ViewPager mTabPager;
	// 动画图片
	private ImageView mTabImg;
	private ImageView img_freshman, img_school, img_common, img_me;
	// 动画图片偏移量
	private int zero = 0;
	// 当前页卡编号
	private int currIndex = 0;
	// 单个水平动画位移
	private int one;
	private int two;
	private int three;
	private LinearLayout mCloseBtn;
	private View layout;
	private boolean menu_display = false;
	private PopupWindow menuWindow;
	private LayoutInflater inflater;

	// private Button mRightBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 启动activity时不自动弹出软键盘
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		instance = this;
		mTabPager = (ViewPager) findViewById(R.id.tabpager);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

		img_freshman = (ImageView) findViewById(R.id.img_freshman);
		img_school = (ImageView) findViewById(R.id.img_school);
		img_common = (ImageView) findViewById(R.id.img_common);
		img_me = (ImageView) findViewById(R.id.img_me);

		mTabImg = (ImageView) findViewById(R.id.img_main_buttom_bg);
		img_freshman.setOnClickListener(new MyOnClickListener(0));
		img_school.setOnClickListener(new MyOnClickListener(1));
		img_common.setOnClickListener(new MyOnClickListener(2));
		img_me.setOnClickListener(new MyOnClickListener(3));

		// 获取屏幕当前分辨率
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int displayWidth = dm.widthPixels;
		@SuppressWarnings("unused")
		int displayHeight = dm.heightPixels;

		one = displayWidth / 4; // 设置水平动画平移大小
		two = one * 2;
		three = one * 3;

		// InitImageView();//使用动画
		// 将要分页显示的View装入数组中
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.main_tab_freshman, null);
		View view2 = mLi.inflate(R.layout.main_tab_school, null);
		View view3 = mLi.inflate(R.layout.main_tab_common, null);
		View view4 = mLi.inflate(R.layout.main_tab_settings, null);

		// 每个页面的view数据
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);

		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mTabPager.setAdapter(mPagerAdapter);
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};

	/**
	 * 页卡切换监听(原作者:D.Winter)
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		Drawable drawable;
		int from, to;
		Animation animation = null;

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0)
				{
				case 0:
					drawable = ResourcesCompat.getDrawable(getResources(),
							R.drawable.tab_freshman_pressed, null);
					img_freshman.setImageDrawable(drawable);
					deal(currIndex, 0);
					break;
				case 1:
					drawable = ResourcesCompat.getDrawable(getResources(),
							R.drawable.tab_school_pressed, null);
					img_school.setImageDrawable(drawable);
					deal(currIndex, one);
					break;
				case 2:
					drawable = ResourcesCompat.getDrawable(getResources(),
							R.drawable.tab_common_pressed, null);
					img_common.setImageDrawable(drawable);
					deal(currIndex, two);
					break;
				case 3:
					drawable = ResourcesCompat.getDrawable(getResources(),
							R.drawable.tab_setting_pressed, null);
					img_me.setImageDrawable(drawable);
					deal(currIndex, three);
					break;
				}
			currIndex = arg0;
			// True:图片停在动画结束位置
			animation.setFillAfter(true);
			animation.setDuration(150);
			mTabImg.startAnimation(animation);
		}

		void deal(int currIndex, int to) {
			switch (currIndex)
				{
				case 0: {
					animation = new TranslateAnimation(zero, to, 0, 0);
					drawable = ResourcesCompat.getDrawable(getResources(),
							R.drawable.tab_freshman_normal, null);
					img_freshman.setImageDrawable(drawable);
					break;
				}
				case 1: {
					animation = new TranslateAnimation(one, to, 0, 0);
					drawable = ResourcesCompat.getDrawable(getResources(),
							R.drawable.tab_school_normal, null);
					img_school.setImageDrawable(drawable);
					break;
				}
				case 2: {

					animation = new TranslateAnimation(two, to, 0, 0);
					drawable = ResourcesCompat.getDrawable(getResources(),
							R.drawable.tab_common_normal, null);
					img_common.setImageDrawable(drawable);
					break;
				}
				case 3: {
					animation = new TranslateAnimation(three, to, 0, 0);
					drawable = ResourcesCompat.getDrawable(getResources(),
							R.drawable.tab_setting_normal, null);
					img_me.setImageDrawable(drawable);
					break;
				}
				}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// 获取back键
			if (menu_display) {
				// 如果 Menu已经打开,关闭Menu
				menuWindow.dismiss();
				menu_display = false;
			} else {
				finish();
			}
		} else if (keyCode == KeyEvent.KEYCODE_MENU) {
			// 获取 Menu键
			if (!menu_display) {
				// 获取LayoutInflater实例
				inflater = (LayoutInflater) this
						.getSystemService(LAYOUT_INFLATER_SERVICE);
				// 该方法返回的是一个View的对象，是布局中的根
				layout = inflater.inflate(R.layout.main_menu, null);

				// 将layout加入到PopupWindow中,后两个参数是width和height
				menuWindow = new PopupWindow(layout, LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT);
				// 设置layout在PopupWindow中显示的位置
				menuWindow.showAtLocation(this.findViewById(R.id.mainweixin),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				// 获取main中的控件
				mCloseBtn = (LinearLayout) layout
						.findViewById(R.id.menu_close_btn);

				// 对每一个Layout进行单击事件的注册吧
				mCloseBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						finish();
						menuWindow.dismiss();
					}
				});
				menu_display = true;
			} else {
				menuWindow.dismiss();
				menu_display = false;
			}

			return false;
		}
		return false;
	}

	public void zzuIntroduction(View view) {
		Intent intent = new Intent(MainActivity.this,
				ZzuIntructionActivity.class);
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void historicalEvolution(View view) {
		Intent intent = new Intent(MainActivity.this,
				ZzuHistoricalEvolutionActivity.class);
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void zzuMap(View view) {
		Intent intent = new Intent(MainActivity.this, ZzuMapActivity.class);
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void logout(View view) {
		ScoreApplication.del();
		Toast.makeText(this, "已经退出登陆", Toast.LENGTH_SHORT).show();
	}

	public void schoolEnvironment(View view) {
		Toast.makeText(this, "学校这么大,自己去看吧:)", Toast.LENGTH_SHORT).show();
	}

	public void divideIntoClass(View view) {
		Intent intent = new Intent(MainActivity.this,
				DivideIntoClassActivity.class);
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void login(View view) {
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void myscore(View view) {
		Intent intent = new Intent(MainActivity.this, MyScoreMainActivity.class);
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	public void myInformation(View view) {
		Intent intent = new Intent(MainActivity.this,
				MyInformationActivity.class);
		instance.startActivity(intent);
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}
}
