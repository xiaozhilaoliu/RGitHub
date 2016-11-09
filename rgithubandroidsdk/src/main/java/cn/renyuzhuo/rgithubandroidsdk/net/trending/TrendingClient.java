package cn.renyuzhuo.rgithubandroidsdk.net.trending;

import android.annotation.SuppressLint;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.trending.TrendingBean;
import cn.renyuzhuo.rlog.rlog;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public class TrendingClient {

    static Document doc;

    @SuppressLint("JavascriptInterface")
    public static void getTrending(final TrendingClientListener trendingClientListener, final String since, final String language) {

        final List<TrendingBean> trendingBeanList = new ArrayList<>();

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    rlog.d("begin get html");
                    String url = "https://github.com/trending/" + language + "?since=" + since;
                    rlog.d(url);
                    doc = Jsoup.connect(url).get();
                    subscriber.onCompleted();
                    rlog.d("finish get html");
                } catch (IOException e) {
                    rlog.ebegin();
                    e.printStackTrace();
                    rlog.eend();
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                        rlog.d("begin deal html");
                        Elements repoList = doc.getElementsByClass("repo-list");
                        if (repoList != null && repoList.size() != 0) {
                            Elements lis = repoList.get(0).getElementsByTag("li");
                            for (int i = 0; i < lis.size(); i++) {
                                TrendingBean trendingBean = new TrendingBean();
                                Elements h3 = lis.get(i).getElementsByTag("h3");
                                if (h3 != null && h3.size() != 0) {
                                    String h3String = h3.get(0).text();
                                    String[] name = h3String.split(" / ");
                                    if (name != null && name.length == 2) {
                                        trendingBean.setOwner(name[0]);
                                        trendingBean.setName(name[1]);
                                    }
                                }
                                Elements description = lis.get(i).getElementsByClass("py-1");
                                if (description != null && description.size() != 0) {
                                    String des = description.get(0).text();
                                    rlog.d(des);
                                    trendingBean.setDescription(des);
                                }
                                Elements programLanguage = lis.get(i).getElementsByAttributeValue("itemprop", "programmingLanguage");
                                if (programLanguage != null && programLanguage.size() != 0) {
                                    String lang = programLanguage.get(0).text();
                                    rlog.d(lang);
                                    trendingBean.setLanguage(lang);
                                }
                                Elements starFork = lis.get(i).getElementsByAttributeValue("class", "muted-link tooltipped tooltipped-s mr-3");
                                if (starFork != null && starFork.size() != 0) {
                                    int star = Integer.valueOf(starFork.get(0).text().replace(",", ""));
                                    trendingBean.setStars(star);
                                    if (starFork.size() != 1) {
                                        int fork = Integer.valueOf(starFork.get(1).text().replace(",", ""));
                                        trendingBean.setForks(fork);
                                    } else {
                                        trendingBean.setForks(0);
                                    }
                                } else {
                                    trendingBean.setStars(0);
                                    trendingBean.setForks(0);
                                }

                                Elements imgs = lis.get(i).getElementsByAttributeValue("class", "avatar mb-1");
                                if (imgs != null && imgs.size() != 0) {
                                    String src = imgs.get(0).attr("src");
                                    trendingBean.setAvatarUrl(src);
                                }

                                trendingBeanList.add(i, trendingBean);

                            }
                            rlog.d(trendingBeanList);
                            trendingClientListener.onGetTrendingSuccess(since + "/" + language, trendingBeanList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        rlog.d("err and maybe timeout");
                        trendingClientListener.onNetErr();
                    }

                    @Override
                    public void onNext(String s) {
                        rlog.d("get html not finish");
                    }
                });

    }
}
