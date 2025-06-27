package com.isofttz.yeriko_backend.entities;

import lombok.Data;

@Data
public class TotalSummaryDTO {
    private String name;
    private int total;

    public TotalSummaryDTO(String name, int total) {
        this.name = name;
        this.total = total;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}