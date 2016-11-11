package cn.renyuzhuo.rgithub.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.activity.PageHelper;
import cn.renyuzhuo.rgithub.activity.RepoDetailActivity;
import cn.renyuzhuo.rgithub.adapter.SearchAdapter;
import cn.renyuzhuo.rgithubandroidsdk.Dialog.LoadingDialog;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.SearchBean;
import cn.renyuzhuo.rgithubandroidsdk.net.search.SearchClient;

public class SearchFragment extends BaseListViewFragment {

    Context context;
    View view;
    Button search;
    EditText editText;
    String keyWord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        context = getActivity();
        listView = (ListView) view.findViewById(R.id.listview);
        search = (Button) view.findViewById(R.id.search);
        editText = (EditText) view.findViewById(R.id.search_edit);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = null;
                pageHelper = new PageHelper();
                keyWord = editText.getText().toString();
                keyWord = keyWord.trim().replace(" ", "+");
                if (keyWord.length() == 0) {
                    editText.setHint(getString(R.string.not_empty));
                    return;
                }
                SearchClient.getSearchResult(SearchFragment.this, keyWord, pageHelper.nextPage());
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                LoadingDialog.openLoadingDialogLoadingMore(context);
            }
        });
        adapter = null;
        return view;
    }

    @Override
    public void onGetSearchResult(SearchBean searchBean) {
        LoadingDialog.closeDialog();
        pageHelper.hasMoreOrNot(searchBean.getItems().size());
        if (adapter != null) {
            ((SearchAdapter) adapter).addSearchResult(searchBean.getItems());
            return;
        }
        adapter = new SearchAdapter(context, searchBean.getItems());
        initListView();
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

    @Override
    public void loadMore() {
        SearchClient.getSearchResult(this, keyWord, pageHelper.nextPage());
        LoadingDialog.openLoadingDialogLoadingMore(context);
    }
}
