package fun.zestzone.zzcheckbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class ZzCheckBox extends AppCompatButton implements View.OnClickListener {

    private final Drawable checkedDrawable;
    private final Drawable unCheckedDrawable;
    private final int checkedTextColor;
    private final int unCheckedTextColor;
    private final int unAbleTextColor;

    private boolean isChecked = false;

    public ZzCheckBox(Context context) {
        this(context, null);
    }

    public ZzCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZzCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.ZzCheckBox, defStyleAttr, defStyleAttr
        );
        checkedDrawable = typedArray.getDrawable(R.styleable.ZzCheckBox_checked_drawable);
        unCheckedDrawable = typedArray.getDrawable(R.styleable.ZzCheckBox_unchecked_drawable);
        checkedTextColor = typedArray.getColor(R.styleable.ZzCheckBox_checked_text_color, 0);
        unCheckedTextColor = typedArray.getColor(R.styleable.ZzCheckBox_unable_text_color, 0);
        unAbleTextColor = typedArray.getColor(R.styleable.ZzCheckBox_unable_text_color, 0);
        isChecked = typedArray.getBoolean(R.styleable.ZzCheckBox_checked, false);
        typedArray.recycle();
        setCheckedBg(isChecked);
        setOnClickListener(this);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        if (isChecked != this.isChecked) {
            this.isChecked = isChecked;
            setCheckedBg(isChecked);
            setCheckedText(isChecked);
        }
    }

    public interface OnCheckChangedListener {
        void onCheckChanged(View v, boolean isChecked);
    }

    private OnCheckChangedListener mOnCheckChangedListener;

    public void setOnCheckChangedListener(OnCheckChangedListener listener) {
        mOnCheckChangedListener = listener;
    }

    private void setCheckedBg(boolean isChecked) {
        if (isChecked) {
            if (checkedDrawable != null) {
                setBackground(checkedDrawable);
            }
        } else {
            if (unCheckedDrawable != null) {
                setBackground(unCheckedDrawable);
            }
        }
    }

    private void setCheckedText(boolean isChecked) {
        if (isChecked) {
            if (checkedTextColor != 0) {
                setTextColor(checkedTextColor);
            } else {
                setTextColor(Color.BLACK);
            }
        } else {
            if (unCheckedTextColor != 0) {
                setTextColor(unCheckedTextColor);
            } else {
                setTextColor(Color.BLACK);
            }
        }
    }

    @Override
    public void onClick(View view) {
        isChecked = !isChecked;
        setCheckedBg(isChecked);
        setCheckedText(isChecked);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (unAbleTextColor != 0) {
            if (enabled) {
                setCheckedText(isChecked);
            } else {
                setTextColor(unAbleTextColor);
            }
        }
    }
}
