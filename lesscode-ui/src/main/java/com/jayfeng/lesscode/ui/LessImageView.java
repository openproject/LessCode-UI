package com.jayfeng.lesscode.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jayfeng.lesscode.core.DrawableLess;

public class LessImageView extends ImageView implements ILessTint {

    private ColorStateList mTintColorStateList;
    private int mTintType = LESS_TINT_TYPE_OVERRIDE;

    public LessImageView(Context context) {
        this(context, null);
    }

    public LessImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LessImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LessTint, defStyleAttr, 0);

        mTintColorStateList = a.getColorStateList(R.styleable.LessTint_less_tint);
        mTintType = a.getInt(R.styleable.LessTint_less_tint_type, LESS_TINT_TYPE_OVERRIDE);
        a.recycle();

        lessTint();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if (drawable != null) {
            drawable = lessTintDrawable(drawable);
        }
        super.setImageDrawable(drawable);

        if (getBackground() == null) {
            // set background state List，or the tint above may be not work
            setBackgroundResource(R.color.less_tint_invalidate_bg);
        }
    }

    @Override
    public void setImageResource(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        setImageDrawable(drawable);
    }

    private void lessTint() {
        if (mTintColorStateList == null) {
            if (mTintType == LESS_TINT_TYPE_OVERLAY) {
                mTintColorStateList = getResources().getColorStateList(R.color.less_tint_overlay_color);
            } else {
                mTintColorStateList = getResources().getColorStateList(R.color.less_tint_override_color);
            }
        }

        if (mTintColorStateList != null) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                drawable = lessTintDrawable(drawable);
                setImageDrawable(drawable);
            }
        }
    }

    private Drawable lessTintDrawable(Drawable drawable) {
        Drawable tintDrawable;
        if (mTintType == LESS_TINT_TYPE_OVERRIDE) {
            tintDrawable = DrawableLess.$tint(drawable, mTintColorStateList);
        } else {
            tintDrawable = DrawableLess.$tint(drawable, mTintColorStateList, PorterDuff.Mode.MULTIPLY);
        }
        return tintDrawable;
    }
}
