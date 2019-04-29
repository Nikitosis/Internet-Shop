package com.shop.entities;

import javax.persistence.*;

@Entity
@Table(name="image")
public class Image {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="image",columnDefinition = "LONGBLOB")
    private byte[] image;

    public Image(){

    }

    public Image(byte[]image){
        this.image=image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
