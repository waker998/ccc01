package com.example.myapplication01.models;

import android.widget.ImageView;
import com.example.myapplication01.R;  // 添加导入

public class Mole {
    private final int index;
    private final ImageView imageView;
    private boolean isVisible;

    public Mole(int index, ImageView imageView) {
        this.index = index;
        this.imageView = imageView;
        this.isVisible = false;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
        if (visible) {
            imageView.setImageResource(R.drawable.img_with_mole);  // 取消注释
        } else {
            imageView.setImageResource(R.drawable.img_without_mole);  // 取消注释
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public int getIndex() {
        return index;
    }

    public ImageView getImageView() {
        return imageView;
    }
}