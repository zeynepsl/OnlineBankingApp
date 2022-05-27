package patika.bootcamp.onlinebanking.core.service;

import java.util.List;

import patika.bootcamp.onlinebanking.exception.BaseException;

public interface BaseService<T> {
	T create(T t) throws BaseException;
	T get(Long id) throws BaseException;
	T update(T t);
	void delete(Long id) throws BaseException;
	List<T> getAll();
}
