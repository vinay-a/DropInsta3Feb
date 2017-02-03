package com.inerun.dropinsta.fontlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.inerun.dropinsta.R;


public class CustomFontButton extends Button {

	public CustomFontButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	public CustomFontButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);

	}

	public CustomFontButton(Context context) {
		super(context);
		init(null);
	}

	private void init(AttributeSet attrs) {
		if (!isInEditMode()) {
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,
					R.styleable.CustomFontButtonView);
			String fontName = a
					.getString(R.styleable.CustomFontButtonView_buttonfontName);
			if (fontName != null) {
				Typeface myTypeface = Typeface.createFromAsset(getContext()
						.getAssets(), "fonts/" + fontName);
				setTypeface(myTypeface);
			}
			a.recycle();
		}
	}
	}

}
