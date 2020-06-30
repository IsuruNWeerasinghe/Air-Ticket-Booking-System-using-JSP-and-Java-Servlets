<%-- 
    Document   : Welcome
    Created on : Jun 26, 2020, 12:14:12 PM
    Author     : isuru
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>

<%
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to the Online Aircraft Booking</title>
        <style>
            @import url(https://fonts.googleapis.com/css?family=Roboto:400,300,600,400italic);
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                -webkit-font-smoothing: antialiased;
                -moz-font-smoothing: antialiased;
                -o-font-smoothing: antialiased;
                font-smoothing: antialiased;
                text-rendering: optimizeLegibility;
            }

            body {
                font-family: "Roboto", Helvetica, Arial, sans-serif;
                font-weight: 100;
                font-size: 12px;
                line-height: 30px;
                color: #777;
                background: #1167b1;
            }

            .container {
                max-width: 350px;
                width: 100%;
                margin: 0 auto;
                position: relative;
            }

            #contact input[type="text"],
            #contact input[type="text"],
            #contact input[type="text"],
            #contact input[type="datetime-local"],
            #contact input[type="datetime-local"],
            #contact input[type="text"],
            #contact input[type="number"],
            #contact input[type="number"],

            #contact button[type="submit"] {
                font: 400 12px/16px "Roboto", Helvetica, Arial, sans-serif;
            }

            #contact {
                background: #F9F9F9;
                padding: 25px;
                margin: 100px 0;
                box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
            }

            #contact h3 {
                display: block;
                font-size: 30px;
                font-weight: 300;
                margin-bottom: 10px;
            }

            #contact h4 {
                margin: 5px 0 15px;
                display: block;
                font-size: 13px;
                font-weight: 400;
            }

            fieldset {
                border: medium none !important;
                margin: 0 0 10px;
                min-width: 100%;
                padding: 0;
                width: 100%;
            }

            #contact input[type="text"],
            #contact input[type="text"],
            #contact input[type="text"],
            #contact input[type="datetime-local"],
            #contact input[type="datetime-local"],
            #contact input[type="text"],
            #contact input[type="number"],
            #contact input[type="number"]{
                width: 100%;
                border: 1px solid #ccc;
                background: #FFF;
                margin: 0 0 5px;
                padding: 10px;
            }

            #contact input[type="text"]:hover,
            #contact input[type="text"]:hover,
            #contact input[type="text"]:hover,
            #contact input[type="datetime-local"]:hover,
            #contact input[type="datetime-local"]:hover,
            #contact input[type="text"]:hover,
            #contact input[type="number"]:hover,
            #contact input[type="number"]:hover {
                -webkit-transition: border-color 0.3s ease-in-out;
                -moz-transition: border-color 0.3s ease-in-out;
                transition: border-color 0.3s ease-in-out;
                border: 1px solid #aaa;
            }

            #contact textarea {
                height: 100px;
                max-width: 100%;
                resize: none;
            }

            #contact button[type="submit"] {
                cursor: pointer;
                width: 100%;
                border: none;
                background: #1167b1;
                color: #FFF;
                margin: 0 0 5px;
                padding: 10px;
                font-size: 15px;
            }

            #contact button[type="submit"]:hover {
                background: #7ea9ff;
                -webkit-transition: background 0.3s ease-in-out;
                -moz-transition: background 0.3s ease-in-out;
                transition: background-color 0.3s ease-in-out;
            }

            #contact button[type="submit"]:active {
                box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
            }


            #contact input:focus,
            #contact textarea:focus {
                outline: 0;
                border: 1px solid #aaa;
            }

            ::-webkit-input-placeholder {
                color: #888;
            }

            :-moz-placeholder {
                color: #888;
            }

            ::-moz-placeholder {
                color: #888;
            }

            :-ms-input-placeholder {
                color: #888;
            }
            .column1 {
                float: left;
                width: 70%;
            }
            .column {
                float: left;
                width: 30%;
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }

            strong {
                font-weight: bold; 
            }

            em {
                font-style: italic; 
            }

            table {
                background: #f5f5f5;
                border-collapse: separate;
                box-shadow: inset 0 1px 0 #fff;
                font-size: 12px;
                line-height: 24px;
                margin: 30px auto;
                text-align: left;
                width: 1100px;
            }	

            th {
                background: url(https://jackrugile.com/images/misc/noise-diagonal.png), linear-gradient(#777, #444);
                border-left: 1px solid #555;
                border-right: 1px solid #777;
                border-top: 1px solid #555;
                border-bottom: 1px solid #333;
                box-shadow: inset 0 1px 0 #999;
                color: #fff;
                font-weight: bold;
                padding: 10px 15px;
                position: relative;
                text-shadow: 0 1px 0 #000;	
            }

            th:after {
                background: linear-gradient(rgba(255,255,255,0), rgba(255,255,255,.08));
                content: '';
                display: block;
                height: 25%;
                left: 0;
                margin: 1px 0 0 0;
                position: absolute;
                top: 25%;
                width: 100%;
            }

            th:first-child {
                border-left: 1px solid #777;	
                box-shadow: inset 1px 1px 0 #999;
            }

            th:last-child {
                box-shadow: inset -1px 1px 0 #999;
            }

            td {
                border-right: 1px solid #fff;
                border-left: 1px solid #e8e8e8;
                border-top: 1px solid #fff;
                border-bottom: 1px solid #e8e8e8;
                padding: 10px 15px;
                position: relative;
                transition: all 300ms;
            }

            td:first-child {
                box-shadow: inset 1px 0 0 #fff;
            }	

            td:last-child {
                border-right: 1px solid #e8e8e8;
                box-shadow: inset -1px 0 0 #fff;
            }	

            tr {
                background: url(https://jackrugile.com/images/misc/noise-diagonal.png);	
            }

            tr:nth-child(odd) td {
                background: #f1f1f1 url(https://jackrugile.com/images/misc/noise-diagonal.png);	
            }

            tr:last-of-type td {
                box-shadow: inset 0 -1px 0 #fff; 
            }

            tr:last-of-type td:first-child {
                box-shadow: inset 1px -1px 0 #fff;
            }	

            tr:last-of-type td:last-child {
                box-shadow: inset -1px -1px 0 #fff;
            }	

            tbody:hover td {
                color: transparent;
                text-shadow: 0 0 3px #aaa;
            }

            tbody:hover tr:hover td {
                color: #444;
                text-shadow: 0 1px 0 #fff;
            }
            .topic-container {
                max-width: 4000px;
                padding-top: 8px;
                padding-bottom: 10px;
                background: url(https://jackrugile.com/images/misc/noise-diagonal.png), linear-gradient(#777, #444);

            }
            .maintopic {
                font-family: "Arial Black", Gadget, sans-serif0;
                font-size: 30px;
                font-weight: bold;
                color: whitesmoke;
                vertical-align: middle;
                display: inline-block;
            }
            .layoutback{
                margin-top: 20px;
                margin-bottom: 30px;
                margin-left: 30px;
                margin-right: 30px;
                padding-top: 15px; 
                border-radius: 20px;
                background-color: rgba(255, 255, 255, .3);
            }
            .tableheading{
                font-family: "Arial Black", Gadget, sans-serif0;
                font-size: 30px;
                font-weight: bold;
                color: whitesmoke;
            }
            .colu1 {
                float: left;
                width: 90%;
            }
            .colu2 {
                float: left;
                width: 10%;
            }

            /* Clear floats after the columns */
            .row1:after {
                content: "";
                display: table;
                clear: both;
            }
            .signoutbtn button[type="submit"]{
                cursor: pointer;
                width: 80%;
                border: none;
                background: #1167b1;
                color: #FFF;
                margin: 0 0 2px;
                padding: 2px;
                font-size: 20px;
                border-radius: 5px;
            }
        </style>

    </head>
    <body>
        <div class="topic-container" style="white-space:nowrap">
            <div class="row1">
                <div class="colu1">
                    <img src="Images/logo2-1.png" alt="logo" width="100" height="50">
                    <p class="maintopic">Smart Air Titcket Reservation</p>
                    <img src="Images/logo2.png" alt="logo" width="100" height="50">
                </div>
                <div class="colu2">
                    <form action="SignoutAdmin" method="POST" class="signoutbtn">
                        <button name="submit" type="submit" value="SignoutAdmin" id="contact-submit">Sign Out</button>
                    </form>
                </div>
            </div>
        </div>
        <br><br>
        
        <div class="row">
            <div class="column1">
                <div class = "layoutback">
                    <div class="contact"><center><p class="tableheading">Current Flights</p></center></div>
                    <table>
                        <thead>
                            <tr>
                                <th>Aircraft Reg No</th>
                                <th>Aircraft Name</th>
                                <th>Source</th>
                                <th>Destination</th>
                                <th>Depature Time</th>
                                <th>Arrival Time</th>
                                <th>Flight Class</th>
                                <th>Flight Charge</th>
                                <th>Seates</th>

                            </tr>
                        </thead>
                        <%
                            try {
                                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");

                                statement = connection.createStatement();
                                String sql = "select * from FLIGHTS";
                                resultSet = statement.executeQuery(sql);
                                int i = 0;
                                while (resultSet.next()) {
                        %>

                        <tbody
                            <tr>
                        <td><%=resultSet.getString("AIRREGNO")%></td>
                        <td><%=resultSet.getString("AIRNAME")%></td>
                        <td><%=resultSet.getString("AIRSOURCE")%></td>
                        <td><%=resultSet.getString("AIRDESTINATION")%></td>
                        <td><%=resultSet.getString("AIRDEPATURE")%></td>
                        <td><%=resultSet.getString("AIRARRIVAL")%></td>
                        <td><%=resultSet.getString("AIRCLASS")%></td>
                        <td><%=resultSet.getString("AIRCHARGE")%></td>
                        <td><%=resultSet.getString("AIRSEATES")%></td>

                        </tr>

                        <%
                                    i++;
                                }
                                connection.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                        </tbody>
                    </table>
                    <br><br>
                </div>
            </div>

            <div class="column">
                <div class="container">  
                    <form id="contact" action="BookSeates" method="GET">
                        <h3>Book Tickets</h3><br>
                        <fieldset><input placeholder="Customer Name" type="text" name="bookname" id="bookname" tabindex="1" required></fieldset>
                        <fieldset><input placeholder="Reg No of Flight" type="number" name="bookregno" id="bookregno" tabindex="2" required></fieldset>
                        <fieldset><input placeholder="Num of Seates Booking" type="number" name="bookseates" id="bookseates" tabindex="3" required></fieldset>

                        <fieldset><button name="submit" type="submit" value="BookSeates" id="contact-submit" data-submit="...Sending">Confirm Booking</button></fieldset>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
