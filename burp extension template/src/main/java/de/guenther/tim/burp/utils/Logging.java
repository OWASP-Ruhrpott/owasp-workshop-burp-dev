/*
 * Copyright (C) 2016 Tim Guenther
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.guenther.tim.burp.utils;

import burp.BurpExtender;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The extension internal logging.
 * @author Tim Guenther
 * @version 1.0
 * 
 * <b>ATTENTION!</b><br>
 * Based on the internal architecture of Burp Suite, the first called class is
 * {@link burp.BurpExtender}, this class initialises the {@link java.io.PrintWriter}.
 * So <b>NEVER</b> call the Logging before this initialisation.
 */
public class Logging {
    
    private static PrintWriter stdout = null;
    private static PrintWriter stderr = null;
    
    /**
     * {@value #ERROR}
     */
    public static final int ERROR = 1;
    /**
     * {@value #INFO}
     */
    public static final int INFO = 2;
    /**
     * {@value #DEBUG}
     */
    public static final int DEBUG = 3;

    //Singleton Design Pattern.
    private Logging(){
        stdout = BurpExtender.getStdOut();
        stderr = BurpExtender.getStdErr();
    }
    
    //Create a only one instace.
    private static class SingletonHolder {
        private static final Logging INSTANCE = new Logging();
    }

    /**
     * Get the Instance of the Logger.
     * @return A Logging instance.
     */
    public static Logging getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    /**
     * Log a specific message on a logging level.
     * @param c The calling class.
     * @param message The message to log.
     * @param log_type The logging type. ERROR = {@value #ERROR}, INFO = 
     * {@value #INFO}, DEBUG = {@value #DEBUG}
     */
    public void log(Class c, String message, int log_type){
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        String time = df.format(dateobj);
        switch(log_type){
            case ERROR:
                stdout.println("[E] "+time+" - ["+c.getName()+"]:\t"+"Error, see Errors tab.");
                stderr.println("[E] "+time+" - ["+c.getName()+"]:\t"+message);
                break;
            case INFO:
                stdout.println("[I] "+time+" - ["+c.getName()+"]:\t"+message);
                break;
            case DEBUG:
                stdout.println("[D] "+time+" - ["+c.getName()+"]:\t"+message);
                break;
        }
    }
    
    /**
     * Log an error on level ERROR.
     * @param c The calling class.
     * @param e The thrown exception.
     */
    public void log(Class c, Exception e){
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date dateobj = new Date();
            String time = df.format(dateobj);
            StackTraceElement[] stacktrace = e.getStackTrace();
            String trace = e.toString()+"\n";
            for(StackTraceElement ste : stacktrace){
                trace += "\t"+ste.toString()+"\n";
            }
            stdout.println("[E] "+time+" - ["+c.getName()+"]:\t"+"Error, see Errors tab.");
            stderr.println("[E] "+time+" - ["+c.getName()+"]:\t"+trace);
    }    
}
