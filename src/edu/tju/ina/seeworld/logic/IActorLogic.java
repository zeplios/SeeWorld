package edu.tju.ina.seeworld.logic;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Actor;

public interface IActorLogic {
	public void save(Actor actor) throws SeeWorldException;

	public void delete(Actor actor) throws SeeWorldException;

	public void update(Actor actor) throws SeeWorldException;

}
