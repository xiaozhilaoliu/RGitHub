package cn.renyuzhuo.rgithubandroidsdk.bean.githubean.notify;

/**
 * Created by renyuzhuo on 16-11-25.
 */
public class NotifyBean {
    private String id;

    private boolean unread;

    private String reason;

    private String updated_at;

    private String last_read_at;

    private Subject subject;

    private Repository repository;

    private String url;

    private String subscription_url;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public boolean getUnread() {
        return this.unread;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setLast_read_at(String last_read_at) {
        this.last_read_at = last_read_at;
    }

    public String getLast_read_at() {
        return this.last_read_at;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return this.repository;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setSubscription_url(String subscription_url) {
        this.subscription_url = subscription_url;
    }

    public String getSubscription_url() {
        return this.subscription_url;
    }

    @Override
    public String toString() {
        return "NotifyBean{" +
                "id='" + id + '\'' +
                ", unread=" + unread +
                ", reason='" + reason + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", last_read_at='" + last_read_at + '\'' +
                ", subject=" + subject +
                ", repository=" + repository +
                ", url='" + url + '\'' +
                ", subscription_url='" + subscription_url + '\'' +
                '}';
    }
}
