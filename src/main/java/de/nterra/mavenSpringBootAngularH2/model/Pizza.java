package de.nterra.mavenSpringBootAngularH2.model;

import javax.persistence.*;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private long price;

    @Column(name = "published")
    private boolean published;

    public Pizza() {

    }

    public Pizza(String title, String description, long price, boolean published) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.published = published;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() { return price; }

    public void setPrice(long price) { this.price = price; }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ",  price=" + price + ", published=" + published + "]";
    }

}