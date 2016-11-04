package cn.renyuzhuo.rgithubandroidsdk.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zzhoujay.richtext.RichText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.renyuzhuo.rgithubandroidsdk.R;
import cn.renyuzhuo.rgithubandroidsdk.net.file.FileClient;
import cn.renyuzhuo.rgithubandroidsdk.net.file.FileClientListener;
import okhttp3.ResponseBody;

public class MarkdownActivity extends Activity implements FileClientListener {

    Intent intent;
    String url;
    TextView markdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdown);

        intent = getIntent();
        url = intent.getStringExtra("url");
        markdownView = (TextView) findViewById(R.id.markdown);
        FileClient.downLoadFile(this, url);

    }

    @Override
    public void onDownload(ResponseBody responseBody) {
        InputStream input;

        try {
            input = responseBody.byteStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            StringBuilder content = new StringBuilder(input.available());
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
                content.append(System.getProperty("line.separator"));
            }
            String markdown = content.toString();
            RichText.fromMarkdown(markdown).into(markdownView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startMarkdownActivity(Context context, String downloadUrl) {
        Intent intent = new Intent(context, MarkdownActivity.class);
        intent.putExtra("url", downloadUrl);
        context.startActivity(intent);
    }
}
