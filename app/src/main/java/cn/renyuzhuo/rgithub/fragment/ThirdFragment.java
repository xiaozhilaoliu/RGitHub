package cn.renyuzhuo.rgithub.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cn.renyuzhuo.rgithub.R;

public class ThirdFragment extends BaseListViewFragment {

    Context context;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third, container, false);
        context = getActivity();
        listView = (ListView) view.findViewById(R.id.listview);
        return view;
    }

}
