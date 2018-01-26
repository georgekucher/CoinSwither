import jdk.nashorn.internal.runtime.logging.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static JsonParser parser = new JsonParser("http://whattomine.com/coins.json");
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("    HH:mm:ss");
    private static final MinerProcessHandler PROCESS_HANDLER = new MinerProcessHandler("");

    private static final Map<String, String> coinMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        coinMap.put("GBX", "");
        coinMap.put("CRC", "");

//        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\Yurii\\Downloads\\cc_miner\\ccminer.exe",
//                                               "-o",
//                                               "stratum+tcp://mine.sumo.fairpool.xyz:5555",
//                                               "-u",
//                                               "Sumoo5H59i819DcLT3DjZVToKneE2UE5xFSGCZkzwmdkdg1W8KPF4s8CmAiNzhmEKW9VLupxAvk56JZYp64wTKEgKufmGzsAeq9.6559996a53d9177398326506fec78ab9f8118dbb6385409584e60dc6b93db87b.Dell",
//                                               "-dbg",
//                                               "-1",
//                                               "-lowcpu",
//                                               "2")
        int counter = 0;
        Coin maxCoin = null;
        while (true) {
            parser.update();
            List<Coin> coins = parser
                    .getCoins()
                    .stream()
                    .filter(coin -> coin.getTag().equals("GBX") || coin.getTag().equals("CRC"))
                    .collect(Collectors.toList());
            Coin currentMaxCoin = getMostProfitableCoin(coins);
            if (PROCESS_HANDLER.getProcess() == null) {
                PROCESS_HANDLER.start(coinMap.get(currentMaxCoin.getTag()));
            }
            if (null != maxCoin) {
                if ((float) currentMaxCoin.getProfitability() / (float) maxCoin.getProfitability() > 1.1f) {
                    PROCESS_HANDLER.restart(coinMap.get(currentMaxCoin.getTag()));
                    counter ++;
                    System.out.println("Restarts count: " + DTF.format(LocalDateTime.now()));
                }
            }
            Thread.sleep(30000);
        }
    }

    private static Coin getMostProfitableCoin(List<Coin> coins) {
        final Comparator<Coin> comp = Comparator.comparingInt(Coin::getProfitability);
        return parser.getCoins().stream().max(comp).get();
    }

    private static int getCoinLastBlock(String tag) {
        return parser.getCoins().stream().filter(coin -> coin.getTag().equals(tag)).collect(Collectors.toList()).get
                (0).getLast_block();
    }
}