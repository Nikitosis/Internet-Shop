package com.shop.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name="commodity")
public class Commodity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name="creation_date")
    private Date creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Image> images=new ArrayList<Image>();

    @OneToMany(mappedBy = "commodity",orphanRemoval = true,cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("date")
    private Set<Comment> comments=new HashSet<Comment>();

    @ManyToMany(mappedBy="commodities", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Tag> tags=new HashSet<Tag>();

    public Commodity(String name, double price, Date creationDate,Set<Comment> comments) {
        this.name = name;
        this.price = price;
        setCreationDate(creationDate);
        this.comments=comments;
    }

    public Commodity(){

    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setCommodity(this);
    }

    public void addImage(Image image){
        images.add(image);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }
//set time to 12AM to get rid of timezone conversions
// (in views date time could be shifted by several hours and it caused increasing/decreasing
// the whole days)
    public void setCreationDate(Date creationDate) {
        final long hours12 = 12L * 60L * 60L * 1000L;
        this.creationDate=creationDate;
        this.creationDate.setTime(this.creationDate.getTime()+hours12); //set time at 12 AM
                                                                        //to get rid of timezone conversions
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
        tag.getCommodities().add(this);
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
