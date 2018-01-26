import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@Builder
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coin {
    private int id;
    private String tag;
    private String algorithm;
    private String block_time;
    private double block_reward;
    private double block_reward24;
    private int last_block;
    private double difficulty;
    private double difficulty24;
    private long nethash;
    private double exchange_rate;
    private double exchange_rate24;
    private double exchange_rate_vol;
    private String exchange_rate_curr;
    private String market_cap;
    private String estimated_rewards;
    private String estimated_rewards24;
    private String btc_revenue;
    private String btc_revenue24;
    private int profitability;
    private int profitability24;
    private boolean lagging;
    private long timestamp;
}