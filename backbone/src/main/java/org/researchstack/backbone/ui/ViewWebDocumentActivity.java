package org.researchstack.backbone.ui;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.researchstack.backbone.R;
import org.researchstack.backbone.ui.views.LocalWebView;
import org.researchstack.backbone.utils.ResUtils;
import org.researchstack.backbone.utils.ThemeUtils;

/**
 * The ViewWebDocumentActivity is used for viewing both local and network HTML docs. This activity
 * has the ability to be themed by the calling activity. You are able to ignore this, and use the
 * default manifest theme by calling {@link #newIntent(Context, String, String)}.
 */
public class ViewWebDocumentActivity extends PinCodeActivity
{
    public static final String TAG          = ViewWebDocumentActivity.class.getSimpleName();
    public static final String KEY_DOC_NAME = TAG + ".DOC_NAME";
    public static final String KEY_TITLE    = TAG + ".TITLE";
    public static final String KEY_THEME    = TAG + ".THEME";

    public static Intent newIntent(Context context, String title, String docName)
    {
        return newIntent(context, title, docName, true);
    }

    public static Intent newIntent(Context context, String title, String docName, boolean useCallingTheme)
    {
        Intent intent = new Intent(context, ViewWebDocumentActivity.class);
        intent.putExtra(KEY_DOC_NAME, docName);
        intent.putExtra(KEY_TITLE, title);
        if (useCallingTheme)
        {
            int theme = ThemeUtils.getTheme(context);
            if(theme != 0)
            {
                intent.putExtra(KEY_THEME, ThemeUtils.getTheme(context));
            }
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if(getIntent() != null && getIntent().hasExtra(KEY_THEME))
        {
            setTheme(getIntent().getIntExtra(KEY_THEME, 0));
        }
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_web_document);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra(KEY_TITLE))
        {
            String title = getIntent().getStringExtra(KEY_TITLE);
            actionBar.setTitle(title);
        }

        LocalWebView webView = (LocalWebView) findViewById(R.id.webview);
        String documentName = getIntent().getStringExtra(KEY_DOC_NAME);
        String path = ResUtils.getHTMLFilePath(documentName);
        webView.loadUrl(path);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
