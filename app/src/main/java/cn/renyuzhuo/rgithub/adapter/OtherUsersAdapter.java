package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        viewHolder.setDatas(getItem(position));
        return view;
    }

    class ViewHolder {
        private Object datas;

        public ViewHolder(View view) {

        }

        public void setDatas(OtherUserInfoBean datas) {
            this.datas = datas;
        }
    }
}
