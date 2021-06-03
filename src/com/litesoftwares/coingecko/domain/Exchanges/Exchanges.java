package com.litesoftwares.coingecko.domain.Exchanges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exchanges {
    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;
    @JsonProperty("year_established")
    long yearEstablished;
    @JsonProperty("country")
    String country;
    @JsonProperty("description")
    Object description;
    @JsonProperty("url")
    String url;
    @JsonProperty("image")
    String image;
    @JsonProperty("has_trading_incentive")
    boolean hasTradingIncentive;
    @JsonProperty("trade_volume_24h_btc")
    double tradeVolume24hBtc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTradeVolume24hBtc() {
        return tradeVolume24hBtc;
    }

    public void setTradeVolume24hBtc(double tradeVolume24hBtc) {
        this.tradeVolume24hBtc = tradeVolume24hBtc;
    }

    public long getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(long yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setHasTradingIncentive(boolean hasTradingIncentive) {
        this.hasTradingIncentive = hasTradingIncentive;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isHasTradingIncentive() {
        return hasTradingIncentive;
    }
}