package cn.renyuzhuo.rgithubandroidsdk.bean.githubean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class OAuthBean implements Parcelable {

    private static OAuthBean oAuthBean;

    public static OAuthBean getInstance() {
        if (oAuthBean == null) {
            oAuthBean = new OAuthBean();
            return oAuthBean;
        } else {
            return oAuthBean;
        }
    }

    public static final Parcelable.Creator<OAuthBean> CREATOR =
            new Parcelable.Creator<OAuthBean>() {
                public OAuthBean createFromParcel(Parcel source) {
                    return new OAuthBean(source);
                }

                public OAuthBean[] newArray(int size) {
                    return new OAuthBean[size];
                }
            };
    public String client_id;
    public String client_secret;
    public String code;
    public String redirect_uri;

    public OAuthBean() {
    }

    protected OAuthBean(Parcel in) {
        this.client_id = in.readString();
        this.client_secret = in.readString();
        this.code = in.readString();
        this.redirect_uri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.client_id);
        dest.writeString(this.client_secret);
        dest.writeString(this.code);
        dest.writeString(this.redirect_uri);
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    @Override
    public String toString() {
        return "[id = " + getInstance().client_id +
                ", secret = " + getInstance().client_secret +
                ", code = " + getInstance().code +
                ", url = " + getInstance().redirect_uri + "]";
    }
}
