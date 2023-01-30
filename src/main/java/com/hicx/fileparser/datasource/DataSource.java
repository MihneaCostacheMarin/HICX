package com.hicx.fileparser.datasource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

public interface DataSource {
    boolean hasNext();
    byte[] next();
}