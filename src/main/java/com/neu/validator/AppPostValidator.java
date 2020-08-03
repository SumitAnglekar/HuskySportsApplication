package com.neu.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.pojo.AppPost;
import com.neu.pojo.User;

public class AppPostValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(AppPost.class);
	}

	public void validate(Object obj, Errors errors) {
		AppPost appPost=(AppPost) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "appID", "error.invalid.appPost", "App ID Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "appTitle", "error.invalid.appPost", "App Title Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teamName", "error.invalid.appPost", "Team Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "campus", "error.invalid.appPost", "Campus Location Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.appPost", "Description Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "playingPosition", "error.invalid.appPost", "Playing Position Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "noOfPosition", "error.invalid.appPost", "Position Required");
	}
}
