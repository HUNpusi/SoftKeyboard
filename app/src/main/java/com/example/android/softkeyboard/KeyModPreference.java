//package com.example.android.softkeyboard;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.DialogPreference;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class KeyModPreference extends DialogPreference {
//
//    private EditText mEditText;
//    private EditText mEditText2;
//
//    private String mText;
//    private String mText2;
//
//    public KeyModPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//
//        mEditText = new EditText(context, attrs);
//        mEditText2 = new EditText(context, attrs);
//
//        // Give it an ID so it can be saved/restored
//        mEditText.setId(View.generateViewId());
//        mEditText.setId(View.generateViewId());
//
//        /*
//         * The preference framework and view framework both have an 'enabled'
//         * attribute. Most likely, the 'enabled' specified in this XML is for
//         * the preference framework, but it was also given to the view framework.
//         * We reset the enabled state.
//         */
//        mEditText.setEnabled(true);
//        mEditText2.setEnabled(true);
//    }
//
//    public String getText() {
//        return mText;
//    }
//    public String getText2() {
//        return mText2;
//    }
//
//    @Override
//    protected void onBindDialogView(View view) {
//        super.onBindDialogView(view);
//
//        EditText editText = mEditText;
//        editText.setText(getText());
//
//        ViewParent oldParent = editText.getParent();
//        if (oldParent != view) {
//            if (oldParent != null) {
//                ((ViewGroup) oldParent).removeView(editText);
//            }
//            onAddEditTextToDialogView(view, editText);
//        }
//        EditText editText2 = mEditText;
//        editText.setText(getText2());
//
//        ViewParent oldParent2 = editText.getParent();
//        if (oldParent != view) {
//            if (oldParent != null) {
//                ((ViewGroup) oldParent2).removeView(editText2);
//            }
//            onAddEditTextToDialogView(view, editText2);
//        }
//
//    }
//
//    protected void onAddEditTextToDialogView(View dialogView, EditText editText) {
////        ViewGroup container = (ViewGroup) dialogView
////                .findViewById(com.android.internal.R.id.edittext_container);
////        if (container != null) {
////            container.addView(editText, ViewGroup.LayoutParams.MATCH_PARENT,
////                    ViewGroup.LayoutParams.WRAP_CONTENT);
////        }
//    }
//
////    @Override
////    protected void onDialogClosed(boolean positiveResult) {
////        super.onDialogClosed(positiveResult);
////        if (positiveResult) {
////            // save shared preferences
////            SharedPreferences.Editor editor = this.getContext().getSharedPreferences("ime_preferences", Context.MODE_PRIVATE).edit();
////            Set<String> values = new HashSet<String>();
//////            values.add(this.getKey());
////            values.add(this.getText());
//////            values.add(this.getKey());
//////            values.add(this.getKey());
//////            values.add(this.getKey());
////
////            editor.putString(this.getKey(), this.getText());
////
////            editor.commit();
////            Toast.makeText(getContext(), "LOG: "+this.getText(), Toast.LENGTH_SHORT).show();
////
////        }
////    }
//}
