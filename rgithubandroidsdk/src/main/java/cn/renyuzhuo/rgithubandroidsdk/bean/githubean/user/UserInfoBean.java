package cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class UserInfoBean {

    /*
     * {
     * "login": "RWebRTC",
     * "id": 21374839,
     * "avatar_url": "https://avatars.githubusercontent.com/u/21374839?v=3",
     * "gravatar_id": "",
     * "url": "https://api.github.com/users/RWebRTC",
     * "html_url": "https://github.com/RWebRTC",
     * "followers_url": "https://api.github.com/users/RWebRTC/followers",
     * "following_url": "https://api.github.com/users/RWebRTC/following{/other_user}",
     * "gists_url": "https://api.github.com/users/RWebRTC/gists{/gist_id}",
     * "starred_url": "https://api.github.com/users/RWebRTC/starred{/owner}{/repo}",
     * "subscriptions_url": "https://api.github.com/users/RWebRTC/subscriptions",
     * "organizations_url": "https://api.github.com/users/RWebRTC/orgs",
     * "repos_url": "https://api.github.com/users/RWebRTC/repos",
     * "events_url": "https://api.github.com/users/RWebRTC/events{/privacy}",
     * "received_events_url": "https://api.github.com/users/RWebRTC/received_events",
     * "type": "User",
     * "site_admin": false,
     * "name": "RWebRTC",
     * "company": null,
     * "blog": "http://renyuzhuo.cn",
     * "location": null,
     * "email": "rwebrtc@gmail.com",
     * "hireable": null,
     * "bio": "Read The Fucking Source Code.",
     * "public_repos": 5,
     * "public_gists": 0,
     * "followers": 1,
     * "following": 6,
     * "created_at": "2016-09-01T01:29:31Z",
     * "updated_at": "2016-11-01T01:59:21Z",
     * "private_gists": 0,
     * "total_private_repos": 0,
     * "owned_private_repos": 0,
     * "disk_usage": 189775,
     * "collaborators": 0,
     * "plan": {
     * "name": "free",
     * "space": 976562499,
     * "collaborators": 0,
     * "private_repos": 0
     * }
     * }
     */

    private static UserInfoBean userInfoBean;

    public static UserInfoBean getInstance() {
        if (userInfoBean == null) {
            userInfoBean = new UserInfoBean();
            return userInfoBean;
        } else {
            return userInfoBean;
        }
    }

    private String login;

    private int id;

    private String avatar_url;

    private String gravatar_id;

    private String url;

    private String html_url;

    private String followers_url;

    private String following_url;

    private String gists_url;

    private String starred_url;

    private String subscriptions_url;

    private String organizations_url;

    private String repos_url;

    private String events_url;

    private String received_events_url;

    private String type;

    private boolean site_admin;

    private String name;

    private String company;

    private String blog;

    private String location;

    private String email;

    private String hireable;

    private String bio;

    private int public_repos;

    private int public_gists;

    private int followers;

    private int following;

    private String created_at;

    private String updated_at;

    private int private_gists;

    private int total_private_repos;

    private int owned_private_repos;

    private int disk_usage;

    private int collaborators;

    private Plan plan;

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        UserInfoBean.userInfoBean = userInfoBean;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getGravatar_id() {
        return this.gravatar_id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getHtml_url() {
        return this.html_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowers_url() {
        return this.followers_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getFollowing_url() {
        return this.following_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getGists_url() {
        return this.gists_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getStarred_url() {
        return this.starred_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getSubscriptions_url() {
        return this.subscriptions_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getOrganizations_url() {
        return this.organizations_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getRepos_url() {
        return this.repos_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getEvents_url() {
        return this.events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getReceived_events_url() {
        return this.received_events_url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public boolean getSite_admin() {
        return this.site_admin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return this.company;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getBlog() {
        return this.blog;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setHireable(String hireable) {
        this.hireable = hireable;
    }

    public String getHireable() {
        return this.hireable;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return this.bio;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getPublic_repos() {
        return this.public_repos;
    }

    public void setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
    }

    public int getPublic_gists() {
        return this.public_gists;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowers() {
        return this.followers;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollowing() {
        return this.following;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setPrivate_gists(int private_gists) {
        this.private_gists = private_gists;
    }

    public int getPrivate_gists() {
        return this.private_gists;
    }

    public void setTotal_private_repos(int total_private_repos) {
        this.total_private_repos = total_private_repos;
    }

    public int getTotal_private_repos() {
        return this.total_private_repos;
    }

    public void setOwned_private_repos(int owned_private_repos) {
        this.owned_private_repos = owned_private_repos;
    }

    public int getOwned_private_repos() {
        return this.owned_private_repos;
    }

    public void setDisk_usage(int disk_usage) {
        this.disk_usage = disk_usage;
    }

    public int getDisk_usage() {
        return this.disk_usage;
    }

    public void setCollaborators(int collaborators) {
        this.collaborators = collaborators;
    }

    public int getCollaborators() {
        return this.collaborators;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Plan getPlan() {
        return this.plan;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", gravatar_id='" + gravatar_id + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", followers_url='" + followers_url + '\'' +
                ", following_url='" + following_url + '\'' +
                ", gists_url='" + gists_url + '\'' +
                ", starred_url='" + starred_url + '\'' +
                ", subscriptions_url='" + subscriptions_url + '\'' +
                ", organizations_url='" + organizations_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", events_url='" + events_url + '\'' +
                ", received_events_url='" + received_events_url + '\'' +
                ", type='" + type + '\'' +
                ", site_admin=" + site_admin +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", blog='" + blog + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", hireable='" + hireable + '\'' +
                ", bio='" + bio + '\'' +
                ", public_repos=" + public_repos +
                ", public_gists=" + public_gists +
                ", followers=" + followers +
                ", following=" + following +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", private_gists=" + private_gists +
                ", total_private_repos=" + total_private_repos +
                ", owned_private_repos=" + owned_private_repos +
                ", disk_usage=" + disk_usage +
                ", collaborators=" + collaborators +
                ", plan=" + plan +
                '}';
    }
}
