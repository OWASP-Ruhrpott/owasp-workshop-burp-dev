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
import burp.IExtensionHelpers;
import burp.IHttpListener;
import burp.IHttpRequestResponse;
import burp.IParameter;
import burp.IRequestInfo;
import de.guenther.tim.burp.utils.Logging;
import java.util.List;

/**
 * Basic IHTTPListener for all incoming and outgoing HTTP messages.
 * @author Tim Guenther
 * @version 1.0
 */
public class ModifyHTTPRequest implements IHttpListener{
    
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    
    public ModifyHTTPRequest(IBurpExtenderCallbacks callbacks){
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();
    }
    
    public void processHttpMessage(int toolFlag, boolean isRequest, IHttpRequestResponse ihrr) {
//        Logging.getInstance().log(this.getClass(), "The listener is not implemented",Logging.INFO);
        
        if(isRequest){
            IRequestInfo iri = helpers.analyzeRequest(ihrr);
            List<IParameter> paramList = iri.getParameters();
            
            // Cycle through all parameters
            for(IParameter param : paramList){
                //Base64 encode the parameter value
                String val = param.getValue();
                if(toolFlag == IBurpExtenderCallbacks.TOOL_PROXY){
                    val = helpers.urlDecode(val);
                }
                val = helpers.base64Encode(val);
                
                //Create a new IParameter with the Base64 encoded value.
                param = helpers.buildParameter(param.getName(), val, param.getType());
                Logging.getInstance().log(this.getClass(), "Update '"+param.getName()+"'='"+param.getValue()+"'", Logging.DEBUG);
                
                //Update the IHttpRequest with the new parameter
                ihrr.setRequest(helpers.updateParameter(ihrr.getRequest(), param));
            }
            ihrr.setComment("Base64 Encoded payload.");
        }
        Logging.getInstance().log(this.getClass(), "Request up-to-date.", Logging.DEBUG);
    }
}
