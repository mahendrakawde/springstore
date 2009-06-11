<xsl:stylesheet version='1.0'
    xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
    xmlns:bpi='http://java.sun.com/blueprints/ns/invoice'>
     <xsl:template match="text()"/>
     <xsl:template match="@*"/>
     <xsl:template match="bpi:Invoice">
      <bpi:Invoice xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://java.sun.com/blueprints/ns/invoice  http://java.sun.com/blueprints/schemas/invoice.xsd">
       <xsl:apply-templates/>
      </bpi:Invoice>
     </xsl:template>

     <xsl:template match="bpi:InvoiceRef">
           <bpi:InvoiceId><xsl:value-of select="text()"/></bpi:InvoiceId>
     </xsl:template>

     <xsl:template match="bpi:OPCPoId">
           <bpi:OPCPoId><xsl:value-of select="text()"/></bpi:OPCPoId>
     </xsl:template>

     <xsl:template match="bpi:SupplierId">
           <bpi:SupplierId><xsl:value-of select="text()"/></bpi:SupplierId>
     </xsl:template>

     <xsl:template match="bpi:Status">
           <bpi:status><xsl:value-of select="text()"/></bpi:status>
     </xsl:template>
</xsl:stylesheet>
