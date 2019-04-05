var testTable= [
	    { reimb_id: 1, reimb_amount: 300, reimb_submitted: "2019-04-02 20:49:15.0", reimb_resolved: null, reimb_description: "Hello", ERS_Users_id: 2, resolver: 4, status: 2, type: 3 },
	    { reimb_id: 1, reimb_amount: 300, reimb_submitted: "2019-04-02 20:49:15.0", reimb_resolved: "2019-04-02 20:49:15.0", reimb_description: "Hello", ERS_Users_id: 2, resolver: 4, status: 2, type: 3 }
	];
var reimbList;
var user;
window.onload = function() {
	
	console.log("app loading");
	loadLandingView();
}
// ---Load Landing View---
function loadLandingView() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log('LOADING RESPONSE RECIEVED');
			$('#view').html(xhr.responseText);
			console.log(user);
			if(user != null){
				$('#username_nav').html(user.username);
			}
			console.log(xhr.responseText);
			//===Remove Prev. Listeners=== //To prevent duplicates
			$('#title').off('click');
			$('#home').off('click');
			$('#login').off('click');
			$('#submitReimb').off('click');
			// --Add event listeners--
			$('#title').on('click', loadLandingView);
			$('#home').on('click', loadLandingView);
			$('#login').on('click', loadLoginView);
			$('#submitReimb').on('click', loadReimbSubmitView);
		}
	}
	xhr.open("GET", "landing.view");
	xhr.send();
}
// ===Load Login Page===
function loadLoginView() {
	console.log("Login Clicked");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("MOVING TO LOGIN PAGE");
			$('#view').html(xhr.responseText);
			$('#password').on('keydown', function(e) {
				if (e.which == 13) {
					loginUser();
				}
			});
			$('#submit_btn').off('click');//remove previous if there
			$('#submit_btn').on('click', loginUser);
		}
	}
	xhr.open("GET", "Login.view");
	xhr.send();
	console.log("SENT LOGIN REQ");
}
// load reimb submit
function loadReimbSubmitView() {
	//checkReimb();
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log('Moving to submit reimb page');
			$('#view').html(xhr.responseText);
			$('#submitReimb_btn').off('click');
			$('#submitReimb_btn').on('click', submitReimb);
			checkReimb();
		}
	}
	xhr.open("GET", "SubmitReimb.view");
	xhr.send();
	console.log("REQ SUBMIT REIMB VIEW");
}

// String check
function validString(str) {
	if (str == null || str == '') {
		return false
	} else
		return true;
}
// Login
function loginUser() {
	console.log("ATTEMTPING TO SIGN IN");
	var username = $('#username').val();
	var password = $('#password').val();

	if (validString(username) && validString(password)) {
		user = {
			username : username,
			password : password
		};

		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				console.log('LOGIN RESPONSE RECIEVED');
				var loggedUser = JSON.parse(xhr.responseText); // get user
				console.log(xhr.getAllResponseHeaders());
				console.log("this is: " +loggedUser.username+"Password: "+ loggedUser.password+ "Id: " + loggedUser.id);
				if (user == null) { // login fail
					console.log('LOGIN FAIL');
					$('#error_message')
							.html(
									"Could not log you in, please check your credentials");
				} else {
					if(loggedUser.id == 1){
						console.log("WELCOME, ADMIN");
						adminReimbView();
					}
					
					else if(loggedUser.id == 2){
					console.log('Success! NORMAL USER: TO SUBMITREIMB');
					checkReimb();
					}
				}
			}
		}
		xhr.open("POST", "Login");
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.send(JSON.stringify(user));
	} else {
		// User has not put in anything for username and password
		$('#error_message')
				.html(
						"<p class = 'text-warning'>Please enter a username and password!</p>");
	}
}
//==Admin Page==
function adminReimbView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("RECIEVED ADMIN VIEW RESP");
			$('#view').html(xhr.responseText);
			getAllReimb();
		}
	}
	xhr.open("GET", "AdminReimb.view");
	xhr.send();
	console.log("SENT REQ FOR ADMINREIMB");
}
// ===See User's reimb===
function checkReimb() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("GOT CHECKREIMB RESP");
			reimbList = JSON.parse(xhr.responseText);
			console.log(reimbList);
			if(reimbList != null){
			
			$('#table_id').DataTable({
				data : reimbList,
				columns : [ 
					{data : 'reimb_id'}, 
					{data : 'reimb_amount'}, 
					{data : 'reimb_submitted'},
					{data: 'reimb_resolved'},
					{data : 'reimb_description'},
					{data: 'resolver'},
					{data: 'status'},
					{data: 'type'},
					{data: 'ers_Users_id'}		//Case sensitive!!!
				]
			});
			}
		}
	}
	xhr.open("GET", "SubmitReimb");
	xhr.send();
	console.log("sent a req to submitSevlet");
}
//==Admin reimb get==
function getAllReimb() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("GOT CHECKREIMB RESP");
			reimbList = JSON.parse(xhr.responseText);
			console.log(reimbList);
			if(reimbList != null){
			
			$('#table_id').DataTable({
				data : reimbList,
				columns : [ 
					{data : 'reimb_id'}, 
					{data : 'reimb_amount'}, 
					{data : 'reimb_submitted'},
					{data: 'reimb_resolved'},
					{data : 'reimb_description'},
					{data: 'resolver'},
					{data: 'status'},
					{data: 'type'},
					{data: 'ers_Users_id'}		//Case sensitive!!!
				]
			});
			}
		}
	}
	xhr.open("GET", "AdminReimb");
	xhr.send();
	console.log("sent a req to submitSevlet");
}
// ===submit reimbursement===
function submitReimb() {
	
	var amount = $('#amount').val();
	//var submitted = $('#date').val(); // should make this a trigger
	//var author = $('#username').val(); // Should also be trigger?
	var type = $('#reimb_type').val(); // need to turn into an id. Maybe make a
	var description = $('#description').val();			// dropdown? Do case statements?
	console.log("This is the submission: " + amount +" "+type+" "+description);
	
	if (validString(amount) && validString(description)
			&& validString(type)) {
		var reimbursement = {
			reimb_amount : amount,
			reimb_description: description,
			type : type
		};

		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState = 4 && xhr.status == 200) {
				console.log('SUBMITTED');

			}
		}
		xhr.open("POST", "SubmitReimb");
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.send(JSON.stringify(reimbursement));
	} else
		console.log('Please fill out all forms');
}
