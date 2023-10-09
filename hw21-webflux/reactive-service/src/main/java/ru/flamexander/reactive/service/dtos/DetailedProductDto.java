package ru.flamexander.reactive.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailedProductDto implements Comparable<DetailedProductDto> {
    private Long id;
    private String name;
    private String description;

    @Override
    public int compareTo(DetailedProductDto o) {
        return this.id.compareTo(o.id);
    }
}
