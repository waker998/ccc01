package com.example.myapplication01.models;

import android.widget.ImageView;

public class Mole {
    private int index;
    private ImageView imageView;
    private boolean isVisible;

    public Mole(int index, ImageView imageView) {
        this.index = index;
        this.imageView = imageView;
        this.isVisible = false;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
        if (visible) {
            imageView.setImageResource(R.drawable.mole_image);
        } else {
            imageView.setImageResource(R.drawable.img_without_mole);
        }
    }

    public boolean isVisible() { return isVisible; }
    public int getIndex() { return index; }
    public ImageView getImageView() { return imageView; }
}
