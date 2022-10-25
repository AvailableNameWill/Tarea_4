package com.example.tarea_4.clases;

import android.graphics.Bitmap;

public class foto {
    private Integer id;
    private Bitmap img;
    private String name;
    private String description;

    public foto(){}

    public foto(Integer _id, Bitmap _img, String _name, String _description){
        id = _id;
        img = _img;
        name = _name;
        description = _description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
