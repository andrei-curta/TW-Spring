package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * singleton class used for logging
 * @author Andrei
 *
 */

public class Logger {

    private static Logger logger = null;
    File logFile;

    public static synchronized Logger getInstance() {
        if(logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    /**
     * method that adds a log entry containing the date of the log
     *  and a log message that it receives as parameter
     * @param logMessage
     */

    public void addToLog(String logMessage) {
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormatDMYHM = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dateOfLog = dateFormatDMYHM.format(cal.getTime());

        try {
            FileWriter fw = new FileWriter(logFile, true);
            PrintWriter writer = new PrintWriter(fw, true);

            writer.println(dateOfLog + " - " + logMessage);
            writer.close();
        } catch (IOException e) {}

    }

    private Logger() {
        try{
            logFile = new File("E:\\Facultate\\Anul_III\\TW\\Proiecte\\Tema3\\logs.txt");
            if(!logFile.exists()){
                logFile.createNewFile();
            }
        }catch (Exception e) {
            System.out.println("could not create logger file: " +  e.getMessage());
        }
    }
}
