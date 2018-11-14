package com.appcomponent.webview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.appcomponent.base.BaseActivity;
import com.appcomponent.constant.ConstanPool;
import com.appcomponent.utils.StringUtil;
import com.appcomponent.utils.UrlConstans;
import com.appcomponent.widget.TopBar;
import com.safframework.log.L;
import com.wx.polymerization.appcomponent.R;

import java.net.URI;
import java.util.List;

import okhttp.Cookie.HttpCookieJar;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

import static android.webkit.WebSettings.LOAD_DEFAULT;

/**
 * Author: yangweichao
 * Date:   2018/11/14 2:35 PM
 * Description: 二次改造需求。
 */


public class WebActivity extends BaseActivity implements DownloadListener, TopBar.OnLeftLayoutListener,
        ProgressWebView.ProgressWebLisneter, TopBar.OnRightLayoutListener {

    private String url;
    private TopBar topbar;
    private boolean isNeedParam;
    private boolean mFocusClose;
    private boolean isNeedCookie;
    private ProgressWebView webView;
    private String title = "";
    private TextView tvTitle;
    private boolean isFinishStartMain;
    private int btnRightType;
    private String rightTitle;
    private boolean isShowRight;
    private String rightIntent;
    private Bundle rightBundle;
    private boolean isShowLeft; //返回,关闭


    @Override
    public void init() {
        Intent intent = getIntent();
        title = intent.getStringExtra(ConstanPool.WEB_TITLE);
        url = intent.getStringExtra(ConstanPool.WEB_URL);
        isNeedCookie = intent.getBooleanExtra(ConstanPool.WEB_IS_COOKIE, false);
        isNeedParam = intent.getBooleanExtra(ConstanPool.WEB_IS_PARAMS, false);
        mFocusClose = intent.getBooleanExtra(ConstanPool.WEB_IS_COLSE, true);
        isFinishStartMain = intent.getBooleanExtra(ConstanPool.WEB_FINISH_STARTMAIN, false);
        btnRightType = intent.getIntExtra(ConstanPool.WEB_BTN_RIGHT_CLICK_TYPE, ConstanPool.WEB_BTN_CLICK_TYPE_STARTACT);
        rightTitle = intent.getStringExtra(ConstanPool.WEB_BTN_RIGHT_TITLE);
        isShowRight = intent.getBooleanExtra(ConstanPool.WEB_BTN_RIGHT, false);
        isShowLeft = intent.getBooleanExtra(ConstanPool.WEB_BTN_LEFT, false);
        rightIntent = intent.getStringExtra(ConstanPool.WEB_BTN_RIGHT_CLASS_ACT);
        rightBundle = intent.getBundleExtra(ConstanPool.WEB_BTN_RIGHT_BUNDLE);
    }


    @Override
    public void initView() {
        setContentView(R.layout.activity_web);
        topbar = (TopBar) findViewById(R.id.webview_topbar);
        webView = (ProgressWebView) findViewById(R.id.webview);
        topbar.setonTopbarLeftLayoutListener(this);
        if (isShowRight) {
            topbar.getRightText().setVisibility(View.VISIBLE);
            topbar.getRightText().setText(rightTitle);
            topbar.setOnRightLayoutListener(this);
        }
        if (isShowLeft) {
            topbar.setTextValue("关闭", TopBar.LEFT);
        }
        topbar.setLineVisiable(View.GONE);
        tvTitle = (TextView) findViewById(R.id.text_title);
        initWebViewDefateSetting();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initValue() {

    }


    private void initWebViewDefateSetting() {
//        if (AppConfig.DEBUG) {
//            //测试模式开启
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                WebView.setWebContentsDebuggingEnabled(true);
//            }
//        }
        topbar.setTopbarTitle(title);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webView.getSettings().setAllowFileAccess(true);
        // 如果访问的页面中有Javascript，则webview必须设置支持Javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setCacheMode(LOAD_DEFAULT);//缓存模式
        webView.getSettings().setDefaultTextEncodingName("UTF-8");//设置字符编码
        webView.setDownloadListener(this);
        //webView.setData(cardMobile, order, mSendAmount, iscert);
        //支持缩放解决webview的适配屏幕问题
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //webView.addJavascriptInterface(new WebJsBridge(this, new Handler(), null), "WebViewJavascriptBridge");
        //cookie
        if (StringUtil.isNotEmpty(url)) {
            if (isNeedCookie) {
                syncCookie(this, url);
            }
            if (isNeedParam) {
//                ParamsGet get = new ParamsGet();
//                if (url.contains("?")) {
//                    url = url + "&" + get.toString();
//                } else {
//                    url = url + "?" + get.toString();
//                }
            }
        }
        webView.setProgressWebLisneter(this);
        webView.loadUrl(url);
        L.d("WebActivity's urlL:" + url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.removeAllViews();
        }
    }


    @Override
    public void finish() {
        if (isFinishStartMain) {
            // TODO: 2018/8/1 调整跳转方式
            //ARouter.getInstance().build(PathConfig.MAIN_ACTIVITY).navigation();
            //startActivity(new Intent(this, MainActivity.class));
        }
        super.finish();
    }

    public ProgressWebView getWebView() {
        return webView;
    }

    @Override
    public void callBackHtmlTitle(String title) {
        String titleTemp = StringUtil.formatString(6, title);
        topbar.setTopbarTitle(StringUtil.isEmpty(this.title) ? titleTemp : this.title);
        //这里每次都有回调，所以处理callbackImg的显示状态
//        if (webView.canGoBack()) {
//            img_close.setVisibility(View.VISIBLE);
//        }
    }



    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//        if (url != null && url.startsWith("http://")) {
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mFocusClose) {
            setResult(1008);
            finish();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK && !webView.canGoBack()) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * WebView同步Cookie
     */
    private void syncCookie(BaseActivity activity, String url) {
        CookieSyncManager.createInstance(activity);
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        else
            cookieManager.setAcceptCookie(true);

        cookieManager.removeSessionCookie();
        cookieManager.removeAllCookie();

        URI uri = URI.create(UrlConstans.BASE_URL);
        List<Cookie> list = HttpCookieJar.create().loadForRequest(HttpUrl.get(uri));
        if (list != null) {
            for (Cookie cookie : list) {
                cookieManager.setCookie(url, cookie.name() + "=" + cookie.value());
            }
        }
        CookieSyncManager.getInstance().sync();
        L.e(">>>>>>>>>>>>>>>>>>>>", cookieManager.getCookie(url));
    }

    @Override
    public void onLeftClick() {
        boolean canGoBack = webView.canGoBack();
        //   if (setBuyProductActivityForResultBackCode()) return;
        if (!mFocusClose && canGoBack) {
            webView.goBack();
        } else {
            setResult(1008);
            finish();
        }
    }

    @Override
    public void onRightClick() {
        switch (btnRightType) {
            case ConstanPool.WEB_BTN_CLICK_TYPE_STARTACT://启动activity
                Intent intent = new Intent();
                intent.setClassName(this, rightIntent);
                startActivity(intent);
                finish();
                break;
            case ConstanPool.WEB_BTN_CLICK_TYPE_FINISH://关闭当前页
                finish();
                break;
        }
    }


}
