package cn.renyuzhuo.rgithubandroidsdk.bean.githubean;

/**
 * Created by renyuzhuo on 16-10-31.
 */
public class AccessTokenBean {
    // {"access_token":"f31f5a4d4d72cb4ab7a9055992e83355b04236ad","token_type":"bearer","scope":"notifications,repo,user"}
    String access_token;
    String token_type;
    String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
