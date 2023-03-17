package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionChainDto{
    private OptionChainMetaDto meta;
    private OptionChainDataDto data;
}
