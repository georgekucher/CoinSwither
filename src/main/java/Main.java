import jdk.nashorn.internal.runtime.logging.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Collectors;

public class Main {

    private static JsonParser parser = new JsonParser("http://whattomine.com/coins.json");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("    HH:mm:ss");

    public static void main(String[] args) throws Exception {
//        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\Yurii\\Downloads\\cc_miner\\ccminer.exe",
//                                               "-o",
//                                               "stratum+tcp://mine.sumo.fairpool.xyz:5555",
//                                               "-u",
//                                               "Sumoo5H59i819DcLT3DjZVToKneE2UE5xFSGCZkzwmdkdg1W8KPF4s8CmAiNzhmEKW9VLupxAvk56JZYp64wTKEgKufmGzsAeq9.6559996a53d9177398326506fec78ab9f8118dbb6385409584e60dc6b93db87b.Dell",
//                                               "-dbg",
//                                               "-1",
//                                               "-lowcpu",
//                                               "2")
        while (true) {
            System.out.println("GBX - " + getCoinProfit("GBX") + dtf.format(LocalDateTime.now()));
//            System.out.println("CRC - " + getCoinProfit("CRC") + dtf.format(LocalDateTime.now()));
            Thread.sleep(5000);
        }
    }

    private static int getCoinProfit(String tag) {
        parser.update();
        return parser.getCoins().stream().filter(coin -> coin.getTag().equals(tag)).collect(Collectors.toList()).get(0).getProfitability();
    }
}