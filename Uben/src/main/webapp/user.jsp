<!DOCTYPE html>
	<html>
		<head>
			<title>Spring Boot</title>
		</head>
		<body>
			<table>
				<thead>
					<th>Id</th>
					<th>Name</th>
					<th>Age</th>
					<th>Course</th>
				</thead>
				<tbody id="table_body">
				</tbody>
			</table>
		</body>
		<script>
		        document.addEventListener("DOMContentLoaded", function() {
		            // Ajax request to fetch users data
		            var xhr = new XMLHttpRequest();
		            xhr.open("GET", "/showUser");
		            xhr.onload = function() {
		                if (xhr.status === 200) {
		                    var users = JSON.parse(xhr.responseText);
		                    populateTable(users);
		                } else {
		                    console.error("Error fetching users: " + xhr.status);
		                }
		            };
		            xhr.send();
		        });

		        // Function to populate the table with users data
		        function populateTable(users) {
		            var tableBody = document.getElementById("table_body");
		            tableBody.innerHTML = ''; // Clear existing rows

		            users.forEach(function(user) {
		                var html = '<tr>' +
		                           '<td>' + user.id + '</td>' +
		                           '<td>' + user.name + '</td>' +
		                           '<td>' + user.age + '</td>' +
		                           '<td>' + user.course + '</td>' +
		                           '</tr>';
		                tableBody.innerHTML += html;
		            });
		        }
		    </script>
	</html>