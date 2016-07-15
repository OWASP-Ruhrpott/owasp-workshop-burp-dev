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
package de.tim.guenther.burp.owasp;

import burp.IBurpExtenderCallbacks;
import burp.IHttpListener;
import burp.IHttpRequestResponse;
import de.guenther.tim.burp.utils.Logging;

/**
 * Basic IHTTPListener for all incoming and outgoing HTTP messages.
 * @author Tim Guenther
 * @version 1.0
 */
public class ModifyHTTPRequest implements IHttpListener{
    
    private IBurpExtenderCallbacks callbacks;
    
    public ModifyHTTPRequest(IBurpExtenderCallbacks callbacks){
        this.callbacks = callbacks;
    }
    
    public void processHttpMessage(int toolFlag, boolean isRequest, IHttpRequestResponse ihrr) {
        Logging.getInstance().log(this.getClass(), "The listener is not implemented",Logging.INFO);
        /* TODO */
    }
}
