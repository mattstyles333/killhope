package uk.ac.durham.ecs.gpttwo.killhopemuesum.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import uk.ac.durham.ecs.gpttwo.killhopemuesum.R;


public class QuizFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

        WebView wv = (WebView)rootView.findViewById(R.id.webview_quiz);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description,String failingUrl){
            }
        });
        wv.loadUrl("community.dur.ac.uk/m.p.szuplewski/KillhopeQuiz/");
        return rootView;
    }

    public static QuizFragment newInstance(){
        return new QuizFragment();

    }
}
