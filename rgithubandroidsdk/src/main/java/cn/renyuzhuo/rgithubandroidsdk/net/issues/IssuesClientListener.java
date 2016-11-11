package cn.renyuzhuo.rgithubandroidsdk.net.issues;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.issues.IssuesBean;

/**
 * Created by renyuzhuo on 16-11-11.
 */
public interface IssuesClientListener {
    void onGetIssues(List<IssuesBean> issuesBean);
}
