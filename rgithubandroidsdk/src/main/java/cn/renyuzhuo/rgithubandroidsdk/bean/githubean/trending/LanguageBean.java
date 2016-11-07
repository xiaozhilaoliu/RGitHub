package cn.renyuzhuo.rgithubandroidsdk.bean.githubean.trending;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public class LanguageBean {
    private String name;
    private String slug;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "LanguageBean{" +
                "name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
