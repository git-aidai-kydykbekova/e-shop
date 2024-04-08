package com.example.eshop.mapper;

import com.amazonaws.jmespath.ObjectMapperSingleton;
import com.example.eshop.dto.Comparison.CompareRequest;
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
    //Comparison dtoToModelId(Long id);
    List<CompareResponse> modelToDtoS(List<Comparison> comparison);

 //   CompareResponse toDto(Comparison comparison);
}
