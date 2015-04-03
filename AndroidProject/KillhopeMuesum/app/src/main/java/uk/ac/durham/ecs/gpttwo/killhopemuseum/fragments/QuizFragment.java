package uk.ac.durham.ecs.gpttwo.killhopemuseum.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import uk.ac.durham.ecs.gpttwo.killhopemuseum.R;


public class QuizFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);


        WebView wv = (WebView) rootView.findViewById(R.id.webview_quiz);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setDomStorageEnabled(true);
        if(Build.VERSION.SDK_INT >= 16) {
            wv.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        wv.loadUrl("file:///android_asset/Quiz.html");

        return rootView;
    }

    public static QuizFragment newInstance() {
        return new QuizFragment();
    }
}
