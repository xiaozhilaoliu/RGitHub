package cn.renyuzhuo.rgithub.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.activity.RepoDetailActivity;
import cn.renyuzhuo.rgithub.adapter.TrendingAdapter;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.trending.TrendingBean;
import cn.renyuzhuo.rgithubandroidsdk.net.trending.TrendingClient;
import cn.renyuzhuo.rgithubandroidsdk.net.trending.TrendingClientListener;
import cn.renyuzhuo.rlog.rlog;

public class FirstFragment extends BaseListViewFragment implements TrendingClientListener {

    Spinner since, language;
    ArrayAdapter sinceAdapter, languageAdapter;
    private Context context;

    private static String slugString = "";
    private static String sinceString = "daily";
    private TrendingAdapter.ViewHolder holder;

    private static Map<String, List<TrendingBean>> mapTrending = new HashMap<>();

    private static boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        listView = (ListView) view.findViewById(R.id.listview);

        sinceAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.since, R.layout.first_fragment_spinner);
        since = (Spinner) view.findViewById(R.id.since);
        since.setAdapter(sinceAdapter);

        languageAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.language, R.layout.first_fragment_spinner);
        language = (Spinner) view.findViewById(R.id.language);
        language.setAdapter(languageAdapter);

        language.setOnItemSelectedListener(new MyListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view != null) {
                    slugString = ((TextView) view).getText().toString();
                    rlog.d(slugString);
                    initTrending();
                }
            }
        });

        since.setOnItemSelectedListener(new MyListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view != null) {
                    sinceString = ((TextView) view).getText().toString();
                    rlog.d(sinceString);
                    initTrending();
                }
            }
        });

        if (mapTrending.get(sinceString + "/" + slugString) != null) {
            adapter = new TrendingAdapter(context, mapTrending.get(sinceString + "/" + slugString));
            initListView();
        } else {
            initTrending();
        }

        return view;
    }

    private void initTrending() {
        if (mapTrending.get(sinceString + "/" + slugString) != null) {
            adapter = new TrendingAdapter(context, mapTrending.get(sinceString + "/" + slugString));
            initListView();
        } else {
            if (!isLoading) {
                isLoading = true;
                TrendingClient.getTrending(this, sinceString, slugString);
                LoadingDialog.openLoadingDialogLoading(context);
            }
        }
    }

    @Override
    public void onGetTrendingSuccess(String key, List<TrendingBean> trendingBeen) {
        isLoading = false;
        LoadingDialog.closeDialog();
        rlog.d(trendingBeen);
        mapTrending.put(key, trendingBeen);
        if (mapTrending.get(sinceString + "/" + slugString) != null) {
            adapter = new TrendingAdapter(context, mapTrending.get(sinceString + "/" + slugString));
            initListView();
            return;
        }
    }

    @Override
    public void onNetErr() {
        isLoading = false;
        LoadingDialog.closeDialog();
        Toast.makeText(context, getString(R.string.net_err), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void afterInitListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view != null) {
                    holder = ((TrendingAdapter.ViewHolder) view.getTag());
                    RepoDetailActivity.startRepoDetailActivity(context, holder.getFullName());
                }
            }
        });
    }

    class MyListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
