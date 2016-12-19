<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,pagedemo.service.*,pagedemo.pojo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show users in pages</title>
<style type="text/css">
table {
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid black;
}
</style>
</head>
<body>
	test page!!
	<jsp:useBean id="dataUtils" scope="session"
		class="pagedemo.service.DataUtils" />
	<%  
	    String pageParam = request.getParameter("page");
	    if(null == pageParam)
	    {
	    	pageParam = "1";
	    }
		List<User> users = DataUtils.getUsers(Integer.valueOf(pageParam));
	%>
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>Sex</td>
			<td>Age</td>
			<td>Birthday</td>
			<td>Occupation</td>
			<td>Address</td>
			<td>Email</td>
			<td>CellPhone</td>
		</tr>
		<%
			if (null != users) {

				for (User u : users) {
					int id = u.getId();
					String name = u.getName();
					String sex = u.getSex();
					int age = u.getAge();
					Date birthday = u.getBirthday();
					String occupation = u.getOccupation();
					String address = u.getAddress();
					String email = u.getEmail();
					String cellPhone = u.getCellPhone();
		%>
		<tr>
			<td>
				<%
					out.println(id);
				%>
			</td>
			<td>
				<%
					out.println(name);
				%>
			</td>
			<td>
				<%
					out.println(sex);
				%>
			</td>
			<td>
				<%
					out.println(age);
				%>
			</td>
			<td>
				<%
					out.println(birthday);
				%>
			</td>
			<td>
				<%
					out.println(occupation);
				%>
			</td>
			<td>
				<%
					out.println(address);
				%>
			</td>
			<td>
				<%out.println(email); %>
			</td>
			<td>
				<%out.println(cellPhone); %>
			</td>
		</tr>
		<%	
}
}
%>
</table>
<p>
<%
int count = DataUtils.getUsersCount();
int pageSize = DataUtils.getPageSize();
int pageCount = DataUtils.getPage(count, pageSize);
for(int i=1; i<pageCount+1;i++)
{
	String url = "http://localhost:8082/pagedemo/?page="+i;
%>
<a href="<%out.print(url);%>"><%out.print(i);%></a>
<%
}
%>


</p>
</body>
</html>