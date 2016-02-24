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
 * -------------------------
 * DescriptionGenerator.java
 * -------------------------
 * (C)opyright 2003, 2004, by Thomas Morgner and Contributors.
 *
 * Original Author:  Thomas Morgner;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * $Id: DescriptionGenerator.java,v 1.8 2004/02/04 15:51:37 mungady Exp $
 *
 * Changes
 * -------------------------
 * 21.06.2003 : Initial version
 *
 */

package org.jfree.xml.generator;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import org.jfree.xml.generator.model.DescriptionModel;
import org.jfree.util.Log;
import org.jfree.util.PrintStreamLogTarget;

/**
 * A utility application for generating class descriptions.
 */
public final class DescriptionGenerator {

    /**
     * Loads a property set from the specified URL.
     * 
     * @param propertyURL  the URL.
     * 
     * @return The properties.
     */
    private static Properties loadProperties(URL propertyURL) {
        Properties p = new Properties();
        try {
            InputStream in = new BufferedInputStream(propertyURL.openStream());
            p.load(in);
            in.close();
        }
        catch (Exception e) {
            System.err.println("Unable to load properties from " + propertyURL);
        }
        return p;
    }

    /**
     * Runs the generator, using the 'generator.properties' file for configuration info.
     * 
     * @param args  command line arguments.
     * 
     * @throws Exception if something goes wrong!
     */
    public static void main(String[] args) throws Exception {

        Log.getInstance().addTarget(new PrintStreamLogTarget());
        
        URL propertyURL = DescriptionGenerator.class.getResource("generator.properties");
        if (args.length > 0) {
            File f = new File(args[0]);
            propertyURL = f.toURL();
        }
        Properties p = loadProperties(propertyURL);

        String handlerSource = p.getProperty("attributedefinition");
        if (handlerSource != null) {
            Properties handlers = loadProperties(new URL(propertyURL, handlerSource));
            ModelBuilder.getInstance().addAttributeHandlers(handlers);
        }

        String source = p.getProperty("sourcedirectory", ".");
        String target = p.getProperty("targetfile", "model.xml");
        DescriptionModel model = null;
        try {
            model = new DefaultModelReader().load(target);
        }
        catch (Exception e) {
            Log.debug("Unable to load default model. Ignoring...");
        }
//        Log.debug (model.getModelComments());
        model = generate(source, p, model);
        model.prune();
        writeMultiFile(target, model);
        System.exit(0);
    }

    /**
     * Generates a {@link DescriptionModel} from the specified source.
     * 
     * @param source  the source directory.
     * @param configuration  the configuration properties.
     * @param model  the model (<code>null</code> permitted).
     * 
     * @return A class description model.
     */
    public static DescriptionModel generate(String source,
                                            Properties configuration,
                                            DescriptionModel model) {
        
        JavaSourceCollector jsc = new JavaSourceCollector(new File(source));

        Iterator it = configuration.keySet().iterator();
        while (it.hasNext()) {
            String pName = (String) it.next();
            if (pName.startsWith("ignore.baseclass.")) {
                jsc.addIgnoredBaseClass(configuration.getProperty(pName));
            }
            else if (pName.startsWith("ignore.package.")) {
                jsc.addIgnoredPackage(configuration.getProperty(pName));
            }
        }

        jsc.collectFiles();
        return ModelBuilder.getInstance().buildModel(jsc, model);
    }

    /**
     * Writes the class description model to a single file.
     * 
     * @param target  the target file name.
     * @param model  the class description model.
     * 
     * @throws IOException if there is an I/O problem.
     */
    public static void writeSingleFile(String target, DescriptionModel model) throws IOException {
        Log.debug ("Writing ...");
        ModelWriter writer = new ModelWriter();
        writer.setModel(model);
        Writer w = new BufferedWriter(new FileWriter(target));
        writer.write(w);
        w.close();
    }

    /**
     * Writes the class description model to multiple files.
     * 
     * @param target  the target file name.
     * @param model  the class description model.
     * 
     * @throws IOException if there is an I/O problem.
     */
    public static void writeMultiFile(String target, DescriptionModel model) throws IOException {
        Log.debug ("Writing multiple files ...");
        SplittingModelWriter writer = new SplittingModelWriter();
        writer.setModel(model);
        writer.write(target);
    }
    
}
