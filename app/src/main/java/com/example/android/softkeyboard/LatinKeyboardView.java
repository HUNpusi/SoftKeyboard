/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.softkeyboard;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class LatinKeyboardView extends KeyboardView {

    static final int KEYCODE_OPTIONS = -100;
    // TODO: Move this into android.inputmethodservice.Keyboard
    static final int KEYCODE_LANGUAGE_SWITCH = -101;

    public LatinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LatinKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //ToDo: on longpress, run on possibilities on ticks.
    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        } else {
            return super.onLongPress(key);
        }
    }

    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final LatinKeyboard keyboard = (LatinKeyboard)getKeyboard();
        keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Resources r = super.getContext().getResources();

        Paint paint = new Paint();
        int mTextSize = r.getDimensionPixelSize(R.dimen.candidate_font_height);
        paint.setTextSize(mTextSize);
        paint.setColor(Color.GRAY);

        List<Key> keys = getKeyboard().getKeys();
        for(Key key: keys) {
            if (key.popupCharacters != null){
                canvas.drawText(key.popupCharacters.toString(), key.x + (key.width / 2) - key.popupCharacters.length()*(mTextSize / 4) , key.y + (key.height / 3), paint);
            }
        }
    }
}
