package amittpad.com.informationapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import amittpad.com.informationapp.MainActivity;
import amittpad.com.informationapp.R;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PrivacyPolicies extends Fragment {

    View view;
    @BindView(R.id.title)
    TextView title;
    @BindViews({R.id.menu, R.id.back})
    List<ImageView> imageViews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        title.setText("Privacy & Terms");
        imageViews.get(0).setVisibility(View.VISIBLE);
        imageViews.get(1).setVisibility(View.INVISIBLE);
        WebView webView = (WebView) view.findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/privacy.html");
        return view;
    }

    @OnClick(R.id.menu)
    public void onClick() {
        if (!MainActivity.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            MainActivity.drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

}
