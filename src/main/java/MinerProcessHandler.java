import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MinerProcessHandler {

    private ProcessBuilder builder;
    private Process process;

    public MinerProcessHandler(String pathToMiner, String... arguments) {
        List<String> commands = Arrays.asList(arguments);
        commands.add(0, pathToMiner);
        builder = new ProcessBuilder(commands);
    }

    public void start() throws IOException {
        process = builder.start();
    }

    private boolean stop() {
        process.destroy();
        return process.isAlive();
    }

//    String line;
//        try {
//        BufferedReader STDOUT = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        BufferedReader STDERR = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//        while ((line = STDOUT.readLine()) != null) {
//            AllText = AllText + "\n" + line;
//            while ((line = STDERR.readLine()) != null) {
//                AllText = AllText + "\n" + line;
//            }
//        }
//        System.out.println("Response from command:" + AllText);
//    } catch (IOException ex) {
//        Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
//    }
}