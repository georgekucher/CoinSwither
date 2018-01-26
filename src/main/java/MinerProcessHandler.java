import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Getter
public class MinerProcessHandler {

    private ProcessBuilder builder;
    private String pathToMiner;
    private Process process;

    public MinerProcessHandler(String pathToMiner) {
        this.pathToMiner = pathToMiner;
    }

    public void start(String... arguments) throws IOException {
        List<String> commands = Arrays.asList(arguments);
        commands.add(0, pathToMiner);
        process = builder.start();
        builder = new ProcessBuilder(commands);
    }

    public void restart(String... arguments) throws IOException, InterruptedException {
        Process oldProcess = this.process;
        start(arguments);
        Thread.sleep(5000);
        oldProcess.destroy();
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