package com.example.eshop.mapper;

import com.example.eshop.dto.Comparison.CompareResponse;
import com.example.eshop.entities.Comparison;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ComparisonMapper {
    ComparisonMapper INSTANCE = Mappers.getMapper(ComparisonMapper.class);
    CompareResponse modelTODto(Comparison comparison);

}
