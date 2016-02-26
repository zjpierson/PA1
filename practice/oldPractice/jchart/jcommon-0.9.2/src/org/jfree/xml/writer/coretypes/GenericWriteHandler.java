/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 * 
 * Project Info:  http://www.jfree.org/jcommon/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 * 
 * ------------------------
 * GenericWriteHandler.java
 * ------------------------
 * (C)opyright 2003, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: GenericWriteHandler.java,v 1.12 2004/03/24 23:20:21 mungady Exp $
 *
 * Changes
 * -------
 * 23-Sep-2003 : Initial version (TM);
 * 23-Dec-2003 : Added missing Javadocs (DG);
 * 
 */

package org.jfree.xml.writer.coretypes;

import java.io.IOException;
import java.util.ArrayList;

import org.jfree.xml.util.AttributeDefinition;
import org.jfree.xml.util.GenericObjectFactory;
import org.jfree.xml.util.ObjectDescriptionException;
import org.jfree.xml.util.PropertyDefinition;
import org.jfree.xml.writer.AbstractXmlWriteHandler;
import org.jfree.xml.writer.AttributeList;
import org.jfree.xml.writer.RootXmlWriteHandler;
import org.jfree.xml.writer.XMLWriter;
import org.jfree.xml.writer.XMLWriterException;
import org.jfree.util.Log;

/**
 * A handler for writing generic objects.
 */
public class GenericWriteHandler extends AbstractXmlWriteHandler {

    private GenericObjectFactory factory;

    /**
     * Creates a new handler.
     * 
     * @param factory  the object factory.
     */
    public GenericWriteHandler(GenericObjectFactory factory) {
        this.factory = factory;
    }

    /**
     * Performs the writing of a generic object.
     *
     * @param tagName  the tag name.
     * @param object  the generic object.
     * @param writer  the writer.
     * @param mPlexAttribute  ??.
     * @param mPlexValue  ??.
     * 
     * @throws IOException if there is an I/O error.
     * @throws XMLWriterException if there is a writer error.
     */
    public void write(String tagName, Object object, XMLWriter writer,
                      String mPlexAttribute, String mPlexValue)
        throws IOException, XMLWriterException {

        try {
            this.factory.readProperties(object);

            AttributeList attributes = new AttributeList();
            if (mPlexAttribute != null) {
                attributes.setAttribute(mPlexAttribute, mPlexValue);
            }
            AttributeDefinition[] attribDefs = this.factory.getAttributeDefinitions();
            ArrayList properties = new ArrayList();
            for (int i = 0; i < attribDefs.length; i++) {
                AttributeDefinition adef = attribDefs[i];
                String pName = adef.getAttributeName();
                Object propValue = this.factory.getProperty(adef.getPropertyName());
                if (propValue != null) {
                    Log.debug(
                        "Here: " + this.factory.getBaseClass() + " -> " + adef.getPropertyName()
                    );
                    String value = adef.getHandler().toAttributeValue(propValue);
                    if (value != null) {
                        attributes.setAttribute(pName, value);
                    }
                }
                properties.add(adef.getPropertyName());
            }
            writer.writeTag(tagName, attributes, false);
            writer.startBlock();

            PropertyDefinition[] propertyDefs = this.factory.getPropertyDefinitions();
            RootXmlWriteHandler rootHandler = getRootHandler();
            for (int i = 0; i < propertyDefs.length; i++) {
                PropertyDefinition pDef = propertyDefs[i];
                String elementName = pDef.getElementName();
                // System.out.println("Searching: " + elementName + " [" + object.getClass() + "]");
                rootHandler.write
                    (elementName, this.factory.getProperty(pDef.getPropertyName()),
                            this.factory.getTypeForTagName(elementName), writer);
            }
            writer.endBlock();
            writer.writeCloseTag(tagName);
        }
        catch (ObjectDescriptionException ode) {
            Log.warn ("Unable to write element", ode);
            throw new IOException(ode.getMessage());
        }
    }

}
