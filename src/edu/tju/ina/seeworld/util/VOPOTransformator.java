package edu.tju.ina.seeworld.util;

import java.util.ArrayList;
import java.util.List;

import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.dao.user.ICollectionDAO;
import edu.tju.ina.seeworld.dao.user.ICommentDAO;
import edu.tju.ina.seeworld.dao.user.IRecommendationDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.po.resource.Multimedia;
import edu.tju.ina.seeworld.po.resource.Serial;
import edu.tju.ina.seeworld.po.resource.Mainpage;
import edu.tju.ina.seeworld.po.resource.SingleSerial;
import edu.tju.ina.seeworld.po.resource.Video;
import edu.tju.ina.seeworld.po.user.Collection;
import edu.tju.ina.seeworld.po.user.Comment;
import edu.tju.ina.seeworld.po.user.InvitationCode;
import edu.tju.ina.seeworld.po.user.NewThings;
import edu.tju.ina.seeworld.po.user.Recommendation;
import edu.tju.ina.seeworld.po.user.Request;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.vo.CollectionVO;
import edu.tju.ina.seeworld.vo.CommentVO;
import edu.tju.ina.seeworld.vo.EssentialUser;
import edu.tju.ina.seeworld.vo.InvitationCodeVO;
import edu.tju.ina.seeworld.vo.MovieVOForView;
import edu.tju.ina.seeworld.vo.NewThingsVO;
import edu.tju.ina.seeworld.vo.RecommendationVO;
import edu.tju.ina.seeworld.vo.RequestVO;
import edu.tju.ina.seeworld.vo.SerialVOForView;
import edu.tju.ina.seeworld.vo.MainpageVO;
import edu.tju.ina.seeworld.vo.SimpleMultimedia;
import edu.tju.ina.seeworld.vo.SimpleUserVO;
import edu.tju.ina.seeworld.vo.SingleSerialVOForView;
import edu.tju.ina.seeworld.vo.UserVO;
import edu.tju.ina.seeworld.vo.VideoVOForView;

public class VOPOTransformator {

	private IMultimediaDAO multimediaDao;
	private ICommentDAO commentDao;
	private ICollectionDAO collectionDao;
	private IRecommendationDAO recommendationDao;

	private IDAssistant iDAssistant;
	
	public RecommendationVO transferRecommendationToRecommendationVO(
			Recommendation r) throws SeeWorldException {
		RecommendationVO recommendationVo = new RecommendationVO();
		recommendationVo.setId(r.getId());
		recommendationVo.setSender_id(r.getSender().getId());
		recommendationVo.setSenderName(r.getSender().getRealName());
		recommendationVo.setReceiver_id(r.getReceiver().getId());
		recommendationVo.setreceiverName(r.getReceiver().getRealName());
		recommendationVo.setTargetId(r.getTargetID());
		recommendationVo.setResourceId(r.getResource().getId());
		
		Multimedia target = multimediaDao.findById(r.getTargetID());
		recommendationVo.setTargetName(target.toString());
		if (target instanceof SingleSerial) {
			SingleSerial ss = (SingleSerial)target;
			recommendationVo.setTargetImg(ss.getSerial().getImage());
		} else {
			recommendationVo.setTargetImg(target.getImage());
		}
		recommendationVo.setAddTime(DateUtil.getFormattedDateString(r.getAddTime()));
		recommendationVo.setMessage(r.getMessage());
		return recommendationVo;
	}

	public List<UserVO> transferUserToUserVOForViewList(
			List<User> userList) {
		List<UserVO> list = new ArrayList<UserVO>();
		for (User u : userList) {
			UserVO userjson = new UserVO(u);
			list.add(userjson);
		}
		return list;
	}

	public List<MovieVOForView> transferMovieToVOForViewList(
			List<Movie> movieList) {
		List<MovieVOForView> resList = new ArrayList<MovieVOForView>();
		for (Movie m : movieList) {
			resList.add(new MovieVOForView(m));
		}
		return resList;
	}
	
	public List<MainpageVO> transferMainpageToVO(List<Mainpage> shows){
		List<MainpageVO> show_vo = new ArrayList<MainpageVO>();
		for(Mainpage s : shows){
			show_vo.add(new MainpageVO(s));
		}
		return show_vo;
	}

	public NewThingsVO transferNewThingsToVO(NewThings newthings,
			String receiverID) throws SeeWorldException {
		NewThingsVO nt = new NewThingsVO();
		nt.setId(newthings.getId());
		nt.setUser(new SimpleUserVO(newthings.getUser()));
		nt.setReplyNum(newthings.getReplyNum());
		nt.setTypeId(newthings.getTypeId());
		nt.setOperation(iDAssistant.getOperationName(newthings.getTypeId()));
		Multimedia target;
		int targetId;
		if(newthings.getTypeId().equals(Constant.USER_OPERATION_COMMENT)){
			Comment comment = commentDao.findById(newthings.getTargetId());
			nt.setOtherInfo(comment.getContent());
			targetId = comment.getTargetID();
		}
		else if(newthings.getTypeId().equals(Constant.USER_OPERATION_COLLECT)){
			Collection collection= collectionDao.findById(newthings.getTargetId());
			targetId = collection.getTargetID();
		}
		else if(newthings.getTypeId().equals(Constant.USER_OPERATION_RECOMMEND)){
			Recommendation recommend= recommendationDao.findById(newthings.getTargetId());
			targetId = recommend.getTargetID();
		}
		else {
			targetId = newthings.getTargetId();
		}
		target = multimediaDao.findById(targetId);
		if(target instanceof SingleSerial){
			target = ((SingleSerial)target).getSerial();
		}
		if(nt.getOtherInfo() == null)
			nt.setOtherInfo(target.getIntroduction());
		nt.setTarget(new SimpleMultimedia(target));
		nt.setModifiedTime(newthings.getAddTime());
		// if (newthings.getReplyNum() > 0) {
		// ntVO.setFirstReply(newthings.getNewThingsReplyByFirstReply(),
		// receiverID);
		// if (newthings.getReplyNum() > 1) {
		// ntVO.setLastReply(newthings.getNewThingsReplyByLastReply(),
		// receiverID);
		// } else {
		// ntVO.setLastReply(new MessageVO());
		// }
		// } else {
		// ntVO.setFirstReply(new MessageVO());
		// ntVO.setLastReply(new MessageVO());
		// }
		return nt;
	}

	public CollectionVO transferCollectionToVO(Collection collection)
			throws SeeWorldException {
		CollectionVO cVO = new CollectionVO();
		if (collection != null) {
			cVO.setId(collection.getId());
			cVO.setTargetId(collection.getTargetID());
			cVO.setResourceId(collection.getResource().getId());
			cVO.setAddtime(DateUtil.getFormattedDateString(collection
					.getAddTime()));
			cVO.setOwnerId(collection.getUser().getId());
		}
		Multimedia target = multimediaDao.findById(cVO.getTargetId());
		cVO.setTargetTitle(target.toString());
		if (target instanceof SingleSerial) {
			SingleSerial ss = (SingleSerial)target;
			cVO.setTargetImage(ss.getSerial().getImage());
		} else {
			cVO.setTargetImage(target.getImage());
		}
		return cVO;
	}

	public List<VideoVOForView> transferVideoToVOForViewList(
			List<Video> videoList) {
		List<VideoVOForView> resList = new ArrayList<VideoVOForView>();
		for (Video m : videoList) {
			resList.add(new VideoVOForView(m));
		}
		return resList;
	}

	public List<InvitationCodeVO> transferInvitationCodeToVOList(
			List<InvitationCode> icList) {
		List<InvitationCodeVO> resList = new ArrayList<InvitationCodeVO>();
		for (InvitationCode ic : icList) {
			resList.add(new InvitationCodeVO(ic));
		}
		return resList;
	}

	public List<SingleSerialVOForView> transferSingleSerialToVOForViewList(
			List<SingleSerial> singlesList) {
		List<SingleSerialVOForView> resList = new ArrayList<SingleSerialVOForView>();
		for (SingleSerial ss : singlesList) {
			resList.add(new SingleSerialVOForView(ss));
		}
		return resList;
	}

	public List<SerialVOForView> transferSerialToVOForViewList(
			List<Serial> serialList) {
		List<SerialVOForView> resList = new ArrayList<SerialVOForView>();
		for (Serial s : serialList) {
			resList.add(new SerialVOForView(s));
		}
		return resList;
	}

	public List<RequestVO> transferRequestToVOList(List<Request> requestList) {
		List<RequestVO> resList = new ArrayList<RequestVO>();
		for (Request r : requestList) {
			resList.add(new RequestVO(r));
		}
		return resList;
	}

	public List<EssentialUser> transferUserToEssentialUserList(List<User> vList) {
		List<EssentialUser> resList = new ArrayList<EssentialUser>();
		for (User v : vList) {
			resList.add(new EssentialUser(v));
		}
		return resList;
	}

	public List<CommentVO> transferCommentToVOList(List<Comment> cList) {
		List<CommentVO> resList = new ArrayList<CommentVO>();
		for (Comment c : cList) {
			CommentVO com = new CommentVO(c);
			try {
				Multimedia multimedia = multimediaDao.findById(c.getTargetID());
				if(multimedia == null)
					com.setTargetName("已删除");
				else
					com.setTargetName(multimedia.getTitle());
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resList.add(com);
		}
		return resList;
	}

	public void setMultimediaDao(IMultimediaDAO multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void setCommentDao(ICommentDAO commentDao) {
		this.commentDao = commentDao;
	}

	public void setCollectionDao(ICollectionDAO collectionDao) {
		this.collectionDao = collectionDao;
	}

	public void setiDAssistant(IDAssistant iDAssistant) {
		this.iDAssistant = iDAssistant;
	}

	public void setRecommendationDao(IRecommendationDAO recommendationDao) {
		this.recommendationDao = recommendationDao;
	}

}
