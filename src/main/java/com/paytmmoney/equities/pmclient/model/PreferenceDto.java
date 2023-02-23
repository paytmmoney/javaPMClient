package com.paytmmoney.equities.pmclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PreferenceDto {

    private String actionType;
    private String modeType;
    private String scripType;
    private String exchangeType;
    private String scripId;

}
