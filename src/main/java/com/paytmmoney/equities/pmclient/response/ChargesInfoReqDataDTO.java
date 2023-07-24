
package com.paytmmoney.equities.pmclient.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargesInfoReqDataDTO {

    @JsonProperty("auto_sq_off")
    private Double autoSqOff;
    private List<ChargesInfoSectionsDTO> sections;
    private Double total;

}
