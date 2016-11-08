package cn.renyuzhuo.rgithub.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class FragmentFactory {

    private static Map<Integer, Fragment> map;
    private FragmentTransaction trans;

    public FragmentFactory() {
        initFragment();
    }

    private void initFragment() {
        if (map == null) {
            map = new HashMap<>();
            map.put(R.id.first, new FirstFragment());
            map.put(R.id.second, new SecondFragment());
            map.put(R.id.third, new ThirdFragment());
            map.put(R.id.fourth, new FourthFragment());
        }
    }

    public void replaceFragment(Activity activity, int checkedId) {
        trans = activity.getFragmentManager().beginTransaction();
        trans.replace(R.id.fragment, map.get(checkedId));
        trans.commit();
    }
}
