package log_processor.pre_processor;

import log_processor.entities.ProcessedThreadEntity;
import log_processor.entities.RequestThreadEntity;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PreProcessor {
    static String OUTPUT_DIR = "/Users/jishantsingh/playground/sandbox/src/log_processor/output_logs/";
    static DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");

    public static List<RequestThreadEntity> processLogFile(String path) {
        List<RequestThreadEntity> timeAxis = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(path);
            Scanner sc = new Scanner(inputStream);
            String tid = "dummy";
            while (sc.hasNextLine()) {
                String log = sc.nextLine();
                String[] logSplit = log.split(" ");
                int pid = 0;
                Path outPath = Paths.get(OUTPUT_DIR + tid + ".log");
                try {
                    String[] threadInfo = logSplit[0].split(":");
                    pid = Integer.parseInt(threadInfo[0]);
                    tid = threadInfo[1];
                    String timeString = logSplit[1] + " " + logSplit[2];
                    String message = "";
                    for (int i = 4; i < logSplit.length; i++) {
                        message = message + " " + logSplit[i];
                    }
                    message = message.trim();
                    if (message.trim().equals("**START**")) {
                        LocalDateTime ts = LocalDateTime.parse(timeString, dateTimeFormatter);
                        RequestThreadEntity start = new RequestThreadEntity(tid, pid, true, ts);
                        timeAxis.add(start);
                    } else if (message.trim().equals("**END**")) {
                        LocalDateTime ts = LocalDateTime.parse(timeString.trim(), dateTimeFormatter);
                        RequestThreadEntity end = new RequestThreadEntity(tid, pid, false, ts);
                        timeAxis.add(end);
                    }
                    outPath = Paths.get(OUTPUT_DIR + tid + ".log");
                    Files.write(outPath, (log + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (NumberFormatException e) {
                    Files.write(outPath, (log + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeAxis;
    }

    public static List<ProcessedThreadEntity> calculateState(List<RequestThreadEntity> timeAxis) {
        List<RequestThreadEntity> openThreads = new ArrayList<>();
        List<ProcessedThreadEntity> outputList = new ArrayList<>();
        for (RequestThreadEntity entity : timeAxis) {
            ProcessedThreadEntity processedThreadEntity = new ProcessedThreadEntity(entity, openThreads);
            openThreads = new ArrayList<>(processedThreadEntity.openThreads);
            outputList.add(processedThreadEntity);
        }
        return outputList;
    }

    public static List<ProcessedThreadEntity> preProcessLogs(String logDir, int numFiles) {
        List<RequestThreadEntity> timeAxis = new ArrayList<>();
        for (int i = 1; i <= numFiles; i++) {
            System.out.println(logDir + "/" + i + ".log");
            timeAxis.addAll(processLogFile(logDir + "/" + i + ".log"));
        }
        Collections.sort(timeAxis);
        System.out.println(timeAxis.size());
        return calculateState(timeAxis);
    }

}
