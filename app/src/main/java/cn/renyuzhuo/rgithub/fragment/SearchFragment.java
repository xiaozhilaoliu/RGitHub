package cn.renyuzhuo.rgithub.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.activity.PageHelper;
import cn.renyuzhuo.rgithub.activity.RepoDetailActivity;
import cn.renyuzhuo.rgithub.adapter.SearchAdapter;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.Items;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.SearchBean;
import cn.renyuzhuo.rgithubandroidsdk.net.search.SearchClient;
import cn.renyuzhuo.rlog.rlog;

public class SearchFragment extends BaseListViewFragment {

    Context context;
    View view;
    Button search;
    EditText editText;
    String keyWord;

    private static Map<String, List<Items>> mapItems = new HashMap<>();
    private static Map<String, Integer> mapCount = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        context = getActivity();

        listView = (ListView) view.findViewById(R.id.listview);
        search = (Button) view.findViewById(R.id.search);
        editText = (EditText) view.findViewById(R.id.search_edit);

        initSearchClick();

        if (keyWord != null && keyWord.length() != 0) {
            if (mapItems.get(keyWord) != null) {
                initAdapterAndAddToList(keyWord);
            }
        }
        return view;
    }

    private void initSearchClick() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = null;
                // 处理关键子为去掉空格的小写字母
                keyWord = editText.getText().toString().trim().toLowerCase();
                rlog.d("keyWord: " + keyWord);
                if (keyWord.length() == 0) {
                    editText.setHint(getString(R.string.please_input));
                    return;
                }
                if (mapItems.get(keyWord) != null) {
                    initAdapterAndAddToList(keyWord);
                    return;
                }
                pageHelper = new PageHelper();
                closeInput();
                LoadingDialog.openLoadingDialogLoadingMore(context);
                SearchClient.getSearchResult(SearchFragment.this, keyWord, pageHelper.nextPage());

            }

        });
    }

    @Override
    public void onGetSearchResult(String key, SearchBean searchBean) {
        LoadingDialog.closeDialog();
        if (adapter != null) {
            // 加载更多，追加结果
            pageHelper.hasMoreOrNot(searchBean.getItems().size());
            adapter.addLists(searchBean.getItems());
            return;
        }
        // 查询某一关键字
        mapItems.put(key, searchBean.getItems());
        mapCount.put(key, searchBean.getTotal_count());
        initAdapterAndAddToList(key);
    }

    private void initAdapterAndAddToList(String key) {
        closeInput();
        pageHelper = new PageHelper(mapItems.get(key).size());
        adapter = new SearchAdapter(context, mapItems.get(key));
        initListView();
        Toast.makeText(context, mapCount.get(key) + " " + getString(R.string.results),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void afterInitListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fullName = ((SearchAdapter.ViewHolder) view.getTag()).getFullName();
                RepoDetailActivity.startRepoDetailActivity(context, fullName);
            }
        });
    }

    private void closeInput() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Override
    public void loadMore() {
        SearchClient.getSearchResult(this, keyWord, pageHelper.nextPage());
        LoadingDialog.openLoadingDialogLoadingMore(context);
    }
}
