<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Tariff</h2>
                <xsl:apply-templates select="tariff/mobile_tariff">

                    <!--- Sort tariffs by name. -->

                    <xsl:sort select="name"/>
                </xsl:apply-templates>
            </body>
        </html>
    </xsl:template>


    <xsl:template match="tariff/mobile_tariff">
        <p>
            <xsl:apply-templates select="name"/>
            <xsl:apply-templates select="operatorName"/>
            <xsl:apply-templates select="payroll"/>
            <xsl:apply-templates select="priceSMS"/>
            <xsl:apply-templates select="callprices"/>
            <xsl:apply-templates select="parameters"/>
        </p>
    </xsl:template>

    <xsl:template match="tariff/mobile_tariff/callprices">
        <p>
            <xsl:apply-templates select="insideCallPrice"/>
            <xsl:apply-templates select="outsideCallPrice"/>
            <xsl:apply-templates select="stationaryCallPrice"/>
        </p>
    </xsl:template>

    <xsl:template match="tariff/mobile_tariff/parameters">
        <p>
            <xsl:apply-templates select="hadFavouriteNumber"/>
            <xsl:apply-templates select="tariffication"/>
            <xsl:apply-templates select="connectionPrice"/>
        </p>
    </xsl:template>
</xsl:stylesheet>