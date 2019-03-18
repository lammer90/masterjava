<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <!--<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>-->
    <!--<xsl:strip-space elements="*"/>-->
    <xsl:param name="project_name"/>
    <xsl:template match="/">
        <html>
            <body>
                <h1>Groups</h1>
                <table border="1">
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                    </tr>
                    <xsl:for-each select="*[name()='Payload']/*[name()='Projects']/*[name()='Project'][@name=$project_name]/*[name()='Group']">
                        <tr>
                            <td>
                                <xsl:value-of select="@name"/>
                            </td>
                            <td>
                                <xsl:value-of select="@type"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="text()"/>
</xsl:stylesheet>