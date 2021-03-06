
package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EdisParamResDto {

    @JsonProperty("DPId")
    private String dPId;
    @JsonProperty("ReqId")
    private String reqId;
    @JsonProperty("TransDtls")
    private String transDtls;
    @JsonProperty("Version")
    private String version;
}
