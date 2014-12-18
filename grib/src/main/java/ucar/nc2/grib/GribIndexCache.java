package ucar.nc2.grib;

import ucar.nc2.util.DiskCache2;

import java.io.File;

/**
 * manages where the grib index files live
 *
 * @author caron
 * @since 12/18/2014
 */
public class GribIndexCache {

  static private DiskCache2 diskCache;

  static synchronized public void setDiskCache2(DiskCache2 dc) {
    diskCache = dc;
  }

  static synchronized public DiskCache2 getDiskCache2() {
    if (diskCache == null)
      diskCache = DiskCache2.getDefault();
    return diskCache;
  }

  /**
   * Get index file, may be in cache directory, may not exist
   *
   * @param fileLocation full path of original index filename
   * @return File, possibly in cache, may or may not exist
   */
  static public File getFileInCache(String fileLocation) {
    File result = getExistingFileInCache(fileLocation);
    if (result != null) return result;
    return getDiskCache2().getFile(fileLocation);
  }

  /**
   * Looking for an existing file, in cache or not
   *
   * @param fileLocation full path of original index filename
   * @return existing file if you can find it, else null
   */
  static public File getExistingFileInCache(String fileLocation) {
    return getDiskCache2().getExistingFileOrCache(fileLocation);
  }
}
