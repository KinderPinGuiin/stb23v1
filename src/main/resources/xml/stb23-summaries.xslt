<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="http://univrouen.fr/stb23" version="3.0">

    <xsl:output method="html" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
                omit-xml-declaration="yes" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/stb-summaries">
        <xsl:element name="html">
            <xsl:element name="head">
                <xsl:element name="title">STB summaries</xsl:element>
            </xsl:element>
            <xsl:element name="body">
                <xsl:element name="table">
                    <xsl:element name="tr">
                        <xsl:element name="th">
                            <xsl:text>Title</xsl:text>
                        </xsl:element>
                        <xsl:element name="th">
                            <xsl:text>Description</xsl:text>
                        </xsl:element>
                        <xsl:element name="th">
                            <xsl:text>Validation date</xsl:text>
                        </xsl:element>
                        <xsl:element name="th">
                            <xsl:text>Client (entity)</xsl:text>
                        </xsl:element>
                    </xsl:element>
                    <xsl:for-each select="summary">
                        <xsl:element name="tr">
                            <xsl:element name="td">
                                <xsl:value-of select="title" />
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:value-of select="description" />
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:value-of select="validation-date" />
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:value-of select="client-entity" />
                            </xsl:element>
                        </xsl:element>
                    </xsl:for-each>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>