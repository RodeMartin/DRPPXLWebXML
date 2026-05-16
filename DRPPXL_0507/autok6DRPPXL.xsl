<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="UTF-8"/>
    <xsl:template match="/">
        <xsl:for-each select="autokDRPPXL/auto[not(preceding-sibling::auto/tulaj/varos = tulaj/varos)]">
            <xsl:variable name="varos" select="tulaj/varos"/>
            <xsl:variable name="autok" select="//auto[tulaj/varos = $varos]"/>
            <xsl:value-of select="$varos"/>
            <xsl:text>: </xsl:text>
            <xsl:value-of select="count($autok)"/>
            <xsl:text> db, összár: </xsl:text>
            <xsl:value-of select="sum($autok/ar)"/>
            <xsl:text>&#10;</xsl:text>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
