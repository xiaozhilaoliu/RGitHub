package cn.renyuzhuo.rgithub.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.renyuzhuo.rgithub.R;

public class FourthFragment extends BaseFragment {

    private static final int loginRequestCode = 0;

    ImageView avatar;
    TextView name, bio;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
//        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
//        context = getActivity();
//        avatar = (ImageView) view.findViewById(R.id.avatar);
//        name = (TextView) view.findViewById(R.id.name);
//        bio = (TextView) view.findViewById(R.id.bio);
//        if (!RGitHubApplication.isLogin) {
//            Intent intent = new Intent(getActivity(), LoginActivity.class);
//            startActivityForResult(intent, loginRequestCode);
//        } else {
//            getUserInfo();
//        }
//        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case loginRequestCode: {
//                    if (RGitHubApplication.isLogin) {
//                        rlog.d("login success forthFragment");
//                        getUserInfo();
//                    } else {
//                        rlog.d("login fail forthFragment");
//                    }
//                    break;
//                }
//            }
//        }
//    }
//
//    public void getUserInfo() {
//        if (RGitHubApplication.user != null) {
//            initUserView();
//            return;
//        }
//        rlog.d("get UserInfo");
//        new GetAuthUserClient(accessToken)
//                .observable()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<User>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(User user) {
//                        rlog.d(user);
//                        RGitHubApplication.user = user;
//                        initUserView();
//                    }
//                });
//    }
//
//    private void initUserView() {
//        Picasso.with(context).load(RGitHubApplication.user.avatar_url).into(avatar);
//        name.setText(RGitHubApplication.user.name);
//        bio.setText(user.bio);
//    }
//
}
