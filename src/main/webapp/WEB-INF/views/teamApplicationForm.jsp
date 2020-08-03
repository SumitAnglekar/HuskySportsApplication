<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype HTML>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
</head>

<body>
	<div class="container">

		<div class="alert alert-info" role="alert">
			<h4 class="text-center">
				<span class="glyphicon glyphicon-time" aria-hidden="true"></span> TA
				Application Form
			</h4>
		</div>

		<div class="row">
			<form:form commandName="applicant" enctype="multipart/form-data"
				class="form-horizontal">
				<div class="form-group">
					<label for="first" class="col-sm-4 control-label">First
						Name: </label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="first"
							path="first" name="first" placeholder="First Name" required="required" />
					</div>
				</div>

				<div class="form-group">
					<label for="last" class="col-sm-4 control-label">Last Name:</label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="last" name="last" path="last"
							placeholder="Last Name" required="required" />
					</div>
				</div>

				<div class="form-group">
					<label for="email" class="col-sm-4 control-label">Email:</label>
					<div class="col-sm-8">
						<c:choose>
							<c:when test="${requestScope.action=='save'}">
								<form:input type="text" class="form-control" path="email"
									id="email" placeholder="Your Husky e-mail Address." required="required" />
							</c:when>

							<c:when test="${requestScope.action=='update'}">
								<form:input type="text" class="form-control" path="email"
									id="email" placeholder="Your Husky e-mail Address."
									disabled="true" />
							</c:when>
						</c:choose>

					</div>
				</div>

				<div class="form-group">
					<label for="nuid" class="col-sm-4 control-label">NUID: </label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="nuid" path="nuid"
							placeholder="Your Northeastern University ID" required="required" />
					</div>
				</div>

				<div class="form-group">
					<label for="playingposition" class="col-sm-4 control-label">Playing Position: </label>
					<div class="col-sm-8">
						<form:input type="text" class="form-control" id="playingposition" path="playingposition" placeholder="Mention Relevant Playing position" required="required" />
					</div>
				</div>

				<div class="form-group">
					<label for="major" class="col-sm-4 control-label">Major: </label>
					<div class="col-sm-8">
						<form:select path="major" id="major" class="form-control">
							<form:option value="MSIS-Boston">MSIS Boston</form:option>
							<form:option value="CSYE-Boston">CSYE Boston</form:option>
							<form:option value="MSIS-Seattle">MSIS Seattle</form:option>
							<form:option value="MSIS-SV">MSIS Silicon Valley</form:option>
							<form:option value="IOT">IoT</form:option>
							<form:option value="TSM">TSM</form:option>
						</form:select>
					</div>
				</div>

				<%-- <div class="form-group">
					<label for="entrance" class="col-sm-4 control-label">Entrance Date: </label>
					<div class="col-sm-8">
						<form:select path="entrance" id="entrance" class="form-control">
							<form:option value="Fall 2014">Fall 2014</form:option>
							<form:option value="Spring 2015">Spring 2015</form:option>
							<form:option value="Summer 2015">Summer 2015</form:option>

							<form:option value="Fall 2015">Fall 2015</form:option>
							<form:option value="Spring 2016">Spring 2016</form:option>
							<form:option value="Summer 2016">Summer 2016</form:option>

							<form:option value="Fall 2016">Fall 2016</form:option>
							<form:option value="Spring 2017">Spring 2017</form:option>
							<form:option value="Summer 2017">Summer 2017</form:option>

							<form:option value="Fall 2017">Fall 2017</form:option>
							<form:option value="Spring 2018">Spring 2018</form:option>
							<form:option value="Summer 2018">Summer 2018</form:option>
							
							<form:option value="Fall 2018">Fall 2018</form:option>
						</form:select>
					</div>
				</div> --%>

				<div class="form-group">
					<label for="graduation" class="col-sm-4 control-label">Expected
						Graduation Date: </label>
					<div class="col-sm-8">
						<form:select path="graduation" id="graduation"
							class="form-control">
							<form:option value="Fall 2017">Fall 2017</form:option>
							<form:option value="Spring 2018">Spring 2018</form:option>
							<form:option value="Summer 2018">Summer 2018</form:option>

							<form:option value="Fall 2018">Fall 2018</form:option>
							<form:option value="Spring 2019">Spring 2019</form:option>
							<form:option value="Summer 2019">Summer 2019</form:option>

							<form:option value="Fall 2019">Fall 2019</form:option>
							<form:option value="Spring 2020">Spring 2020</form:option>
							<form:option value="Summer 2020">Summer 2020</form:option>
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="aboutme" class="col-sm-4 control-label">About
						Me: </label>
					<div class="col-sm-8">
						<form:textarea class="form-control" id="aboutme" path="aboutme"
							rows="3" placeholder="A few lines about you"></form:textarea>
					</div>
				</div>

				<div class="form-group">
					<label for="skills" class="col-sm-4 control-label">Skills:</label>
					<div class="col-sm-8">
						<form:textarea class="form-control" id="skills" path="skills"
							rows="3"
							placeholder="A short summary of your skills and background"></form:textarea>
					</div>
				</div>

			

	

				<div class="form-group">
					<label for="whichclass" class="col-sm-4 control-label">TA
						for Which Class?: <br />Enter 1 class per line
					</label>
					<div class="col-sm-8">
						<form:textarea class="form-control" id="whichclass"
							path="whichclass" rows="3"
							placeholder="List of courses for which you want to be considered as a TA - Enter 1 class per line"></form:textarea>
					</div>
				</div>

				<div class="form-group">
					<label for="resume" class="col-sm-4 control-label">Resume:
					</label>
					<div class="col-sm-4">
						<input type="file" id="resume" name="resume" required="required" />
						<p class="help-block">Recent resume in PDF format</p>
					</div>
					<c:if test="${requestScope.action=='update'}">
						<div class="alert alert-info" role="alert">
							<h4 class="text-center">
								<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
								RESUME HAVE BEEN SAVED.<br/>UPLOAD A NEW ONE TO UPDATE.
							</h4>
						</div>
					</c:if>
				</div>

				<div class="form-group">
					<label for="photo" class="col-sm-4 control-label">Photo: </label>
					<div class="col-sm-4">
						<input type="file" id="photo" name="photo" required="required" />
						<p class="help-block">Recent photo in JPG format</p>
					</div>
					<c:if test="${requestScope.action=='update'}">
						<div class="col-sm-4">
							<a href="/ta_applications/${requestScope.nuid}.jpg"
								target="_blank"><img
								src="/ta_applications/${requestScope.nuid}.jpg" alt="My Photo"
								class="img-thumbnail"></a>
						</div>
					</c:if>
				</div>

				<div class="form-group">
					<c:choose>
						<c:when test="${requestScope.action=='save'}">
							<div class="col-sm-offset-4 col-sm-8">
								<input type="submit" name="saveorupdate" class="btn btn-primary"
									value="Save My Information" />
							</div>
						</c:when>

						<c:when test="${requestScope.action=='update'}">
							<div class="col-sm-offset-4 col-sm-8">
								<form:hidden path="authkey1" />
								<form:hidden path="authkey2" />
								<input type="submit" name="saveorupdate" class="btn btn-success"
									value="Update My Information" />
							</div>
						</c:when>
					</c:choose>
				</div>
			</form:form>
		</div>
	</div>

	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
</body>
</html>