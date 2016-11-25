package cn.renyuzhuo.rgithubandroidsdk.bean.githubean.notify;

/**
 * Created by renyuzhuo on 16-11-25.
 */
public class Subject {
    private String title;

    private String url;

    private String latest_comment_url;

    private String type;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setLatest_comment_url(String latest_comment_url) {
        this.latest_comment_url = latest_comment_url;
    }

    public String getLatest_comment_url() {
        return this.latest_comment_url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", latest_comment_url='" + latest_comment_url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
