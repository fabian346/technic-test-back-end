package com.technicTestBackEnd.api.model.responses.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;

    public ProductResponse( String name, BigDecimal bigDecimal, String description) {
    }

    public ProductResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
