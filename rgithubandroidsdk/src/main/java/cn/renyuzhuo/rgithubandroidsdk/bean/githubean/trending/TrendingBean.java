package cn.renyuzhuo.rgithubandroidsdk.bean.githubean.trending;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public class TrendingBean {
    //{"owner":"verekia","name":"js-stack-from-scratch","url":"https://api.github.com/repos/verekia/js-stack-from-scratch",
    // "avatarUrl":"https://avatars.githubusercontent.com/u/522007?v=3",
    // "description":"Step-by-step tutorial to build a modern JavaScript stack from scratch",
    // "stars":6917,"forks":392}

    private String owner;
    private String name;
    private String avatarUrl;
    private String description;

    private String language;

    private int stars;
    private int forks;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return "https://api.github.com/repos/" + getFullName();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public String getFullName() {
        return owner + "/" + name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "TrendingBean{" +
                "owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", stars=" + stars +
                ", forks=" + forks +
                '}';
    }
}
