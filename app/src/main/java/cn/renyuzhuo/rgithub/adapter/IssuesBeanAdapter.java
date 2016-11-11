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
import cn.renyuzhuo.rgithub.utils.DateUtil;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.issues.IssuesBean;

/**
 * Created by renyuzhuo on 16-11-11.
 */
public class IssuesBeanAdapter extends BaseAdapter {

    Context context;
    List<IssuesBean> issuesBeanList;

    public IssuesBeanAdapter(Context context, List<IssuesBean> issuesBeanList) {
        this.context = context;
        this.issuesBeanList = issuesBeanList;
    }

    @Override
    public int getCount() {
        if (issuesBeanList != null) {
            return issuesBeanList.size();
        }
        return 0;
    }

    @Override
    public IssuesBean getItem(int position) {
        if (issuesBeanList != null) {
            return issuesBeanList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (issuesBeanList != null) {
            return issuesBeanList.get(position).hashCode();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.issue_item, null, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.setData(getItem(position));
        return view;
    }

    public void addIssues(List<IssuesBean> issuesBeanList) {
        this.issuesBeanList.addAll(issuesBeanList);
        notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView tvIssueNumber;
        TextView tvIssueRepoName;
        TextView tvIssueTitle;
        ImageView ivAvatar;
        TextView tvIssueComments;
        TextView tvIssueCreation;

        public ViewHolder(View view) {
            tvIssueNumber = (TextView) view.findViewById(R.id.tv_issue_number);
            tvIssueRepoName = (TextView) view.findViewById(R.id.tv_issue_repo_name);
            tvIssueTitle = (TextView) view.findViewById(R.id.tv_issue_title);
            ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
            tvIssueComments = (TextView) view.findViewById(R.id.tv_issue_comments);
            tvIssueCreation = (TextView) view.findViewById(R.id.tv_issue_creation);
        }

        public void setData(IssuesBean issuesBean) {
            tvIssueNumber.setText(context.getString(R.string.sp) + String.valueOf(issuesBean.getNumber()));
            tvIssueRepoName.setText(issuesBean.getFullName());
            tvIssueTitle.setText(issuesBean.getTitle());
            Picasso.with(context).load(issuesBean.getUser().getAvatar_url()).placeholder(R.drawable.logo).into(ivAvatar);
            tvIssueComments.setText(String.valueOf(issuesBean.getComments()));
            tvIssueCreation.setText(issuesBean.getUser().getLogin() + " " + DateUtil.formate(issuesBean.getCreated_at()));
        }
    }
}
