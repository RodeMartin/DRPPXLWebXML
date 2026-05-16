<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="UTF-8"/>
    <xsl:template match="/">
        <xsl:for-each select="autokDRPPXL/auto[not(preceding-sibling::auto/tipus = tipus)]">
            <xsl:sort select="count(//auto[tipus = current()/tipus])" data-type="number" order="descending"/>
            <xsl:value-of select="tipus"/>
            <xsl:text>: </xsl:text>
            <xsl:value-of select="count(//auto[tipus = current()/tipus])"/>
            <xsl:text>&#10;</xsl:text>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
