package com.example.eshop.mapper.impl;

import com.example.eshop.dto.Comparison.CompareResponse;
import com.example.eshop.entities.Comparison;
import com.example.eshop.mapper.ComparisonMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ComparisonMapperImpl implements ComparisonMapper {

    @Override
    public List<CompareResponse> toDtoS(List<Comparison> all) {
        List<CompareResponse> compareResponses = new ArrayList<>();
        for(Comparison comparison : all) {
            compareResponses.add(toDto(comparison));
        }
        return compareResponses;
    }

    @Override
    public CompareResponse toDto(Comparison comparison) {
        CompareResponse compareResponse = new CompareResponse();
        compareResponse.setSales_package(comparison.getSales_package());
        compareResponse.setModel_number(comparison.getModel_number());
        compareResponse.setSecondary_material(comparison.getSecondary_material());
        compareResponse.setConfiguration(comparison.getConfiguration());
        compareResponse.setUpholstery_material(comparison.getUpholstery_material());
        compareResponse.setUpholstery_color(comparison.getUpholstery_color());

        compareResponse.setFilling_material(comparison.getFilling_material());
        compareResponse.setFinish_type(comparison.getFinish_type());
        compareResponse.setAdjustable_headrest(comparison.getAdjustable_headrest());
        compareResponse.setMax_load_capacity(comparison.getMax_load_capacity());
        compareResponse.setOrigin_of_manufacture(comparison.getOrigin_of_manufacture());

        compareResponse.setWidth(comparison.getWidth());
        compareResponse.setHeight(comparison.getHeight());
        compareResponse.setDepth(comparison.getDepth());
        compareResponse.setWeight(comparison.getWeight());
        compareResponse.setSeat_height(comparison.getSeat_height());
        compareResponse.setLed_height(comparison.getLed_height());

        compareResponse.setWarranty_summary(comparison.getWarranty_summary());
        compareResponse.setWarranty_service_type(comparison.getWarranty_service_type());
        compareResponse.setCovered_in_warranty(comparison.getCovered_in_warranty());
        compareResponse.setNot_covered_in_warranty(comparison.getNot_covered_in_warranty());
        compareResponse.setDomestic_warranty(comparison.getDomestic_warranty());

        return compareResponse;
    }
}
