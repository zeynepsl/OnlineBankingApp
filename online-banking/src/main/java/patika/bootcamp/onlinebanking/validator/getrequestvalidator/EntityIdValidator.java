package patika.bootcamp.onlinebanking.validator.getrequestvalidator;

import org.springframework.stereotype.Component;

import patika.bootcamp.onlinebanking.exception.BaseException;
import patika.bootcamp.onlinebanking.exception.ControllerOperationException;
import patika.bootcamp.onlinebanking.validator.Validator;

@Component
public class EntityIdValidator implements Validator<Long>{

	@Override
	public void validate(Long id) throws BaseException {
		if(id < 0) {
    		throw new ControllerOperationException.IDNotValidException("id is not valid");
    	}
	}

}
