/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.fils.angularspring.util;

/**
 *
 * @author andre
 */
public interface XSLTFile {

    static final String xsltString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"\n"
            + "    xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n"
            + "    exclude-result-prefixes=\"xs\"\n"
            + "    version=\"2.0\">\n"
            + "    <xsl:template match=\"/\">\n"
            + "        <html>\n"
            + "            <body>\n"
            + "                <table border=\"1\">\n"
            + "                    <tr bgcolor=\"#9acd32\">\n"
            + "                        <th> Title </th>\n"
            + "                        <th> Budget </th>\n"
            + "                        <th> Domain </th>\n"
            + "                        <th> Duration </th>\n"
            + "                        <th> Partner</th>\n"
            + "                        <th> Objective </th>\n"
            + "                    </tr>\n"
            + "                    \n"
            + "                    <xsl:for-each select=\"projects/project\">\n"
            + "                   <tr>\n"
            + "                            <td><xsl:value-of select=\"title\"/></td>\n"
            + "                            <td><xsl:value-of select=\"budget\"></xsl:value-of>$</td>\n"
            + "                            <td><xsl:value-of select=\"domain\"></xsl:value-of></td>\n"
            + "                           <td><xsl:value-of select=\"duration\"></xsl:value-of> Days </td>\n"
            + "                           <td>\n"
            + "                        <xsl:for-each select=\"partners/partner\"> \n"
            + "                           <xsl:value-of select=\"name\"/> |\n"
            + "                    </xsl:for-each>\n"
            + "                     </td>\n"
            + "                      <td><xsl:value-of select=\"objective\"></xsl:value-of></td>\n"
            + "                       </tr>\n"
            + "                    </xsl:for-each>\n"
            + "                </table>            \n"
            + "            </body>\n"
            + "        </html>\n"
            + "    </xsl:template>\n"
            + "</xsl:stylesheet>";
}
