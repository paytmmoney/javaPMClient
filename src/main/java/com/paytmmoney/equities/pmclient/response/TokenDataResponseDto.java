package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.paytmmoney.equities.pmclient.constant.ApiConstants.ACCESS_TOKEN;
import static com.paytmmoney.equities.pmclient.constant.ApiConstants.API_KEY;
import static com.paytmmoney.equities.pmclient.constant.ApiConstants.CHANNEL_ID;
import static com.paytmmoney.equities.pmclient.constant.ApiConstants.MERCHANT_ID;
import static com.paytmmoney.equities.pmclient.constant.ApiConstants.PUBLIC_ACCESS_TOKEN;
import static com.paytmmoney.equities.pmclient.constant.ApiConstants.READ_ACCESS_TOKEN;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDataResponseDto {

    @JsonProperty(MERCHANT_ID)
    private String merchantId;

    @JsonProperty(CHANNEL_ID)
    private String channelId;

    @JsonProperty(API_KEY)
    private String apiKey;

    @JsonProperty(ACCESS_TOKEN)
    private String accessToken;

    @JsonProperty(PUBLIC_ACCESS_TOKEN)
    private String publicAccessToken;

    @JsonProperty(READ_ACCESS_TOKEN)
    private String readAccessToken;
}
