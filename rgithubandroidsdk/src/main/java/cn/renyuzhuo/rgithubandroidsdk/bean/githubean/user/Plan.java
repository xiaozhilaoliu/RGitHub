package cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class Plan {
    private String name;

    private int space;

    private int collaborators;

    private int private_repos;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getSpace() {
        return this.space;
    }

    public void setCollaborators(int collaborators) {
        this.collaborators = collaborators;
    }

    public int getCollaborators() {
        return this.collaborators;
    }

    public void setPrivate_repos(int private_repos) {
        this.private_repos = private_repos;
    }

    public int getPrivate_repos() {
        return this.private_repos;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "name='" + name + '\'' +
                ", space=" + space +
                ", collaborators=" + collaborators +
                ", private_repos=" + private_repos +
                '}';
    }
}