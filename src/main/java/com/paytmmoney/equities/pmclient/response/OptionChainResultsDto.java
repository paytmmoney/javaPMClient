package com.paytmmoney.equities.pmclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionChainResultsDto {
    public long pml_id;
    public String exchange;
    public int underlaying_scrip_code;
    public String segment;
    public int security_id;
    public String pml_symbol;
    public String vega;
    public String fresh_pos;
    public String iv;
    public String square_off_pos;
    public String desc;
    public String theta;
    public String gamma;
    public String spot_price;
    public String delta;
    public String price;
    public double stk_price;
    public String net_chg;
    public String oi;
    public String oi_per_chg;
    public String oi_net_chg;
    public String per_chg;
    public String traded_vol;
    public String symbol;
    public Date expiry_date;
    public String option_type;
    public String instrument;
    public String name;
    public int tick_size;
    public int lot_size;
    public Date exch_feed_time;
}