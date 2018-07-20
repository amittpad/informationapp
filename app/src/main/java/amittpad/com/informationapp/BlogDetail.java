package amittpad.com.informationapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BlogDetail extends Activity {

    @BindView(R.id.title)
    TextView title;
    @BindViews({R.id.menu, R.id.back})
    List<ImageView> imageViews;
    public static int addTime = 1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String targetPackage = "com.abhiandroid.com.informationapp";
    InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_detail);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("rateUs", 0);
        editor = sharedPreferences.edit();
        Log.d("addTime", addTime + "");

        if (addTime % 5 == 0) {
            addTime = addTime + 1;
            if (sharedPreferences.getString("rate", "No").equalsIgnoreCase("No")) {
                showRateDialog();
            }
        } else if (addTime % 6 == 0) {
            showAd();
        } else {
            addTime = addTime + 1;
        }
        imageViews.get(0).setVisibility(View.INVISIBLE);
        imageViews.get(1).setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String fileName = intent.getStringExtra("fileName");
        title.setText(fileName.substring(0, fileName.length() - 5));
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings=webView.getSettings();
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.loadUrl("file:///android_asset/" + fileName);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("D075C3BB04030944B7F9397F23409900")
                .build();
        mAdView.loadAd(adRequest);
    }

    private void showAd() {
        addTime = addTime + 1;
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitialad));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("D075C3BB04030944B7F9397F23409900")
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    private void showRateDialog() {

        new AlertDialog.Builder(BlogDetail.this)
                .setTitle("Rate On Play Store")
                .setMessage("If you like this App. Please rate us on play Store.!!!")
                .setPositiveButton("Rate Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        editor.putString("rate", "Yes");
                        editor.commit();
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + targetPackage)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + targetPackage)));
                        }

                    }
                })
                .setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        editor.putString("rate", "No");
                        editor.commit();

                    }
                }).show();
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

}
