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
package burp;

import de.guenther.tim.burp.utils.Logging;
import de.tim.guenther.burp.owasp.ModifyHTTPRequest;
import java.io.PrintWriter;


/**
 * The first class called by Burp Suite.
 * This is the starting class for all other functionalities.
 * @author Tim Guenther
 * @version 1.0
 */

public class BurpExtender implements IBurpExtender{
    /**
     * {@value #EXTENSION_NAME}
     */
    public static final String EXTENSION_NAME = "OWASP Workshop Ruhrpott";
    
    private static PrintWriter stdout;
    private static PrintWriter stderr;
    
    /**
     * Register all new functions like for the internals and GUI.
     * Registered are Editors, a Tab and a HttpListner
     * @param callbacks {@link burp.IBurpExtenderCallbacks}
     */
    
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {

        //set extension name
        callbacks.setExtensionName(EXTENSION_NAME);
        
        //obtain streams
        stdout = new PrintWriter(callbacks.getStdout(), true);
        stderr = new PrintWriter(callbacks.getStderr(), true);
        
        //Just basic logging for burp. See burp.utils.Logging for more info.
        Logging.getInstance().log(getClass(), "Logging activated", Logging.INFO);
        
        //register the http listener for burp
        callbacks.registerHttpListener(new ModifyHTTPRequest(callbacks));
        
        Logging.getInstance().log(getClass(), "HTTPListener activated", Logging.INFO);
    }
    
    
    /**
     * Get a {@link java.io.PrintWriter} to the standard output of Burp.
     * @return The standard output
     */
    public static PrintWriter getStdOut(){
        return stdout;
    }
    
    /**
     * Get a {@link java.io.PrintWriter} to the standard error output of Burp.
     * @return The standard error output
     */    
    public static PrintWriter getStdErr(){
        return stderr;
    }
 
}
