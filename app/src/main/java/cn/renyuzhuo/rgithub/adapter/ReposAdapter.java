package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public class ReposAdapter extends BaseAdapter {

    private Context context;
    private List<RepoBean> repoBeanList;

    public ReposAdapter(Context context, List<RepoBean> repoBeanList) {
        this.context = context;
        this.repoBeanList = repoBeanList;
    }

    @Override
    public int getCount() {
        if (repoBeanList != null) {
            return repoBeanList.size();
        }
        return 0;
    }

    @Override
    public RepoBean getItem(int position) {
        if (repoBeanList != null) {
            return repoBeanList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (repoBeanList != null) {
            return repoBeanList.get(position).hashCode();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.repo_item, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.setData(getItem(position));
        return view;

    }

    class ViewHolder {
        ImageView ownerAvatar;

        public ViewHolder(View view) {
            ownerAvatar = (ImageView) view.findViewById(R.id.owner_avatar);
        }

        public void setData(RepoBean repoBean) {
            Picasso.with(context).load(repoBean.getOwner().getAvatar_url()).placeholder(R.drawable.logo).into(ownerAvatar);
        }
    }

}
