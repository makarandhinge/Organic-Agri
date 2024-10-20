package com.scm.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredictionResponse {
    private String title;
    private String description;
    private String prevent;
    private String image_url;
    private String sname;
    private String simage;
    private String buy_link;

    public String getImageUrl() {
        return image_url;
    }
    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public String getBuyLink() {
        return buy_link;
    }
    public void setBuyLink(String buy_link) {
        this.buy_link = buy_link;
    }
}