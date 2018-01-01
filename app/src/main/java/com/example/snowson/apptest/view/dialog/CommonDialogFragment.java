package com.example.snowson.apptest.view.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * author: snowson
 * created on: 17-12-24 上午12:57
 * description: 自定义DialogFragment实现Dialog
 * 易忽略点: 1.若需要旋转屏幕时保存dialog中editText中的内容，需要给对应的EditText标记ID
 * 2.目前调查结果：dialog无法使用属性动画
 */

public class CommonDialogFragment extends AppCompatDialogFragment {

    private static DialogParams mParams;

    public static CommonDialogFragment getInstance(DialogParams params) {
        CommonDialogFragment dialogFragment = new CommonDialogFragment();
        mParams = params;
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int theme = 0;
        setStyle(mParams.getStyle(), theme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setWindowAnimations(mParams.getAnimation());
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = Gravity.CENTER;
            window.setAttributes(attributes);
        }
        View rootView = inflater.inflate(mParams.getResId(), container, false);
        ViewGroup.LayoutParams params = rootView.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(mParams.getWidth(), mParams.getHeight());
        } else {
            params.width = mParams.getWidth();
            params.height = mParams.getHeight();
        }
        rootView.setLayoutParams(params);
        return rootView;
    }

    static class DialogParams {
        private int width;
        private int height;
        private int resId;
        private View contentView;
        private int style;
        private int animEnter;
        private int animExit;
        private int animation;

        public int getAnimation() {
            return animation;
        }

        public void setAnimation(int animation) {
            this.animation = animation;
        }

        public int getAnimEnter() {
            return animEnter;
        }

        public void setAnimEnter(int animEnter) {
            this.animEnter = animEnter;
        }

        public int getAnimExit() {
            return animExit;
        }

        public void setAnimExit(int animExit) {
            this.animExit = animExit;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

        public View getContentView() {
            return contentView;
        }

        public void setContentView(View contentView) {
            this.contentView = contentView;
        }

        public int getStyle() {
            return style;
        }

        public void setStyle(int style) {
            this.style = style;
        }
    }

    public static class Builder {
        private DialogParams mParams;

        public Builder() {
            mParams = new DialogParams();
        }

        public CommonDialogFragment build() {
            CommonDialogFragment cdf = getInstance(mParams);
            return cdf;
        }

        public Builder setWidth(int width) {
            mParams.setWidth(width);
            return this;
        }

        public Builder setHeight(int height) {
            mParams.setHeight(height);
            return this;
        }

        public Builder setContentView(int resId) {
            mParams.setResId(resId);
            return this;
        }

        public Builder setContentView(View contentView) {
            mParams.setContentView(contentView);
            return this;
        }

        public Builder setStyle(int style) {
            mParams.setStyle(style);
            return this;
        }

        public Builder setEnterAnim(int enterAnimResId) {
            mParams.setAnimEnter(enterAnimResId);
            return this;
        }

        public Builder setExitAnim(int exitAnimResId) {
            mParams.setAnimExit(exitAnimResId);
            return this;
        }

        public Builder setAnimation(int animResId) {
            mParams.setAnimation(animResId);
            return this;
        }
    }
}
