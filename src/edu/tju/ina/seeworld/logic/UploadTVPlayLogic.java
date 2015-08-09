package edu.tju.ina.seeworld.logic;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.tju.ina.seeworld.dao.common.IRbacDAO;
import edu.tju.ina.seeworld.dao.resource.IActorDAO;
import edu.tju.ina.seeworld.dao.resource.IAreaAndCountryDAO;
import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.dao.resource.IDirectorDAO;
import edu.tju.ina.seeworld.dao.resource.IKeyWordDAO;
import edu.tju.ina.seeworld.dao.resource.IKeyWordRelationshipDAO;
import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.dao.resource.ISingleSerialDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.po.resource.KeyWord;
import edu.tju.ina.seeworld.po.resource.KeyWordRelationship;
import edu.tju.ina.seeworld.po.resource.Multimedia;
import edu.tju.ina.seeworld.po.resource.SingleSerial;
import edu.tju.ina.seeworld.po.user.User;

public class UploadTVPlayLogic {

	private IDirectorDAO directorDao;
	private IActorDAO actorDao;
	private IAreaAndCountryDAO areaAndCountryDao;
	private IUserDAO userDao;
	private ICategoryDAO categoryDao;
	private IRbacDAO<Resource> resourceDao;
	private IKeyWordDAO keyWordDao;
	private IMultimediaDAO multimediaDao;
	private ISingleSerialDAO singleSerialDao;

	public void setMultimediaDao(IMultimediaDAO multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void setKeyWordDao(IKeyWordDAO keyWordDao) {
		this.keyWordDao = keyWordDao;
	}

	public void setKeyWordRelationshipDao(
			IKeyWordRelationshipDAO keyWordRelationshipDao) {
		this.keyWordRelationshipDao = keyWordRelationshipDao;
	}

	private IKeyWordRelationshipDAO keyWordRelationshipDao;

	public void setResourceDao(IRbacDAO<Resource> resourceDao) {
		this.resourceDao = resourceDao;
	}

	public ICategoryDAO getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ICategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public void setSingleSerialDao(ISingleSerialDAO singleSerialDao) {
		this.singleSerialDao = singleSerialDao;
	}

	public void setDirectorDao(IDirectorDAO directorDao) {
		this.directorDao = directorDao;
	}

	public void setdirectorDao(IDirectorDAO directorDao) {
		this.directorDao = directorDao;
	}

	public void setActorDao(IActorDAO actorDao) {
		this.actorDao = actorDao;
	}

	public void setAreaAndCountryDao(IAreaAndCountryDAO areaAndCountryDao) {
		this.areaAndCountryDao = areaAndCountryDao;
	}

	public boolean saveTVPlay(List<String> keywords, List<String> categorys,
			String userName, String movieName, String introduction,
			List<String> directors, List<String> directorsAlias,
			List<String> directorsArea, List<String> actors,
			List<String> actorsAlias, List<String> actorsArea, String area,
			String year, String path, String alias, List<String> otherInfos)
			throws UnsupportedEncodingException, SeeWorldException {
		boolean tag = false;

		saveSingleSerial(keywords, categorys, userName, movieName,
				introduction, directors, directorsAlias, directorsArea, actors,
				actorsAlias, actorsArea, area, year, path, alias, otherInfos);

		return tag;

	}

	public boolean saveSingleSerial(List<String> keywords,
			List<String> categorys, String userName, String movieName,
			String introduction, List<String> directors,
			List<String> directorsAlias, List<String> directorsArea,
			List<String> actors, List<String> actorsAlias,
			List<String> actorsArea, String area, String year, String path,
			String alias, List<String> otherInfos)
			throws UnsupportedEncodingException, SeeWorldException {

		// List<Category> categoryList=categoryDao.findByName(category);
		// Category type=categoryList.get(0);
		System.out.println(userName + "++++++++");

		List<User> userList = userDao.findByUserName(userName);
		User user = userList.get(0);

		// 电影的地区
		List<AreaAndCountry> aaclist = areaAndCountryDao.findByName(area);
		AreaAndCountry areaAndCountry = aaclist.get(0);

		String[] clientPaths = getClientPath(path);

		Resource resource = resourceDao.findByName("PLAY");
		Category c = categoryDao.findById(3);

		String imgPath = getFileName(getImgPath(clientPaths));

		Timestamp addTime = new java.sql.Timestamp(System.currentTimeMillis());

		Integer clickCount = new Integer(0);
		Integer collectedCount = new Integer(0);

		if (multimediaDao.findByName(movieName).size() != 0) {
			// Set<Director>
			Set<Director> direcset = new HashSet<Director>();
			for (int i = 0; i < directors.size(); i++) {
				List<AreaAndCountry> directorACList = areaAndCountryDao
						.findByName(directorsArea.get(i));
				AreaAndCountry DirectorAreaAndCountry = directorACList.get(0);
				Director direc = new Director();
				direc.setName(directors.get(i));
				direc.setAddTime(addTime);
				direc.setAlias(directorsAlias.get(i));
				direc.setAreaAndCountry(DirectorAreaAndCountry);
				directorDao.save(direc);
				direcset.add(direc);
			}

			// Set<Actor>
			Set<Actor> acset = new HashSet<Actor>();
			for (int i = 0; i < actors.size(); i++) {
				Actor act = new Actor();
				System.out.println(actorsArea.get(i));
				List<AreaAndCountry> actorACList = areaAndCountryDao
						.findByName(actorsArea.get(i));
				AreaAndCountry ActorAreaAndCountry = actorACList.get(0);
				act.setAddTime(addTime);
				act.setAreaAndCountry(ActorAreaAndCountry);
				act.setName(actors.get(i));
				act.setAlias(actorsAlias.get(i));
				actorDao.save(act);
				acset.add(act);
			}
			Multimedia multimedia = new Multimedia();
			multimedia.setTitle(movieName);
			multimedia.setAddTime(addTime);
			multimedia.setAlias(alias);
			multimedia.setAreaAndCountry(areaAndCountry);
			Set<Category> cs = new HashSet<Category>();
			cs.add(c);
//			multimedia.setCategory(cs);
			multimedia.setClickCount(clickCount);
			multimedia.setCollectedCount(collectedCount);
			multimedia.setImage(imgPath);
			multimedia.setIntroduction(introduction);
			multimedia.setPublishedYear(year);
//			multimedia.setDirectors(direcset);
//			multimedia.setActors(acset);
			multimedia.setResource(resource);
			multimediaDao.save(multimedia);

			Multimedia mul = multimediaDao.findByName(movieName).get(0);

			for (int i = 0; i < clientPaths.length; i++) {
				if (isOneOfMultimedia(clientPaths[i])) {
					SingleSerial play = new SingleSerial();
					play.setPath(getFileName(clientPaths[i]));
					singleSerialDao.save(play);
				}
			}

			for (int i = 0; i < keywords.size(); i++) {
				KeyWord keyWord = new KeyWord();
				keyWord.setAddTime(addTime);
				keyWord.setKeyword(keywords.get(i));
				keyWordDao.save(keyWord);

				KeyWordRelationship krl = new KeyWordRelationship();
				krl.setAddTime(addTime);
				krl.setWeight(new Integer("1"));
				krl.setKeyWord(keyWord);
				krl.setTarget(mul);
				keyWordRelationshipDao.save(krl);

			}
			return true;
		} else
			return false;
	}

	public String[] getClientPath(String path) {
		return path.split("&&");
	}

	public String getFormat(String path) {
		return path.substring(path.lastIndexOf(".") + 1, path.length());
	}

	public boolean isOneOfMultimedia(String path) {

		String format = getFormat(path);

		return (format.equals("rmvb") || format.equals("flv")
				|| format.equals("wmv") || format.equals("rm"));

	}

	public String getImgPath(String[] path) {
		String imgPath = "";
		for (String s : path) {
			if ((!isOneOfMultimedia(s)) && (s != "")) {
				imgPath = s;
			}
		}
		return imgPath;
	}

	public String getFileName(String s) {
		return s.substring(s.lastIndexOf("\\") + 1, s.length());
	}

	public static void main(String args[]) {
		// System.out.println(new
		// UploadMovieLogic().getFileSize("IPTV_MULTIMEDIA/movie/lx/aa/jiaoxue.rm"));
		String encoding = System.getProperty("file.encoding");
		System.out.println(encoding);
		String str = "汉";
		String str1, str2;
		try {
			str1 = new String(str.getBytes("UTF-8"), "GBK");
			System.out.println(str1.length() + "1");
			str2 = new String(str1.getBytes("GBK"), "UTF-8");
			System.out.println(str2.length() + "2");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
