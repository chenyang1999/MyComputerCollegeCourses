package sample.commandline.filefilter;

import java.io.File;
import java.io.FileFilter;

public class MyFilter implements FileFilter {
  private String extension;
  
  public MyFilter() {
	  
  }
 
  public MyFilter(String extension) {
    this.extension = extension;
  }
  
  @Override
  public boolean accept(File file) {
    if (file.isDirectory())
    return false;
    
    String name = file.getName();
    int idx = name.lastIndexOf(".");
    
    if ((idx == -1) || (idx == (name.length() - 1))) {
      return false;
    } else {
      return name.substring(idx).equals(extension);
    }
  }
}
