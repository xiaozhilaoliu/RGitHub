package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.user.OtherUserInfoBean;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public class OtherUsersAdapter extends BaseAdapter {
    private final List<OtherUserInfoBean> otherUserInfoBeenList;
    private final Context context;

    public OtherUsersAdapter(Context context, List<OtherUserInfoBean> otherUserInfoBeenList) {
        this.otherUserInfoBeenList = otherUserInfoBeenList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (otherUserInfoBeenList != null) {
            return otherUserInfoBeenList.size();
        }
        return 0;
    }

    @Override
    public OtherUserInfoBean getItem(int position) {
        if (otherUserInfoBeenList != null) {
            return otherUserInfoBeenList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (otherUserInfoBeenList != null) {
            return otherUserInfoBeenList.get(position).hashCode();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder.setData(getItem(position));
        return view;
    }

    public class ViewHolder {
        ImageView avatar;
        TextView name;

        public ViewHolder(View view) {
            avatar = (ImageView) view.findViewById(R.id.avatar);
            name = (TextView) view.findViewById(R.id.name);
        }

        public void setData(OtherUserInfoBean otherUserInfoBean) {
            name.setText(otherUserInfoBean.getLogin());
            Picasso.with(context).load(otherUserInfoBean.getAvatar_url()).placeholder(R.drawable.logo).into(avatar);
        }

        public TextView getName() {
            return name;
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "name=" + name.getText() +
                    '}';
        }
    }
}
