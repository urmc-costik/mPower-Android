package co.touchlab.researchstack.core.ui.views;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import co.touchlab.researchstack.core.R;
import rx.functions.Action1;

public class SubmitBar extends LinearLayout
{
    private TextView submit;
    private TextView exit;

    public SubmitBar(Context context)
    {
        super(context);
        init();
    }

    public SubmitBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public SubmitBar(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        LayoutInflater.from(getContext()).inflate(R.layout.bar_submit, this, true);

        submit = (TextView) findViewById(R.id.bar_submit_postitive);
        exit = (TextView) findViewById(R.id.bar_submit_negative);
    }

    public void setSubmitAction(Action1 submit)
    {
        setSubmitAction(null, submit);
    }

    public void setSubmitAction(String title, Action1 submit)
    {
        if (!TextUtils.isEmpty(title))
        {
            this.submit.setText(title);
        }

        RxView.clicks(this.submit).subscribe(submit);
    }

    public void setExitAction(Action1 submit)
    {
        setExitAction(null, submit);
    }

    public void setExitAction(String title, Action1 exit)
    {
        if (!TextUtils.isEmpty(title))
        {
            this.exit.setText(title);
        }

        RxView.clicks(this.exit).subscribe(exit);
    }

}