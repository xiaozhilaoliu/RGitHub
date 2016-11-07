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
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.Items;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.search.SearchBean;

/**
 * Created by renyuzhuo on 16-11-7.
 */
public class SearchAdapter extends BaseAdapter {

    Context context;
    List<Items> itemses;

    public SearchAdapter(Context context, List<Items> itemses) {
        this.context = context;
        this.itemses = itemses;
    }

    @Override
    public int getCount() {
        if (itemses != null) {
            return itemses.size();
        }
        return 0;
    }

    @Override
    public Items getItem(int position) {
        if (itemses != null) {
            return itemses.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (itemses != null) {
            return itemses.get(position).hashCode();
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

    public void addSearchResult(List<Items> itemsList) {
        itemses.addAll(itemsList);
        notifyDataSetChanged();
    }


    public class ViewHolder {
        ImageView ownerAvatar;
        TextView repoName;
        TextView repoDescription;

        TextView repoLanguage, repoStarNum, repoForkNum;
        String fullName;

        public ViewHolder(View view) {
            ownerAvatar = (ImageView) view.findViewById(R.id.owner_avatar);
            repoName = (TextView) view.findViewById(R.id.repo_name);
            repoDescription = (TextView) view.findViewById(R.id.repo_description);
            repoLanguage = (TextView) view.findViewById(R.id.repo_language);
            repoStarNum = (TextView) view.findViewById(R.id.repo_star_num);
            repoForkNum = (TextView) view.findViewById(R.id.repo_fork_num);
        }

        public void setData(Items items) {
            Picasso.with(context).load(items.getOwner().getAvatar_url()).placeholder(R.drawable.logo).into(ownerAvatar);
            repoName.setText(items.getFull_name());
            repoDescription.setText(items.getDescription());
            repoLanguage.setText(items.getLanguage());
            repoStarNum.setText(String.valueOf(items.getStargazers_count()));
            repoForkNum.setText(String.valueOf(items.getForks_count()));
            fullName = items.getFull_name();
        }

        public String getFullName() {
            return fullName;
        }
    }

}
