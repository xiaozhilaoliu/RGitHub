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
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.trending.TrendingBean;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public class TrendingAdapter extends ListBaseAdapter {

    public TrendingAdapter(Context context, List<TrendingBean> trendingBeanList) {
        super(context, trendingBeanList);
    }

    @Override
    public TrendingBean getItem(int position) {
        if (lists != null) {
            return (TrendingBean) lists.get(position);
        }
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.repo_item, null, false);
            viewHolder = new TrendingAdapter.ViewHolder(view);
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

        String fullNema;

        public ViewHolder(View view) {
            ownerAvatar = (ImageView) view.findViewById(R.id.owner_avatar);
            repoName = (TextView) view.findViewById(R.id.repo_name);
            repoDescription = (TextView) view.findViewById(R.id.repo_description);
            repoLanguage = (TextView) view.findViewById(R.id.repo_language);
            repoStarNum = (TextView) view.findViewById(R.id.repo_star_num);
            repoForkNum = (TextView) view.findViewById(R.id.repo_fork_num);
        }

        public void setData(TrendingBean trendingBean) {
            Picasso.with(context).load(trendingBean.getAvatarUrl()).placeholder(R.drawable.logo).into(ownerAvatar);
            repoName.setText(trendingBean.getFullName());
            repoDescription.setText(trendingBean.getDescription());
            repoLanguage.setText(trendingBean.getLanguage());
            repoStarNum.setText(String.valueOf(trendingBean.getStars()));
            repoForkNum.setText(String.valueOf(trendingBean.getForks()));
            fullNema = trendingBean.getFullName();
        }

        public String getFullName() {
            return fullNema;
        }
    }
}
