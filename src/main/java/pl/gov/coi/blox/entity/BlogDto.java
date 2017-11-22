package pl.gov.coi.blox.entity;

import pl.gov.coi.blox.model.BlogType;
import pl.gov.coi.blox.model.RateType;


public class BlogDto {

    private RateType rateType;
    private BlogType blogType;
    private String description;
    private boolean active;

    public boolean getActive() { return active; }

    public void setActive(boolean active) {
        this.active = active;
    }

    public RateType getRateType() { return rateType; }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
