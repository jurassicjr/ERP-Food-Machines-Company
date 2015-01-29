package OfficeImport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Import {

	public abstract void importExcel(File file) throws FileNotFoundException, IOException;
}
