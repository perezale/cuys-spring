package ar.com.cuys.webapp.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.cuys.webapp.repository.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UniqueUsername constraintAnnotation) {		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(userRepository == null) return true;
		return userRepository.findByNameIgnoreCase(value) == null;		
	}

}
