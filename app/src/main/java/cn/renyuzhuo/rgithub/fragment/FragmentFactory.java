package cn.renyuzhuo.rgithub.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class FragmentFactory {

    private static SparseArray<Fragment> fragmentSparseArray;

    public FragmentFactory() {
        initFragment();
    }

    private void initFragment() {
        if (fragmentSparseArray == null) {
            fragmentSparseArray = new SparseArray<>();
            fragmentSparseArray.put(R.id.trending_icon, new FirstFragment());
            fragmentSparseArray.put(R.id.search_icon, new SearchFragment());
            fragmentSparseArray.put(R.id.second, new SecondFragment());
            fragmentSparseArray.put(R.id.third, new ThirdFragment());
            fragmentSparseArray.put(R.id.fourth, new FourthFragment());
        }
    }

    public void replaceFragment(Activity activity, int checkedId) {
        activity.getFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragmentSparseArray.get(checkedId))
                .commit();
    }

    public void onDestroy() {
        fragmentSparseArray = null;
    }
}
