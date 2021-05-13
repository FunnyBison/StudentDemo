package com.chenantao.autolayout;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by zhy on 15/11/19.
 */
public class AutoLayoutActivity extends FragmentActivity
{
	private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
	private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
	private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

	private static final String CARD_VIEW = "android.support.v7.widget.CardView";

	@Override
	public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);

	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs)
	{


		View view = null;
		if (name.equals(LAYOUT_FRAMELAYOUT))
		{
			view = new AutoFrameLayout(context, attrs);
		}
		if (name.equals(LAYOUT_LINEARLAYOUT))
		{
			view = new AutoLinearLayout(context, attrs);
		}
		if (name.equals(LAYOUT_RELATIVELAYOUT))
		{
			view = new AutoRelativeLayout(context, attrs);
		}
		if (name.equals(CARD_VIEW))
		{
			view = new AutoCardView(context, attrs);
		}
		if (view != null) return view;
		return super.onCreateView(name, context, attrs);
	}


}
