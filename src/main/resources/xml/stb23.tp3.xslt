<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:p="http://univrouen.fr/stb23" version="1.0">
	
	<xsl:output method="html" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" 
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
		omit-xml-declaration="yes" encoding="UTF-8" indent="yes"/>
		
	<xsl:template match="/p:stb">
		<xsl:element name="html">
			<xsl:element name="head">
				<xsl:element name="title">XML - TP3</xsl:element>
			</xsl:element>
			<xsl:element name="body">
				<xsl:element name="h1">
					<xsl:text>STB23 - XSLT V1.0</xsl:text>
				</xsl:element>
				<xsl:element name="p">
					<xsl:text>Elie Jordan (Le 1er Mars 2023)</xsl:text>
				</xsl:element>
				<xsl:element name="h2">
					<xsl:value-of select="title" />
				</xsl:element>
				<xsl:element name="p">
					<xsl:text>( V</xsl:text>
					<xsl:value-of select="version" />
					<xsl:text> - </xsl:text>
					<xsl:value-of select="date" />
					<xsl:text> )</xsl:text>
				</xsl:element>
				<xsl:element name="p">
					<xsl:value-of select="description" />
				</xsl:element>
				<xsl:element name="h2">
					<xsl:text>Client : </xsl:text>
					<xsl:value-of select="client/person" />
					<xsl:text> </xsl:text>
					<xsl:value-of select="client/person/@lastname" />
				</xsl:element>
				<xsl:element name="p">
					<xsl:text>Contact : </xsl:text>
					<xsl:value-of select="client/entity" />
					<xsl:text> - tel : </xsl:text>
					<xsl:value-of select="client/tel" />
				</xsl:element>
				<xsl:element name="h2">
					<xsl:text>Equipe</xsl:text>
				</xsl:element>
				<xsl:element name="ul">
					<xsl:for-each select="team/member">
						<xsl:element name="li">
							<xsl:value-of select="@gender" />
							<xsl:text> </xsl:text>
							<xsl:value-of select="person" />
							<xsl:text> </xsl:text>
							<xsl:value-of select="person/@lastname" />
							<xsl:text> : </xsl:text>
							<xsl:value-of select="function" />
							<xsl:text> (</xsl:text>
							<xsl:value-of select="mail" />
							<xsl:text>)</xsl:text>
						</xsl:element>
					</xsl:for-each>
				</xsl:element>
				<xsl:element name="h2">
					<xsl:text>Liste des fonctionnalités</xsl:text>
				</xsl:element>
				<xsl:element name="table">
					<xsl:for-each select="features/feature">
						<xsl:element name="tr">
							<xsl:element name="td">
								<xsl:text>item = </xsl:text>
								<xsl:value-of select="@section" />
								<xsl:text>.</xsl:text>
								<xsl:value-of select="@number" />
							</xsl:element>
							<xsl:element name="td">
								<xsl:value-of select="@name" />
							</xsl:element>
						</xsl:element>
						<xsl:element name="tr">
							<xsl:element name="td">
								<xsl:text>priorité = </xsl:text>
								<xsl:value-of select="priority" />
							</xsl:element>
							<xsl:element name="td">
								<xsl:text>Livraison : </xsl:text>
								<xsl:value-of select="delivery" />
							</xsl:element>
						</xsl:element>
						<xsl:element name="tr">
							<xsl:element name="td">
								<xsl:element name="p">
									<xsl:value-of select="description" />
								</xsl:element>
								<xsl:element name="p">
									<xsl:value-of select="comment" />
								</xsl:element>
							</xsl:element>
						</xsl:element>
						<xsl:element name="tr"></xsl:element>
					</xsl:for-each>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	
</xsl:stylesheet>