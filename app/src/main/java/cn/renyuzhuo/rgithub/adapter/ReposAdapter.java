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
import java.util.StringTokenizer;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.repo.RepoBean;

/**
 * Created by renyuzhuo on 16-11-1.
 */
public class ReposAdapter extends ListBaseAdapter {

    private boolean isFullName = false;

    public ReposAdapter(Context context, List<RepoBean> repoBeanList) {
        super(context, repoBeanList);
    }

    public ReposAdapter(Context context, List<RepoBean> repoBeanList, boolean isFullName) {
        this(context, repoBeanList);
        this.isFullName = isFullName;
    }

    @Override
    public RepoBean getItem(int position) {
        if (lists != null) {
            return (RepoBean) lists.get(position);
        }
        return null;
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

    public class ViewHolder {
        ImageView ownerAvatar;
        TextView repoName;
        TextView repoDescription;

        TextView repoLanguage, repoStarNum, repoForkNum;

        public ViewHolder(View view) {
            ownerAvatar = (ImageView) view.findViewById(R.id.owner_avatar);
            repoName = (TextView) view.findViewById(R.id.repo_name);
            repoDescription = (TextView) view.findViewById(R.id.repo_description);
            repoLanguage = (TextView) view.findViewById(R.id.repo_language);
            repoStarNum = (TextView) view.findViewById(R.id.repo_star_num);
            repoForkNum = (TextView) view.findViewById(R.id.repo_fork_num);
        }

        public void setData(RepoBean repoBean) {
            Picasso.with(context).load(repoBean.getOwner().getAvatar_url()).placeholder(R.drawable.logo).into(ownerAvatar);
            if ((isFullName)) {
                repoName.setText(repoBean.getFull_name());
            } else {
                repoName.setText(repoBean.getName());
            }
            repoDescription.setText(repoBean.getDescription());
            repoLanguage.setText(repoBean.getLanguage());
            repoStarNum.setText(String.valueOf(repoBean.getStargazers_count()));
            repoForkNum.setText(String.valueOf(repoBean.getForks_count()));
        }
    }

}
