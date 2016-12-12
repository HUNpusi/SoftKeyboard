package com.example.android.softkeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class KeyMapPreference extends EditTextPreference {

    private EditText mEditText2;

    public KeyMapPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mEditText2 = new EditText(context, attrs);

        // Give it an ID so it can be saved/restored
        mEditText2.setId(99989);

        /*
         * The preference framework and view framework both have an 'enabled'
         * attribute. Most likely, the 'enabled' specified in this XML is for
         * the preference framework, but it was also given to the view framework.
         * We reset the enabled state.
         */
        mEditText2.setEnabled(true);
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
        //Toast.makeText(this.getContext(), "áááááááááááááááááááááÁÁÁÁÁÁÁÁ", Toast.LENGTH_SHORT).show();

        //EditText editText2 = mEditText2;
        EditText editText2 = new EditText(this.getContext());
        editText2.setText("anyááád");
        editText2.setLabelFor(99989);

        ViewParent oldParent = editText2.getParent();
        if (oldParent != view) {
            if (oldParent != null) {
                ((ViewGroup) oldParent).removeView(editText2);
            }
            onAddEditTextToDialogView(view, editText2);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        if (positiveResult) {
            // save shared preferences
            SharedPreferences.Editor editor = this.getContext().getSharedPreferences("ime_preferences", Context.MODE_PRIVATE).edit();
            Set<String> values = new HashSet<String>();
//            values.add(this.getKey());
            values.add(this.getText());
//            values.add(this.getKey());
//            values.add(this.getKey());
//            values.add(this.getKey());

            editor.putString(this.getKey(), this.getText());

            editor.commit();
            //Toast.makeText(getContext(), "LOG: "+this.getText(), Toast.LENGTH_SHORT).show();

        }
    }
    private void init(Context context) {
        setPersistent(false);
        setDialogLayoutResource(R.layout.input);
    }
}

