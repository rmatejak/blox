package pl.gov.coi.blox.entity;

import pl.gov.coi.blox.model.BlogType;
import pl.gov.coi.blox.model.RateType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class BlogDto {
    @NotNull
    @Min(value = 1,message = "Jest ok")
    private Long ownerId;
    @NotNull(message = "RateType cannot be NULL!")
    private RateType rateType;
    @NotNull(message = "BlogType cannot be NULL!")
    private BlogType blogType;
    @NotNull(message = "Please enter the text of range 0-300")
    @Size(min = 0, max = 300)
    private String description;
    @NotNull
    private boolean active;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isActive() {
        return active;
    }

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
