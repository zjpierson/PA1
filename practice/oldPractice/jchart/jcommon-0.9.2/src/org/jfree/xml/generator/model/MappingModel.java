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
 * -----------------
 * MappingModel.java
 * -----------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: MappingModel.java,v 1.6 2004/03/05 13:45:43 mungady Exp $
 *
 * Changes 
 * -------
 * 20-Nov-2003 : Initial version
 *  
 */

package org.jfree.xml.generator.model;

import java.util.HashMap;
import java.util.ArrayList;

import org.jfree.util.Log;

/**
 * A mapping model.
 */
public class MappingModel {
    
    /** Mapping infos. */
    private HashMap mappingInfos;
    
    /** The manual mappings. */
    private ArrayList manualMappings;
    
    /** The multiplex mappings. */
    private ArrayList multiplexMappings;

    /**
     * Creates a new instance.
     */
    public MappingModel() {
        this.mappingInfos = new HashMap();
        this.manualMappings = new ArrayList();
        this.multiplexMappings = new ArrayList();
    }

    /**
     * Returns the multiplex mapping info.
     * 
     * @return The multiplex mapping info.
     */
    public MultiplexMappingInfo[] getMultiplexMapping() {
        return (MultiplexMappingInfo[]) this.multiplexMappings.toArray(
            new MultiplexMappingInfo[this.multiplexMappings.size()]
        );
    }

    /**
     * Returns the manual mapping info.
     * 
     * @return The manual mapping info.
     */
    public ManualMappingInfo[] getManualMapping() {
        return (ManualMappingInfo[]) this.manualMappings.toArray(
            new ManualMappingInfo[this.manualMappings.size()]
        );
    }

    /**
     * Adds a manual mapping.
     * 
     * @param mappingInfo  the mapping.
     */
    public void addManualMapping(ManualMappingInfo mappingInfo) {
        if (!this.mappingInfos.containsKey(mappingInfo.getBaseClass())) {
            this.manualMappings.add(mappingInfo);
            this.mappingInfos.put(mappingInfo.getBaseClass(), mappingInfo);
        }
        else {
            Object o = this.mappingInfos.get(mappingInfo.getBaseClass());
            if (o instanceof ManualMappingInfo) {
                Log.info ("Duplicate manual mapping: " + mappingInfo.getBaseClass());
            }
            else {
                throw new IllegalArgumentException
                    ("This mapping is already a multiplex mapping.");
            }
        }
    }

    /**
     * Adds a multiplex mapping.
     * 
     * @param mappingInfo  the mapping.
     */
    public void addMultiplexMapping(MultiplexMappingInfo mappingInfo) {
        if (!this.mappingInfos.containsKey(mappingInfo.getBaseClass())) {
            this.multiplexMappings.add(mappingInfo);
            this.mappingInfos.put(mappingInfo.getBaseClass(), mappingInfo);
        }
        else {
            Object o = this.mappingInfos.get(mappingInfo.getBaseClass());
            if (o instanceof ManualMappingInfo) {
                throw new IllegalArgumentException
                    ("This mapping is already a manual mapping.");
            }
            else {
                Log.info(
                    "Duplicate Multiplex mapping: " + mappingInfo.getBaseClass(), new Exception()
                );
            }
        }

    }

    /**
     * Returns a multiplex mapping for the specified class.
     * 
     * @param baseClass  the base class.
     * 
     * @return The mapping.
     */
    public MultiplexMappingInfo lookupMultiplexMapping(Class baseClass) {
        Object o = this.mappingInfos.get(baseClass);
        if (o instanceof MultiplexMappingInfo) {
            return (MultiplexMappingInfo) o;
        }
        return null;
    }

}
