package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IUserLogic;
import edu.tju.ina.seeworld.logic.MovieLogic;
import edu.tju.ina.seeworld.logic.INewThingsLogic;
import edu.tju.ina.seeworld.logic.SerialLogic;
import edu.tju.ina.seeworld.logic.SingleSerialLogic;
import edu.tju.ina.seeworld.logic.VideoLogic;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.po.resource.Serial;
import edu.tju.ina.seeworld.po.resource.SingleSerial;
import edu.tju.ina.seeworld.po.resource.Video;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.IDAssistant;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.util.enums.MultimediaType;
import edu.tju.ina.seeworld.vo.MovieVOForView;
import edu.tju.ina.seeworld.vo.MultimediaVOForView;
import edu.tju.ina.seeworld.vo.SerialVOForView;
import edu.tju.ina.seeworld.vo.SingleSerialVOForView;
import edu.tju.ina.seeworld.vo.VideoVOForView;

/**
 * 本类处理所有的观看请求，核心方法为playAction，根据传入的multimedia的id和resourceId进入播放页
 * 根据resource的不同跳转结果如下
 * MOVIE：跳转到movie的播放页，返回代表当前播放电影的movie对象和相关电影列表relativeMovies
 * VIDEO：跳转到video的播放页，返回代表当前播放视频的video对象和相关视频列表relativeVideos
 * SERIAL：跳转到serial的列表页，返回代表当前剧集的对象serial和相关剧集列表relativeSerials
 * SINGLESERIAL：跳转到单集的播放页，返回代表单集的singleserial对象
 * default：跳转到首页
 * @author zhfch
 *
 */
public class MultimediaAction extends BaseAction implements ModelDriven<MultimediaVOForView> {
	private static final long serialVersionUID = -4392193229193054755L;

	/* 必须在playAction中赋值，用来指定跳转到哪个view页，z */
	private MultimediaType type;

	private MultimediaVOForView model;
	private List<MovieVOForView> relativeMovies;
	private List<VideoVOForView> relativeVideos;
	private List<SerialVOForView> relativeSerials;

	private MovieLogic movieLogic;
	private VideoLogic videoLogic;
	private SerialLogic serialLogic;
	private SingleSerialLogic singleSerialLogic;
	
	private INewThingsLogic newThingsLogic;
	private IUserLogic userLogic;
	private IDAssistant iDAssistant;
	private VOPOTransformator vOPOTransformator;

	private Integer id;
	private String resourceId;

	public String playAction() {
		try {
			User u = userLogic.findByUserName(getCurrentUsername()).get(0);
			String resourceName = iDAssistant.getResourceName(resourceId);
			
			if (StringUtils.equalsIgnoreCase(
					resourceName, MultimediaType.Movie.toString())) {
				
				type = MultimediaType.Movie;
				Movie m = movieLogic.getMovieById(id);
				m.setClickCount(m.getClickCount() + 1);
				movieLogic.update(m);
				List<Movie> list = movieLogic.getRelativeMovies(id);
				relativeMovies = vOPOTransformator
						.transferMovieToVOForViewList(list);
				newThingsLogic.addViewNewThings(u, id, m.getResource());
				model = new MovieVOForView(m);
				
			} else if (StringUtils.equalsIgnoreCase(
					resourceName, MultimediaType.Video.toString())) {
				
				type = MultimediaType.Video;
				Video v = videoLogic.getVideoById(id);
				v.setClickCount(v.getClickCount() + 1);
				videoLogic.update(v);
				newThingsLogic.addViewNewThings(u, id, v.getResource());
				List<Video> list = videoLogic.getRelativeVideos(id);
				relativeVideos = vOPOTransformator
						.transferVideoToVOForViewList(list);
				model = new VideoVOForView(v);
				
			} else if (StringUtils.equalsIgnoreCase(
					resourceName, MultimediaType.Serial.toString())) {
				
				type = MultimediaType.Serial;
				
			} else if (StringUtils.equalsIgnoreCase(
					resourceName, MultimediaType.SingleSerial.toString())) {
				
				type = MultimediaType.SingleSerial;
				SingleSerial ss = singleSerialLogic.findByID(id);
				ss.setClickCount(ss.getClickCount() + 1);
				Serial s = ss.getSerial();
				Integer serialId = (Integer) session.get(Constant.CURRENT_SERIAL_ID_IN_SESSION);
				if (serialId == null || !serialId.equals(s.getId())){
					s.setClickCount(s.getClickCount() + 1);
					session.put(Constant.CURRENT_SERIAL_ID_IN_SESSION, s.getId());
					serialLogic.update(s);
				}
				singleSerialLogic.update(ss);
				newThingsLogic.addViewNewThings(u, id, s.getResource());
				model = new SingleSerialVOForView(ss);
				
			} else
				return TO_MAIN_PAGE;
		} catch (SeeWorldException e) {
			e.printStackTrace();
		}
		return PLAYMULTIMEDIA;
	}

	public List<MovieVOForView> getRelativeMovies() {
		return relativeMovies;
	}

	public List<VideoVOForView> getRelativeVideos() {
		return relativeVideos;
	}

	public List<SerialVOForView> getRelativeSerials() {
		return relativeSerials;
	}

	public void setMovieLogic(MovieLogic movieLogic) {
		this.movieLogic = movieLogic;
	}

	public void setVideoLogic(VideoLogic videoLogic) {
		this.videoLogic = videoLogic;
	}

	public void setSerialLogic(SerialLogic serialLogic) {
		this.serialLogic = serialLogic;
	}

	public void setSingleSerialLogic(SingleSerialLogic singleSerialLogic) {
		this.singleSerialLogic = singleSerialLogic;
	}

	public void setNewThingsLogic(INewThingsLogic newThingsLogic) {
		this.newThingsLogic = newThingsLogic;
	}

	public void setUserLogic(IUserLogic userLogic) {
		this.userLogic = userLogic;
	}

	public void setiDAssistant(IDAssistant iDAssistant) {
		this.iDAssistant = iDAssistant;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getType() {
		return type.toString();
	}

	public MultimediaVOForView getModel() {
		// TODO Auto-generated method stub
		return model;
	}
}
