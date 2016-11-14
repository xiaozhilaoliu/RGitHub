package cn.renyuzhuo.rgithubandroidsdk.net.issues;

import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.Token;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.issues.IssuesBean;
import cn.renyuzhuo.rgithubandroidsdk.net.Base.ApiBase.ApiBase;
import cn.renyuzhuo.rgithubandroidsdk.net.result.MySubscriber;
import cn.renyuzhuo.rgithubandroidsdk.service.issues.IssuesService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-11.
 */
public class IssuesClient {
    private static IssuesService issuesService = ApiBase.getInstance().build().create(IssuesService.class);

    public static void getIssues(final IssuesClientListener issuesClientListener, int page) {
        issuesService.getIssues("token " + Token.getAuthorization(), "updated", "subscribed", "all", page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<List<IssuesBean>>() {
                    @Override
                    public void onNext(List<IssuesBean> issuesBeanList) {
                        if (issuesClientListener != null) {
                            issuesClientListener.onGetIssues(issuesBeanList);
                        }
                    }
                });
    }
}
