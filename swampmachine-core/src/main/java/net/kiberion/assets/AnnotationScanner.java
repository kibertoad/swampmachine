package net.kiberion.assets;

import java.io.IOException;
import java.net.URL;

import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

public class AnnotationScanner {

	public void scan() {
		URL[] urls = ClasspathUrlFinder.findClassPaths();
		AnnotationDB db = new AnnotationDB();

		db.setScanPackages(new String[] { "net.kiberion" });

		try {
			db.scanArchives(urls);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
