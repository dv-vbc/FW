/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.ws.eis.tax;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

/**
 *
 * @author DragonFly
 */
public class SampleService {  
  
    public OMElement sayHello(OMElement element){  
          
        element.build();  
        element.detach();  
          
        String rootName = element.getLocalName();  
        System.out.println("Reading " + rootName + " element");  
          
        OMElement childElement = element.getFirstElement();  
        String personToGreet = childElement.getText();  
          
        OMFactory factory = OMAbstractFactory.getOMFactory();  
        OMNamespace omNamespace = factory.createOMNamespace("http://example1.org/example1", "example1");  
      
        OMElement method = factory.createOMElement("sayHelloResponse", omNamespace);  
        OMElement value = factory.createOMElement("greeting", omNamespace);  
        value.addChild(factory.createOMText(value, "Hello, " + personToGreet));  
        method.addChild(value);  
          
        return method;  
    }  
}  
