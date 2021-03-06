/*
 * -------------------------
 * 
 * MIT License
 * 
 * Copyright (c) 2018, Schneider Electric USA, Inc.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * ---------------------
 */
package semanticstore.ontology.library.generator.model.xsd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import semanticstore.ontology.library.generator.global.CODE;
import semanticstore.ontology.library.generator.model.ZPair;

public class XsdTypeMapper {

  @SuppressWarnings("unused")
  private static Log log = LogFactory.getLog(XsdTypeMapper.class);
  private static boolean systemType = false;

  /**
   * Used to map ontology types to specific language type.
   * 
   * @param Ontology type and the Code "language" we need to map to.
   * @return Pair of type name in specified language and the namespace or package name.
   */

  public synchronized static ZPair<String, String> getClassName(String xsdUri, CODE code)
      throws IllegalArgumentException {
    xsdUri = xsdUri.replace("http://www.w3.org/2001/XMLSchema#", "xsd:");
    ZPair<String, String> type;
    switch (code) {

      case C_SHARP:
        type = XsdMapConfig.getXsd2cSharpName().get(xsdUri);
        if (type.getKey().equalsIgnoreCase("DateTime")) {
          systemType = true;
        }
        return type;

      case JAVA:
        type = XsdMapConfig.getXsd2JavaName().get(xsdUri);
        return type;
      case PYTHON:
        type = XsdMapConfig.getXsd2PythonName().get(xsdUri);
        return type;

      default:
        throw new IllegalArgumentException(xsdUri);

    }
  }

  public synchronized static boolean isSystemType() {
    return systemType;
  }

  /*
   * public static String getAccessMethod(String xsdUri) { String javaClass =
   * XsdMapConfig.xsd2javaName.get(xsdUri); return XsdMapConfig.javaName2Method.get(javaClass); }
   */

}
