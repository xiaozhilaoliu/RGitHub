package cn.renyuzhuo.rgithub.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event.EventBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoDetailBean;

/**
 * Created by renyuzhuo on 16-11-24.
 */
public class GitHubData {

    private static Map<String, OtherUserInfoDetailBean> otherUserInfoDetailBeanMap = new HashMap<>();

    private static Map<String, List<EventBean>> userEventBeansMap = new HashMap<>();
    private static Map<String, List<EventBean>> repoEventBeansMap = new HashMap<>();

    private static Map<String, List<OtherUserInfoBean>> followerOtherUserInfoBeansMap = new HashMap<>();
    private static Map<String, List<OtherUserInfoBean>> followingOtherUserInfoBeansMap = new HashMap<>();

    private static Map<String, List<RepoBean>> starRepoBeansMap = new HashMap<>();
    private static Map<String, List<RepoBean>> repoRepoBeansMap = new HashMap<>();

    private static Map<String, List<OtherUserInfoBean>> starOtherUserInfoBeansMap = new HashMap<>();
    private static Map<String, List<RepoBean>> forkRepoBeansMap = new HashMap<>();
    private static Map<String, List<OtherUserInfoBean>> watchOtherUserInfoBeansMap = new HashMap<>();

    public static Map<String, OtherUserInfoDetailBean> getOtherUserInfoDetailBeanMap() {
        return otherUserInfoDetailBeanMap;
    }

    public static Map<String, List<EventBean>> getUserEventBeansMap() {
        return userEventBeansMap;
    }

    public static Map<String, List<EventBean>> getRepoEventBeansMap() {
        return repoEventBeansMap;
    }

    public static Map<String, List<OtherUserInfoBean>> getFollowerOtherUserInfoBeansMap() {
        return followerOtherUserInfoBeansMap;
    }

    public static Map<String, List<OtherUserInfoBean>> getFollowingOtherUserInfoBeansMap() {
        return followingOtherUserInfoBeansMap;
    }

    public static Map<String, List<RepoBean>> getStarRepoBeansMap() {
        return starRepoBeansMap;
    }

    public static Map<String, List<RepoBean>> getRepoRepoBeansMap() {
        return repoRepoBeansMap;
    }

    public static Map<String, List<OtherUserInfoBean>> getStarOtherUserInfoBeansMap() {
        return starOtherUserInfoBeansMap;
    }

    public static Map<String, List<RepoBean>> getForkRepoBeansMap() {
        return forkRepoBeansMap;
    }

    public static Map<String, List<OtherUserInfoBean>> getWatchOtherUserInfoBeansMap() {
        return watchOtherUserInfoBeansMap;
    }
}
