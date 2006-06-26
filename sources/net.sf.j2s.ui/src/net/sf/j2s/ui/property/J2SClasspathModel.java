/*******************************************************************************
 * Java2Script Pacemaker (http://j2s.sourceforge.net)
 *
 * Copyright (c) 2006 ognize.com and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ognize.com - initial API and implementation
 *******************************************************************************/
package net.sf.j2s.ui.property;

import java.util.ArrayList;
import java.util.List;

import net.sf.j2s.ui.classpath.Resource;
import net.sf.j2s.ui.classpath.UnitClass;

/**
 * @author josson smith
 *
 * 2006-2-1
 */
public class J2SClasspathModel {
	protected List resources = new ArrayList();
	protected List unitClasses = new ArrayList();
	protected List abandonedClasses = new ArrayList();
	
	public void addResource(Resource res) {
		resources.add(res);
	}
	public void removeResource(Resource res) {
		resources.remove(res);
	}
	
	public void addUnitClass(UnitClass cl) {
		unitClasses.add(cl);
	}
	public void removeUnitClass(UnitClass cl) {
		unitClasses.remove(cl);
	}
	
	public void addAbandonedClass(UnitClass cl) {
		abandonedClasses.add(cl);
	}
	public void removeAbandonedClass(UnitClass cl) {
		abandonedClasses.remove(cl);
	}

	public void abandonUnitClass(UnitClass cl) {
		unitClasses.remove(cl);
		abandonedClasses.add(cl);
	}
	
	public void restoreUnitClass(UnitClass cl) {
		abandonedClasses.remove(cl);
		unitClasses.add(cl);
	}
	
	public Resource[] getResources() {
		return (Resource[]) resources.toArray(new Resource[0]);
	}
	
	public UnitClass[] getUnitClasses() {
		return (UnitClass[]) unitClasses.toArray(new UnitClass[0]);
	}
	
	public UnitClass[] getAbandonedClasses() {
		return (UnitClass[]) abandonedClasses.toArray(new UnitClass[0]);
	}
	
	static String[] categories = new String[] {
		"Resources",
		"Classes",
		"Abandoned Classes"
	};
	public J2SCategory[] getCategories() {
		J2SCategory[] ctgs = new J2SCategory[3];
		for (int i = 0; i < ctgs.length; i++) {
			ctgs[i] = new J2SCategory(this, categories[i]);
		}
		return ctgs;
	}
	
	public boolean isResourceInResources(Object res) {
		return resources.contains(res);
	}
	
	public boolean isResourceInClasses(Object res) {
		return unitClasses.contains(res);
	}
	
	public boolean isResourceInAbandons(Object res) {
		return abandonedClasses.contains(res);
	}
	
	public void removeTheResource(Resource res) {
		if (!resources.remove(res)) {
			if (!unitClasses.remove(res)) {
				abandonedClasses.remove(res);
			}
		}
	}
}