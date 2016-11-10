package cn.renyuzhuo.autoupdate;

import com.google.gson.annotations.Expose;

/**
 * Created by renyuzhuo on 16-11-10.
 */
public class Version {
    @Expose
    private int versionCode;
    @Expose
    private String versionName;
    @Expose
    private String description;
    @Expose
    private String url;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Version{" +
                "versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
