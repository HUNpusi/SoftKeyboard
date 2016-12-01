package com.example.android.softkeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

public class KeyMapPreference extends EditTextPreference {

    public KeyMapPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public KeyMapPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KeyMapPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyMapPreference(Context context) {
        super(context);
    }

    @Override
    protected void onBindDialogView(View view) {
        // load shared preferences
        // init views
        super.onBindDialogView(view);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        if (positiveResult) {
            // save shared preferences
            SharedPreferences.Editor editor = this.getContext().getSharedPreferences("ime_preferences", Context.MODE_PRIVATE).edit();
            editor.putString("key_q", this.getText());
            editor.commit();
            Toast.makeText(getContext(), "L?OG: "+this.getText(), Toast.LENGTH_SHORT).show();

        }
    }
    private void init(Context context) {
        setPersistent(false);
        setDialogLayoutResource(R.layout.input);
    }
}
