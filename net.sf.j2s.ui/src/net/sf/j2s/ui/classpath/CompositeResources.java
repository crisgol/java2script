package net.sf.j2s.ui.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class CompositeResources extends Resource implements IClasspathContainer {
	protected List resources;
	protected List abandomedResources;
	//private Resource[] children;
	private String binRelativePath;

	private String compilerStatus;
	
	public File getAbsoluteFile() {
		if (getRelativePath().startsWith("/")) {
			return new File(getFolder(), ".." + getRelativePath());
		} else {
			return super.getAbsoluteFile();
		}
	}
	public String getName() {
//		String path = getRelativePath();
//		return path.substring(1, path.indexOf('/', 2));
		return super.getName();
	}

	public boolean exists() {
		if (getRelativePath().startsWith("/")) {
			return new File(getFolder(), ".." + getRelativePath()).exists();
		} else {
			return super.exists();
		}
	}
	public String getBinRelativePath() {
		if (getRelativePath().startsWith("/")) {
			return ".." + getRelativePath().substring(0, getRelativePath().lastIndexOf('/') + 1);
		} else {
			return "";
			//return binRelativePath;
		}
		//return super.getBinRelativePath();
	}

	public void load() {
		File file = getAbsoluteFile();
		resources = new ArrayList();
		abandomedResources = new ArrayList();
		if (file != null && file.exists()) {
			InputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			load(fis);
		}
	}

	public String getCompilerStatus() {
		return compilerStatus;
	}
	public void load(InputStream fis) {
		resources = new ArrayList();
		abandomedResources = new ArrayList();
		if (fis != null) {
			Properties props = new Properties();
			try {
				props.load(fis);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//setFolder(file.getParentFile());
			//setRelativePath(file.getName());
			compilerStatus = props.getProperty("j2s.compiler.status");
			
			binRelativePath = props.getProperty(PathUtil.J2S_OUTPUT_PATH);
			
			String[] reses = PathUtil.getResources(props);
			addResourceByString(resources, reses);
			
			reses = PathUtil.getAbandomedResources(props);
			addResourceByString(abandomedResources, reses);

			/*
			this.children = new Resource[resources.size()];
			for (int i = 0; i < resources.size(); i++) {
				Resource res = (Resource) resources.get(i);
				this.children[i] = res;
				res.setParent(this);
			}
			*/
		}
	}
	
	public Resource[] getAbandomedResources() {
		if (abandomedResources == null) {
			return new Resource[0];
		}
		return (Resource []) abandomedResources.toArray(new Resource[0]);
	}
	private void addResourceByString(List resourcesList, String[] reses) {
		for (int i = 0; i < reses.length; i++) {
			if (reses[i] != null) {
				String res = reses[i].trim();
				if (res.startsWith("|")) {
					res = res.substring(1);
					Resource rr = null;
					if (res.endsWith(".z.js")) {
						rr = new ContactedClasses();
					} else if (res.endsWith(".css")) {
						rr = new CSSResource();
					} else if (res.endsWith("/.j2s")) {
						rr = new ProjectResources();
					} else if (res.endsWith(".j2s")) {
						rr = new CompositeResources();
					}
					rr.setFolder(new File(res).getParentFile());
					rr.setRelativePath(new File(res).getName());
					rr.setParent(this);
					rr.setAbsolute(true);
					resourcesList.add(rr);
				} else if (res.endsWith(".z.js")) {
					ContactedClasses jz = new ContactedClasses();
					jz.setFolder(this.getAbsoluteFolder());
					jz.setRelativePath(res);
					jz.setParent(this);
					resourcesList.add(jz);
				} else if (res.endsWith(".js")) {
					UnitClass unit = new UnitClass();
					unit.setFolder(this.getAbsoluteFolder());
					unit.setRelativePath(res);
					unit.setBinRelativePath(binRelativePath);
					unit.parseClassName();
					unit.setParent(this);
					resourcesList.add(unit);
				} else if (res.endsWith(".css")) {
					CSSResource css = new CSSResource();
					css.setFolder(this.getAbsoluteFolder());
					css.setRelativePath(res);
					css.setParent(this);
					resourcesList.add(css);
				} else if (res.endsWith("/.j2s")) {
					ProjectResources prj = new ProjectResources();
					prj.setFolder(this.getAbsoluteFolder());
					prj.setRelativePath(res);
					prj.setParent(this);
					resourcesList.add(prj);
				} else if (res.endsWith(".j2s")) {
					CompositeResources comp = new CompositeResources();
					comp.setFolder(this.getAbsoluteFolder());
					comp.setRelativePath(res);
					comp.setParent(this);
					resourcesList.add(comp);
				}
			}
		}
	}
	public void store(Properties props) {
		Resource[] reses = getChildren();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < reses.length; i++) {
			String str = reses[i].toResourceString();
			//if (str != null) {
				buf.append(str);
				if (i != reses.length - 1) {
					buf.append(",");
				}
			//}
		}
		props.setProperty(PathUtil.J2S_RESOURCES_LIST, buf.toString());
		props.setProperty(PathUtil.J2S_OUTPUT_PATH, binRelativePath);
	}

	public Resource[] getChildren() {
		if (resources == null) {
			this.load();
		}
		return (Resource[]) resources.toArray(new Resource[0]);
		//return children;
	}
	public void addResource(Resource res) {
		if (!resources.contains(res)) {
			resources.add(res);
		}
	}
	public boolean existedResource(Resource res) {
		return resources.contains(res);
	}
	public void removeResource(Resource res) {
		resources.add(res);
	}
	public void removeResource(int res) {
		resources.remove(res);
	}
	public void upResource(Resource res) {
		//for (int i = 0;)
	}
	public void downResource(Resource res) {
		
	}
	public void topResource(Resource res) {
		//for (int i = 0;)
	}
	public void bottomResource(Resource res) {
		
	}
	public void upResource(int res) {
		//for (int i = 0;)
	}
	public void downResource(int res) {
		
	}
	public void topResource(int res) {
		//for (int i = 0;)
	}
	public void bottomResource(int res) {
		
	}
//	public String getBinRelativePath() {
//		return binRelativePath;
//	}

	public void setBinRelativePath(String binRelativePath) {
		this.binRelativePath = binRelativePath;
	}
	public String toHTMLString() {
		StringBuffer buf = new StringBuffer();
		if (resources == null) {
			this.load();
		}
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			Resource res = (Resource) iter.next();
			buf.append(res.toHTMLString());
		}
		return buf.toString();
	}
	public int getType() {
		return VARIABLE;
	}
}