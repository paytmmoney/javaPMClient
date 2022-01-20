
package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EdisDataStatusResDto {

    @JsonProperty("edis_request_id")
    private String edisRequestId;
    @JsonProperty("trading_date")
    private String tradingDate;
    @JsonProperty("isin_list")
    private List<EdisIsinResDto> isinList;
}
