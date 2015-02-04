package OfficeImport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Import {

	public abstract void importExcelClient(File file) throws FileNotFoundException, IOException;
	public abstract void importExcelSupplier(File file)throws FileNotFoundException, IOException;
}
