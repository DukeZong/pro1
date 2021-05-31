package com.zkq.mygallery.entity;

public class Painting {
    private Integer id;
    private String pname;
    private Integer category;
    private Integer price;//价格
    private String preview;//油画图片地址
    private  String description;//描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", preview='" + preview + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
