<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Table Details</title>

</head>
<body>


<div id="tableData">
	
	<% Map m=(Map)request.getAttribute("data") ;%>
	
	<% Set tables=m.keySet();
		
		Iterator iter=tables.iterator();
		
		
		
		while(iter.hasNext())
		{
			
	%><%String tableName=(String)iter.next();%>
	<h1><%=tableName %></h1></br>
	
	<% Map cols=(Map) m.get(tableName) ;
	
		int size=cols.size();
		Set colSet=cols.keySet();
		int inc=0;
		String columns[]=new String[colSet.size()];
		
		Iterator colIter=colSet.iterator();
		
		while(colIter.hasNext())
		{
			columns[inc]=(String)colIter.next();
			++inc;
		}
		inc=0;
		List columnValues[]=new ArrayList[colSet.size()];
		for(int i=0;i<columnValues.length;i++)
		{
			
			columnValues[i]=(List)cols.get(columns[i]);
		}
	
	%>
	
	<table border="1" >
	
		<tr>
		
		<%for(int k=0;k<columns.length;k++){ %>
		
			<th><%=columns[k] %></th>
		
		<%} %>
		
		</tr>
		
		
		
		<%if(columnValues[0]!=null)
			{
			
			for(int v=0;v<columnValues[0].size();v++){
		
		%>
		<tr>
		
		<%
				for(int z=0;z<columnValues.length;z++)
				{
		%>
		
				<td><%=columnValues[z].get(v) %></td>
		
		<%
				}
		%>
		
		</tr>
		
		<%
			}
			
			}%>
	
	</table>
			 
	<%
	}
	
	%>
</div>
</body>
</html>