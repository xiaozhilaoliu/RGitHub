package cn.renyuzhuo.rgithub.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.renyuzhuo.rgithub.R;
import cn.renyuzhuo.rgithub.utils.DateUtil;
import cn.renyuzhuo.rgithubandroidsdk.bean.githubean.issues.IssuesBean;

/**
 * Created by renyuzhuo on 16-11-11.
 */
public class IssuesBeanAdapter extends ListBaseAdapter {

    public IssuesBeanAdapter(Context context, List<IssuesBean> issuesBeanList) {
        super(context, issuesBeanList);
    }

    @Override
    public IssuesBean getItem(int position) {
        if (lists != null) {
            return (IssuesBean) lists.get(position);
        }
        return null;
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

    public class ViewHolder {
        LinearLayout issuesBack;
        TextView tvIssueNumber;
        TextView tvIssueRepoName;
        TextView tvIssueTitle;
        ImageView ivAvatar;
        TextView tvIssueComments;
        TextView tvIssueCreation;
        private String issueUrl;

        public ViewHolder(View view) {
            issuesBack = (LinearLayout) view.findViewById(R.id.issues_back);
            tvIssueNumber = (TextView) view.findViewById(R.id.tv_issue_number);
            tvIssueRepoName = (TextView) view.findViewById(R.id.tv_issue_repo_name);
            tvIssueTitle = (TextView) view.findViewById(R.id.tv_issue_title);
            ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
            tvIssueComments = (TextView) view.findViewById(R.id.tv_issue_comments);
            tvIssueCreation = (TextView) view.findViewById(R.id.tv_issue_creation);
        }

        public void setData(IssuesBean issuesBean) {
            if (issuesBean.getState().equals("open")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    issuesBack.setBackgroundColor(context.getResources().getColor(R.color.event_open, null));
                } else {
                    issuesBack.setBackgroundColor(context.getResources().getColor(R.color.event_open));
                }
            } else if (issuesBean.getState().equals("closed")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    issuesBack.setBackgroundColor(context.getResources().getColor(R.color.event_del, null));
                } else {
                    issuesBack.setBackgroundColor(context.getResources().getColor(R.color.event_del));
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    issuesBack.setBackgroundColor(context.getResources().getColor(R.color.white, null));
                } else {
                    issuesBack.setBackgroundColor(context.getResources().getColor(R.color.white));
                }
            }

            tvIssueNumber.setText(context.getString(R.string.sp) + String.valueOf(issuesBean.getNumber()));
            tvIssueRepoName.setText(issuesBean.getFullName());
            tvIssueTitle.setText(issuesBean.getTitle());
            Picasso.with(context).load(issuesBean.getUser().getAvatar_url()).placeholder(R.drawable.logo).into(ivAvatar);
            tvIssueComments.setText(String.valueOf(issuesBean.getComments()));
            tvIssueCreation.setText(issuesBean.getUser().getLogin() + " " + DateUtil.formate(issuesBean.getUpdated_at()));
            issueUrl = issuesBean.getHtml_url();
        }

        public String getIssueUrl() {
            return issueUrl;
        }
    }
}
