<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">

        <html>
            <head>
                <title>Ródé Martin Órarend – 2026. II. félév</title>
                <style>
                    table { border-collapse: collapse; width: 100%; font-family: Arial, sans-serif; }
                    th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }
                    tr:nth-child(even) { background-color: #f2f2f2; }
                    th { background-color: #4CAF50; color: white; }
                    .tipus { font-style: italic; }
                </style>
            </head>
            <body>
                <h2>Ródé Martin Órarend – 2026. II. félév</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Típus</th>
                        <th>Kurzus</th>
                        <th>Nap</th>
                        <th>Időpont</th>
                        <th>Helyszín</th>
                        <th>Oktató</th>
                        <th>Szak</th>
                    </tr>
                    <xsl:for-each select="orarend/ora">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td class="tipus"><xsl:value-of select="@tipus"/></td>
                            <td><strong><xsl:value-of select="kurzus"/></strong></td>
                            <td><xsl:value-of select="Idopont/nap"/></td>
                            <td><xsl:value-of select="Idopont/tol"/> - <xsl:value-of select="Idopont/ig"/></td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>