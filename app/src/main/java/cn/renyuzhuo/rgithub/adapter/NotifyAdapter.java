package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.notify.NotifyBean;

/**
 * Created by renyuzhuo on 16-11-25.
 */
public class NotifyAdapter extends ListBaseAdapter {
    public NotifyAdapter(Context context, List<NotifyBean> lists) {
        super(context, lists);
    }

    @Override
    public NotifyBean getItem(int position) {
        if (lists != null) {
            return (NotifyBean) lists.get(position);
        }
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.notify_item, null, false);
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
        TextView notifyDescription;
        String url;

        public ViewHolder(View view) {
            ownerAvatar = (ImageView) view.findViewById(R.id.owner_avatar);
            repoName = (TextView) view.findViewById(R.id.repo_name);
            notifyDescription = (TextView) view.findViewById(R.id.notify_description);
        }

        public void setData(NotifyBean notifyBean) {
            Picasso.with(context).load(notifyBean.getRepository().getOwner().getAvatar_url()).into(ownerAvatar);
            repoName.setText(notifyBean.getRepository().getFull_name());
            notifyDescription.setText(notifyBean.getSubject().getTitle());
            url = notifyBean.getSubject().getUrl().replaceFirst("api.", "").replaceFirst("repos/", "");
        }

        public String getUrl() {
            return url;
        }
    }
}
