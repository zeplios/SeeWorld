package edu.tju.ina.seeworld.logic;

import java.io.Serializable;
import java.util.List;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;


public interface IAreaAndCountryLogic {

	public AreaAndCountry findById(Serializable id) throws SeeWorldException ;

	public List<AreaAndCountry> getAllAreaAndCountryList()
			throws SeeWorldException ;

	public List<AreaAndCountry> getAllAreaAndCountryListForCertainResource(
			String resourceName) throws SeeWorldException ;
}
