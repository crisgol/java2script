/*
 * Some portions of this file have been modified by Robert Hanson hansonr.at.stolaf.edu 2012-2017
 * for use in SwingJS via transpilation into JavaScript using Java2Script.
 *
 * Copyright (c) 1997, 2006, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package javax.swing;



/**
 * An implementation of a radio button -- an item that can be selected or
 * deselected, and which displays its state to the user.
 * Used with a {@link ButtonGroup} object to create a group of buttons
 * in which only one button at a time can be selected. (Create a ButtonGroup
 * object and use its <code>add</code> method to include the JRadioButton objects
 * in the group.)
 * <blockquote>
 * <strong>Note:</strong>
 * The ButtonGroup object is a logical grouping -- not a physical grouping.
 * To create a button panel, you should still create a {@link JPanel} or similar
 * container-object and add a {@link javax.swing.border.Border} to it to set it off from surrounding
 * components.
 * </blockquote>
 * <p>
 * Buttons can be configured, and to some degree controlled, by
 * <code><a href="Action.html">Action</a></code>s.  Using an
 * <code>Action</code> with a button has many benefits beyond directly
 * configuring a button.  Refer to <a href="Action.html#buttonActions">
 * Swing Components Supporting <code>Action</code></a> for more
 * details, and you can find more information in <a
 * href="http://java.sun.com/docs/books/tutorial/uiswing/misc/action.html">How
 * to Use Actions</a>, a section in <em>The Java Tutorial</em>.
 * <p>
 * See <a href="http://java.sun.com/docs/books/tutorial/uiswing/components/button.html">How to Use Buttons, Check Boxes, and Radio Buttons</a>
 * in <em>The Java Tutorial</em>
 * for further documentation.
 * <p>
 * <strong>Warning:</strong> Swing is not thread safe. For more
 * information see <a
 * href="package-summary.html#threading">Swing's Threading
 * Policy</a>.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans<sup><font size="-2">TM</font></sup>
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @beaninfo
 *   attribute: isContainer false
 * description: A component which can display it's state as selected or deselected.
 *
 * @see ButtonGroup
 * @see JCheckBox
 * @author Jeff Dinkins
 */
public class JRadioButton extends JToggleButton {


    /**
     * Creates an initially unselected radio button
     * with no set text.
     */
    public JRadioButton () {
        this(null, null, false);
    }

    /**
     * Creates an initially unselected radio button
     * with the specified image but no text.
     *
     * @param icon  the image that the button should display
     */
    public JRadioButton(Icon icon) {
        this(null, icon, false);
    }

    /**
     * Creates a radiobutton where properties are taken from the
     * Action supplied.
     *
     * @since 1.3
     */
    public JRadioButton(Action a) {
        this();
        setAction(a);
    }

    /**
     * Creates a radio button with the specified image
     * and selection state, but no text.
     *
     * @param icon  the image that the button should display
     * @param selected  if true, the button is initially selected;
     *                  otherwise, the button is initially unselected
     */
    public JRadioButton(Icon icon, boolean selected) {
        this(null, icon, selected);
    }

    /**
     * Creates an unselected radio button with the specified text.
     *
     * @param text  the string displayed on the radio button
     */
    public JRadioButton (String text) {
        this(text, null, false);
    }

    /**
     * Creates a radio button with the specified text
     * and selection state.
     *
     * @param text  the string displayed on the radio button
     * @param selected  if true, the button is initially selected;
     *                  otherwise, the button is initially unselected
     */
    public JRadioButton (String text, boolean selected) {
        this(text, null, selected);
    }

    /**
     * Creates a radio button that has the specified text and image,
     * and that is initially unselected.
     *
     * @param text  the string displayed on the radio button
     * @param icon  the image that the button should display
     */
    public JRadioButton(String text, Icon icon) {
        this(text, icon, false);
    }

    /**
     * Creates a radio button that has the specified text, image,
     * and selection state.
     *
     * @param text  the string displayed on the radio button
     * @param icon  the image that the button should display
     */
    public JRadioButton (String text, Icon icon, boolean selected) {
        super(text, icon, selected);
        setBorderPainted(false);
        setHorizontalAlignment(LEADING);
    }

	@Override
	public String getUIClassID() {
		return "RadioButtonUI";
	}


    /**
     * The icon for radio buttons comes from the look and feel,
     * not the Action.
     */
    @Override
		void setIconFromAction(Action a) {
    }

    /**
     * Returns a string representation of this JRadioButton. This method
     * is intended to be used only for debugging purposes, and the
     * content and format of the returned string may vary between
     * implementations. The returned string may be empty but may not
     * be <code>null</code>.
     *
     * @return  a string representation of this JRadioButton.
     */
    @Override
		protected String paramString() {
        return super.paramString();
    }

}
