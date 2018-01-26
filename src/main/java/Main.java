import java.util.stream.Collectors;

public class Main {

    private static JsonParser parser = new JsonParser("http://whattomine.com/coins.json");

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


    }

    private int getCoinProfit(String tag) {
        parser.update();

//        return parser.getCoins().stream().filter(coin -> coin.getTag().equals(tag)).map(Collectors.toList());
        return 5;
    }
}