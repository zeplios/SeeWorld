package edu.tju.ina.seeworld.logic;

import edu.tju.ina.seeworld.dao.resource.IDirectorDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Director;

public interface IDirectorLogic {

	public void save(Director director) throws SeeWorldException;

	public void delete(Director director) throws SeeWorldException;

	public void update(Director director) throws SeeWorldException;

}
