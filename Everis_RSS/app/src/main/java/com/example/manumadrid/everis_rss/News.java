package com.example.manumadrid.everis_rss;

import java.util.ArrayList;

/**
 * Created by ManuMadrid on 14/06/2017.
 */

/**
 * Clase encargada de aglutinar las noticias para su posterior uso po el adaptador
 */
public class News {
    /**
     * Conjunto de titulos de noticias
     */
    public ArrayList<String> titles = new ArrayList<>();
    /**
     * Conjunto de descripciones de noticias
     */
    public ArrayList<String> descriptions = new ArrayList<>();
    /**
     * Conjunto de url de imagenes de noticias
     */
    public ArrayList<String> images = new ArrayList<>();
    /**
     * Conjunto de url de noticias
     */
    public ArrayList<String> urls = new ArrayList<>();

    public News(ArrayList<String> titles, ArrayList<String> descriptions, ArrayList<String> images, ArrayList<String> urls) {
        this.titles = titles;
        this.descriptions = descriptions;
        this.images = images;
        this.urls = urls;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(ArrayList<String> descriptions) {
        this.descriptions = descriptions;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }
}
