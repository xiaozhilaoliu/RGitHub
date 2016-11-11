package cn.renyuzhuo.rgithubandroidsdk.bean.githubean.issues;

import java.util.List;

/**
 * Created by renyuzhuo on 16-11-11.
 */
public class IssuesBean {
    private String url;

    private String repository_url;

    private String labels_url;

    private String comments_url;

    private String events_url;

    private String html_url;

    private int id;

    private int number;

    private String title;

    private User user;

    private List<Labels> labels;

    private String state;

    private boolean locked;

    private String assignee;

    private List<String> assignees;

    private String milestone;

    private int comments;

    private String created_at;

    private String updated_at;

    private String closed_at;

    private String body;

    private Closed_by closed_by;

    public String getFullName() {
        if (repository_url != null) {
            return repository_url.replace("https://api.github.com/repos/", "");
        }
        return null;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }

    public String getRepository_url() {
        return this.repository_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public String getLabels_url() {
        return this.labels_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getComments_url() {
        return this.comments_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getEvents_url() {
        return this.events_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getHtml_url() {
        return this.html_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setLabels(List<Labels> labels) {
        this.labels = labels;
    }

    public List<Labels> getLabels() {
        return this.labels;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean getLocked() {
        return this.locked;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public void setAssignees(List<String> assignees) {
        this.assignees = assignees;
    }

    public List<String> getAssignees() {
        return this.assignees;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getMilestone() {
        return this.milestone;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getComments() {
        return this.comments;
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

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String getClosed_at() {
        return this.closed_at;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setClosed_by(Closed_by closed_by) {
        this.closed_by = closed_by;
    }

    public Closed_by getClosed_by() {
        return this.closed_by;
    }

    @Override
    public String toString() {
        return "IssuesBean{" +
                "url='" + url + '\'' +
                ", repository_url='" + repository_url + '\'' +
                ", labels_url='" + labels_url + '\'' +
                ", comments_url='" + comments_url + '\'' +
                ", events_url='" + events_url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", id=" + id +
                ", number=" + number +
                ", title='" + title + '\'' +
                ", user=" + user +
                ", labels=" + labels +
                ", state='" + state + '\'' +
                ", locked=" + locked +
                ", assignee='" + assignee + '\'' +
                ", assignees=" + assignees +
                ", milestone='" + milestone + '\'' +
                ", comments=" + comments +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", closed_at='" + closed_at + '\'' +
                ", body='" + body + '\'' +
                ", closed_by=" + closed_by +
                '}';
    }
}
