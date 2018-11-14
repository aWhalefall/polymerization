package com.appcomponent.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class ProgressWebView extends WebView {
    private ProgressBar progressBar;
    private ChromeClient chromeClient;
    private MyWebClient myWebClient;
    private ProgressWebLisneter progressWebLisneter;
    private Context mContext;

    @SuppressWarnings("deprecation")
    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        progressBar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                5, 0, 0));
        addView(progressBar);
        progressBar.setMax(100);
        progressBar.setVisibility(GONE);
        chromeClient = new ChromeClient(progressBar);
        setWebChromeClient(chromeClient);
    }

    public void setData(String cardMobile, long order, String mSendAmount, long iscert) {
        myWebClient = new MyWebClient(cardMobile, order, mSendAmount, iscert);
        setWebViewClient(myWebClient);
    }


    @SuppressWarnings("deprecation")
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public ChromeClient getWebChrome() {
        return chromeClient;
    }

    class ChromeClient extends WebChromeClient {
        private ProgressBar progressBar;

        public ChromeClient(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsTimeout() {
            return super.onJsTimeout();
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            System.out.println("progress =" + newProgress);
            progressBar.setProgress(newProgress);
            progressBar.setVisibility(newProgress >= 100 ? View.GONE : View.VISIBLE);
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (progressWebLisneter != null) {
                progressWebLisneter.callBackHtmlTitle(title);
            }
        }

    }

    class MyWebClient extends WebViewClient {
        private String cardMobile;
        private long order;
        private String mSendAmount;
        private long iscert;


        public MyWebClient(String cardMobile, long order, String mSendAmount, long iscert) {
            this.cardMobile = cardMobile;
            this.order = order;
            this.mSendAmount = mSendAmount;
            this.iscert = iscert;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //view.addJavascriptInterface(new WebJsBridge(mContext, new Handler(), null), "WebViewJavascriptBridge");
            if (url.contains("dpajsbridge://")) {
               new WebJsBridge(mContext).showJsBridgeDialog(url);
               return true;
            }

            return super.shouldOverrideUrlLoading(view, url);
        }

        @JavascriptInterface
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (progressWebLisneter != null && view.getTitle() != null) {
                    progressWebLisneter.callBackHtmlTitle(view.getTitle());
            }
            //view.loadUrl("javascript:window.bridgeInit()");
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }


    public void setProgressWebLisneter(ProgressWebLisneter lisneter) {
        this.progressWebLisneter = lisneter;
    }

    public interface ProgressWebLisneter {
        void callBackHtmlTitle(String title);
    }


}
