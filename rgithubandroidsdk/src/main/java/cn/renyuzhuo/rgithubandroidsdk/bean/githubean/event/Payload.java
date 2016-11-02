package cn.renyuzhuo.rgithubandroidsdk.bean.githubean.event;

/**
 * Created by renyuzhuo on 16-11-2.
 */
public class Payload {
    private String action;

    private Issue issue;

    private String ref;

    private String ref_type;

    private String master_branch;

    private String description;

    private String pusher_type;

    private Forkee forkee;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRef_type() {
        return ref_type;
    }

    public void setRef_type(String ref_type) {
        this.ref_type = ref_type;
    }

    public String getMaster_branch() {
        return master_branch;
    }

    public void setMaster_branch(String master_branch) {
        this.master_branch = master_branch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPusher_type() {
        return pusher_type;
    }

    public void setPusher_type(String pusher_type) {
        this.pusher_type = pusher_type;
    }

    public Forkee getForkee() {
        return forkee;
    }

    public void setForkee(Forkee forkee) {
        this.forkee = forkee;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "action='" + action + '\'' +
                ", issue=" + issue +
                ", ref='" + ref + '\'' +
                ", ref_type='" + ref_type + '\'' +
                ", master_branch='" + master_branch + '\'' +
                ", description='" + description + '\'' +
                ", pusher_type='" + pusher_type + '\'' +
                ", forkee=" + forkee +
                '}';
    }
}
