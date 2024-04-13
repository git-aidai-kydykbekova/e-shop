package com.example.eshop.mapper;

import com.example.eshop.dto.Comparison.CompareResponse;
import com.example.eshop.entities.Comparison;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ComparisonMapper {
    ComparisonMapper INSTANCE = Mappers.getMapper(ComparisonMapper.class);
    CompareResponse modelTODto(Comparison comparison);
    Comparison dtoToModel(CompareResponse compareResponse);
    List<CompareResponse> modelToDtoS(List<Comparison> comparison);


}
