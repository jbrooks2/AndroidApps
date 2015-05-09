package com.detroitteatime.uidemo2;

/**
 * Created by mark on 4/24/15.
 */
public class RowModel {
    private String text;
    private float stars;


    RowModel(String text, float rating){
        this.text = text;
        this.stars = rating;

    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
