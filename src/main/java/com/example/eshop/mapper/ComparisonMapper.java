package com.example.eshop.mapper;

import com.example.eshop.dto.Comparison.CompareResponse;
import com.example.eshop.entities.Comparison;

import java.util.List;

public interface ComparisonMapper {
    List<CompareResponse> toDtoS(List<Comparison> all);

    CompareResponse toDto(Comparison comparison);
}
