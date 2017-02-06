
<!--
  ~ Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  -->

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h1>Orders</h1>
  <p>
  <font color="#872d2d" size="6">
  	Country :
  </font>
  
  
  <xsl:for-each select="Orders">
    	<xsl:value-of select="Country"/>
  </xsl:for-each>
  </p>
  
  <br></br>

  <h2>Companies</h2>
    
  <table border="1">
    <tr bgcolor="#9acd32">
      <th>ID</th>
      <th>Name</th>
    </tr>

    <xsl:for-each select="Orders/Companies/Company">
      <tr>
        <td><xsl:value-of select="@Company_ID"/></td>
        <td><xsl:value-of select="CompanyName"/></td>
      </tr>
    </xsl:for-each>
  </table>

  <h2>Employees</h2>

  <table border="1">
    <tr bgcolor="#9acd32">
      <th>Employee ID</th>
      <th>Company ID</th>
      <th>Name</th>
      <th>Title</th>
    </tr>

    <xsl:for-each select="Orders/Employees/Employee">
      <tr>
        <td><xsl:value-of select="@EmployeeID"/></td>
        <td><xsl:value-of select="CompanyID"/></td>
        <td><xsl:value-of select="EmployeeName"/></td>
        <td><xsl:value-of select="JobTitle"/></td>
      </tr>
    </xsl:for-each>
  </table>

  <br></br>

  <h2>Customer Details</h2>

  <table border="1">
    <tr bgcolor="#9acd32">
      <th>ID</th>
      <th>Name</th>
      <th>Phone</th>
      <th>Address</th>
      <th>City</th>
      <th>PostalCode</th>
      <th>Country</th>
    </tr>

    <xsl:for-each select="Orders/Customers/Customer">
      <tr>
        <td><xsl:value-of select="@CustomerID"/></td>
        <td><xsl:value-of select="Name"/></td>
        <td><xsl:value-of select="Phone"/></td>
        <td><xsl:value-of select="FullAddress/Address"/></td>
        <td><xsl:value-of select="FullAddress/City"/></td>
        <td><xsl:value-of select="FullAddress/PostalCode"/></td>
        <td><xsl:value-of select="FullAddress/Country"/></td>
      </tr>
    </xsl:for-each>
  </table>

  <br></br>

  <h2>Order Details</h2>

  <table border="1">
    <tr bgcolor="#9acd32">
      <th>Order ID</th>
      <th>Customer ID</th>
      <th>Employee ID</th>
      <th>Order Date</th>
      <th>Required Date</th>
      <th>Ship Info</th>
    </tr>

    <xsl:for-each select="Orders/AvailableOrders/Order">
      <tr>
        <td><xsl:value-of select="@OrderID"/></td>
        <td><xsl:value-of select="CustomerID"/></td>
        <td><xsl:value-of select="EmployeeID"/></td>
        <td><xsl:value-of select="OrderDate"/></td>
        <td><xsl:value-of select="RequiredDate"/></td>
        <td><xsl:value-of select="ShipInfo/CompanyID"/></td>
      </tr>
    </xsl:for-each>
  </table>

  </body>
  </html>

</xsl:template>

</xsl:stylesheet>