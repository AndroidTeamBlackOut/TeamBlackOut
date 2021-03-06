package com.blackout.paidupdater.Themes;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.blackout.paidupdater.R;

public class SwitchPreference extends android.preference.SwitchPreference {

    public SwitchPreference(Context context) {
        super(context);
    }

    public SwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        final TextView title = (TextView) view.findViewById(android.R.id.title);
        title.setTextColor(getContext().getResources().getColor(R.color.text));
        title.setEllipsize(null);
        title.setSingleLine(false);
        final TextView summary = (TextView) view.findViewById(android.R.id.summary);
        summary.setTextColor(getContext().getResources().getColor(R.color.secondary_text));
        summary.setEllipsize(null);
        summary.setSingleLine(false);
    }
}
