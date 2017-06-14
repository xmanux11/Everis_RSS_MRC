package com.example.manumadrid.everis_rss;

/**
 * Created by ManuMadrid on 14/06/2017.
 */

/**
 * Clase Noticia
 */

public class New {
    /**
     * Titulo de la noticia
     */
    public String title;
    /**
     * Descripcion de la noticia
     */
    public String description;
    /**
     * url de la imagen de la noticia
     */
    public String imageUrl;
    /**
     * Url de la noticia
     */
    public String urlNew;

    public New(String title, String description, String imageUrl, String urlNew) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.urlNew = urlNew;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrlNew() {
        return urlNew;
    }

    public void setUrlNew(String urlNew) {
        this.urlNew = urlNew;
    }
}
