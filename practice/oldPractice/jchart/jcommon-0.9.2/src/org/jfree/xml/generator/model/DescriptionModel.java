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
 * ---------------------
 * DescriptionModel.java
 * ---------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: DescriptionModel.java,v 1.10 2004/03/05 13:45:43 mungady Exp $
 *
 * Changes
 * -------
 * 21-Jun-2003 : Initial version (TM);
 *
 */

package org.jfree.xml.generator.model;

import java.util.HashMap;
import java.util.ArrayList;


import org.jfree.util.Log;

/**
 * A model containing class descriptions.
 */
public class DescriptionModel {

    /** The sources. */
    private ArrayList sources;
    
    /** The classes. */
    private ArrayList classes;

    /** Maps classes to class descriptions. */
    private HashMap classesMap;
    
    /** The mapping model. */
    private MappingModel mappingModel;
    
    /** Model comments. */
    private Comments modelComments;
    
    /** Include comments. */
    private HashMap includeComments;

    /**
     * Creates a new class description model.
     */
    public DescriptionModel() {
        this.classes = new ArrayList();
        this.classesMap = new HashMap();
        this.mappingModel = new MappingModel();
        this.sources = new ArrayList();
        this.includeComments = new HashMap();
    }

    /**
     * Adds a class description to the model.
     * 
     * @param cd  the class description.
     */
    public void addClassDescription(ClassDescription cd) {
        this.classesMap.put(cd.getObjectClass(), cd);
        if (!this.classes.contains(cd)) {
            this.classes.add(cd);
        }
    }

    /**
     * Removes a class description from the model.
     * 
     * @param cd  the class description.
     */
    public void removeClassDescription(ClassDescription cd) {
        this.classesMap.remove(cd.getObjectClass());
        this.classes.remove(cd);
    }

    /**
     * Returns a class description.
     * 
     * @param index  the description index (zero-based).
     * 
     * @return a class description.
     */
    public ClassDescription get(int index) {
        return (ClassDescription) this.classes.get(index);
    }

    /**
     * Returns a class description for the given class name.
     * 
     * @param key  the class name.
     * 
     * @return the class description.
     */
    public ClassDescription get(Class key) {
        return (ClassDescription) this.classesMap.get(key);
    }

    /**
     * Returns the number of classes in the model.
     * 
     * @return the number of classes in the model.
     */
    public int size() {
        return this.classes.size();
    }

    /**
     * Returns the mapping model.
     * 
     * @return the mapping model.
     */
    public MappingModel getMappingModel() {
        return this.mappingModel;
    }

    /**
     * Adds a source to the model description.
     * 
     * @param source  the source.
     */
    public void addSource(String source) {
        this.sources.add(source);
    }

    /**
     * Returns the sources for the model description.
     * 
     * @return The sources.
     */
    public String[] getSources() {
        return (String[]) this.sources.toArray(new String[this.sources.size()]);
    }

    /**
     * Removes any class descriptions that are not fully defined.
     */
    public void prune() {
        ClassDescription[] cds = (ClassDescription[]) this.classes.toArray(new ClassDescription[0]);
        for (int i = 0; i < cds.length; i++) {
            if (cds[i].isUndefined()) {
                removeClassDescription(cds[i]);
                Log.debug("Pruned: " + cds[i].getName());
            }
        }
    }

    /**
     * Adds an include comment.
     * 
     * @param source  the source.
     * @param comments  the comments.
     */
    public void addIncludeComment(String source, Comments comments) {
        this.includeComments.put(source, comments);
    }

    /**
     * Returns the include comment for the specified source.
     * 
     * @param source  the source.
     * 
     * @return The include comment.
     */
    public Comments getIncludeComment(String source) {
        return (Comments) this.includeComments.get(source);
    }

    /**
     * Returns the model comments.
     * 
     * @return The model comments.
     */
    public Comments getModelComments() {
        return this.modelComments;
    }

    /**
     * Sets the model comments.
     * 
     * @param modelComments  the model comments.
     */
    public void setModelComments(Comments modelComments) {
        Log.debug ("Model: Comment set: " + modelComments);
        this.modelComments = modelComments;
    }
    
}
