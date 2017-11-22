package br.com.testeapi.dao;

import java.util.List;

public interface Dao<T> {

	public Boolean delete(T t);
	public T findById(Long id);
	public List<T> listAll(T t);
	public Boolean update(T t);
	public Boolean save(T t);
	
}
