package com.inerun.dropinsta.fontlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.inerun.dropinsta.R;


public class CustomFontAutoCompleteTextview extends AutoCompleteTextView {

	public CustomFontAutoCompleteTextview(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	public CustomFontAutoCompleteTextview(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);

	}

	public CustomFontAutoCompleteTextview(Context context) {
		super(context);
		init(null);
	}

	private void init(AttributeSet attrs) {
		if (!isInEditMode()) {
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,
					R.styleable.CustomFontAutoCompleteTextView);
			String fontName = a
					.getString(R.styleable.CustomFontAutoCompleteTextView_autocompletetextfontName);
			if (fontName != null) {
				Typeface myTypeface = Typeface.createFromAsset(getContext()
						.getAssets(), "fonts/" + fontName);
				setTypeface(myTypeface);
			}
			a.recycle();
		}
	}}

}
