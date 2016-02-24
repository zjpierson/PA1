package org.jfree.xml.parser.coretypes;

import java.awt.geom.Rectangle2D;

import org.jfree.xml.parser.AbstractXmlReadHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * A handler for reading a {@link Rectangle2D} object.
 */
public class Rectangle2DReadHandler extends AbstractXmlReadHandler  {
    
    /** The rectangle being constructed. */
    private Rectangle2D rectangle;

    /**
     * Default constructor.
     */
    public Rectangle2DReadHandler() {
        super();
    }

    /**
     * Begins parsing.
     * 
     * @param attrs  the attributes.
     * 
     * @throws SAXException if there is a parsing error.
     */
    protected void startParsing(Attributes attrs) throws SAXException {
        String type = attrs.getValue("type");
        this.rectangle = createRect(type);
        String x = attrs.getValue("x");
        String y = attrs.getValue("y");
        String w = attrs.getValue("width");
        String h = attrs.getValue("height");

        this.rectangle.setRect(
            Double.parseDouble(x), Double.parseDouble(y),
            Double.parseDouble(w), Double.parseDouble(h)
        );
    }

    /**
     * Creates a rectangle.
     * 
     * @param type  the type ('float' or 'double').
     * 
     * @return The rectangle.
     */
    private Rectangle2D createRect(String type) {
        if ("float".equals(type)) {
            return new Rectangle2D.Float();
        }
        return new Rectangle2D.Double();
    }

    /**
     * Returns the object under construction.
     * 
     * @return The object.
     */
    public Object getObject() {
        return this.rectangle;
    }
}
