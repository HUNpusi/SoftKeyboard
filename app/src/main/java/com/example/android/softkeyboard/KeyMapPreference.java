package com.example.android.softkeyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;
/**
 * A {@link Preference} that allows for string
 * input.
 * <p>
 * It is a subclass of {@link DialogPreference} and shows the {@link EditText}
 * in a dialog. This {@link EditText} can be modified either programmatically
 * via {@link #getEditText()}, or through XML by setting any EditText
 * attributes on the EditTextPreference.
 * <p>
 * This preference will store a string into the SharedPreferences.
 * <p>
 * See {@link android.R.styleable#EditText EditText Attributes}.
 */
public class KeyMapPreference extends DialogPreference {
    /**
     * The edit text shown in the dialog.
     */
    private EditText mEditText;

    private String mText;
    private EditText mEditText2;


    public KeyMapPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        mEditText = new EditText(context, attrs);

        // Give it an ID so it can be saved/restored
        mEditText.setId(9888);

        Toast.makeText(this.getContext(), "áááááááááááááááááááááÁÁÁÁÁÁÁÁ", Toast.LENGTH_SHORT).show();

        /*
         * The preference framework and view framework both have an 'enabled'
         * attribute. Most likely, the 'enabled' specified in this XML is for
         * the preference framework, but it was also given to the view framework.
         * We reset the enabled state.
         */
        mEditText.setEnabled(true);

        mEditText2 = new EditText(context, attrs);

        // Give it an ID so it can be saved/restored
        mEditText2.setId(99989);
    }

    public KeyMapPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        init(context);
    }

    public KeyMapPreference(Context context, AttributeSet attrs) {
//        this(context, attrs, com.android.internal.R.attr.editTextPreferenceStyle);
        this(context, attrs, 0);
        init(context);
    }

    public KeyMapPreference(Context context) {
        this(context, null);
        init(context);
    }

    /**
     * Saves the text to the {@link SharedPreferences}.
     *
     * @param text The text to save
     */
    public void setText(String text) {
        final boolean wasBlocking = shouldDisableDependents();

        mText = text;

        persistString(text);

        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }
    }

    /**
     * Gets the text from the {@link SharedPreferences}.
     *
     * @return The current preference value.
     */
    public String getText() {
        return mText;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        //Toast.makeText(this.getContext(), "áááááááááááááááááááááÁÁÁÁÁÁÁÁ", Toast.LENGTH_SHORT).show();

        EditText editText = mEditText;
        editText.setText(getText());

        ViewParent oldParent = editText.getParent();
        if (oldParent != view) {
            if (oldParent != null) {
                ((ViewGroup) oldParent).removeView(editText);
            }
            onAddEditTextToDialogView(view, editText);
        }

        // load shared preferences
        //Toast.makeText(this.getContext(), "áááááááááááááááááááááÁÁÁÁÁÁÁÁ", Toast.LENGTH_SHORT).show();

        //EditText editText2 = mEditText2;
        EditText editText2 = new EditText(this.getContext());
        editText2.setText("anyááád");
        editText2.setLabelFor(99989);

        SharedPreferences sharedPref = getContext().getSharedPreferences("ime_preferences",Context.MODE_PRIVATE);
        editText2.setText(sharedPref.getString(getEditText().getText().toString(),""));

        oldParent = editText2.getParent();
        if (oldParent != view) {
            if (oldParent != null) {
                ((ViewGroup) oldParent).removeView(editText2);
            }
            onAddEditTextToDialogView(view, editText2);
        }
    }

    /**
     * Adds the EditText widget of this preference to the dialog's view.
     *
     * @param dialogView The dialog view.
     */
    protected void onAddEditTextToDialogView(View dialogView, EditText editText) {
        ViewGroup container = (ViewGroup) dialogView
//                .findViewById(com.android.internal.R.id.edittext_container);
                .findViewById(R.id.edittext_container);
        //Toast.makeText(this.getContext(), "áááááááááááááááááááááÁÁÁÁÁÁÁÁ", Toast.LENGTH_SHORT).show();
        if (container != null) {
            container.addView(editText, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            String value = mEditText.getText().toString();
            if (callChangeListener(value)) {
                setText(value);
            }
        }
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
        setDialogLayoutResource(R.layout.preference_dialog_edittext_material);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        setText(restoreValue ? getPersistedString(mText) : (String) defaultValue);
    }

    @Override
    public boolean shouldDisableDependents() {
        return TextUtils.isEmpty(mText) || super.shouldDisableDependents();
    }

    /**
     * Returns the {@link EditText} widget that will be shown in the dialog.
     *
     * @return The {@link EditText} widget that will be shown in the dialog.
     */
    public EditText getEditText() {
        return mEditText;
    }



    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            // No need to save instance state since it's persistent
            return superState;
        }

        final SavedState myState = new SavedState(superState);
        myState.text = getText();
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state == null || !state.getClass().equals(SavedState.class)) {
            // Didn't save state for us in onSaveInstanceState
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());
        setText(myState.text);
    }

    private static class SavedState extends BaseSavedState {
        String text;

        public SavedState(Parcel source) {
            super(source);
            text = source.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(text);
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

}
