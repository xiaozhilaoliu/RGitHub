package cn.renyuzhuo.rgithub.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import cn.renyuzhuo.rgithub.R;

public class FirstFragment extends BaseFragment {

    Spinner trending, language, repoDeve;
    ArrayAdapter trendingAdapter, languageAdapter, repoDeveAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        trendingAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.trending, android.R.layout.simple_spinner_item);
        trending = (Spinner) view.findViewById(R.id.trending);
        trending.setAdapter(trendingAdapter);

        languageAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.language, android.R.layout.simple_spinner_item);
        language = (Spinner) view.findViewById(R.id.language);
        language.setAdapter(languageAdapter);

        repoDeveAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.repo_deve, android.R.layout.simple_spinner_item);
        repoDeve = (Spinner) view.findViewById(R.id.repo_deve);
        repoDeve.setAdapter(repoDeveAdapter);
        return view;
    }



}
