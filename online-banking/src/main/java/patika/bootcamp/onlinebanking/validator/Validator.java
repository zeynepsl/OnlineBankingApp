package patika.bootcamp.onlinebanking.validator;

import patika.bootcamp.onlinebanking.exception.BaseException;

public interface Validator<T> {
	void validate(T input) throws BaseException;
}
