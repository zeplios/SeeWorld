package edu.tju.ina.seeworld.util;

/**
 * @author Uranus
 * 
 */
public class Constant {
	
	private Constant() {
	}

	public static final int AVATAR_WIDTH=120;
	public static final int AVATAR_HEIGHT=168;
	public static final String PHOTO_TEMP_PATH = "PHOTO_TEMP_PATH";
	public static final String AVATAR_PATH = "AVATAR_PATH";
	public static final String MULTIMEDIA_IMAGE_PATH = "MULTIMEDIA_IMAGE_PATH";
	/**
	 * 注册是否需要邀请码注册
	 */
	public static final String IS_INVITED_REQUIRED = "is_invited_required";
	
	public static final String RANK_LIST_LENGTH = "rank_list_length";
	public static final String NEWEST_LIST_LENGTH="newest_list_length";
	public static final String RELATED_ITEMS_LENGTH ="related_items_length";
	public static final String SERIAL_LIST_LENGTH ="serial_list_length";
	
	/**
	 * 电影电视剧简介页面显示长度
	 */
	public static final String MULTIMEDIA_INTRODUCTION_LENGTH="multimeida_introduction_length";
	
	public static final String CURRENT_USER_MODEL_IN_SESSION = "usermodel_in_session";
	/**
	 * 当用户连续播放剧集时，从观看的第二集开始，不应该再更新整个剧集的观看次数，因此把当前正在观看的剧集id存入session
	 */
	public static final String CURRENT_SERIAL_ID_IN_SESSION = "serial_id_in_session";
	
	public static final String DEFAULT_USER_PHOTO = "noPhoto.jpg";

	/**
	 * 操作分类
	 */
	public static final int USER_OPERATION_MODIFY = 1;
	public static final int USER_OPERATION_COLLECT = 2;
	public static final int USER_OPERATION_COMMENT = 3;
	public static final int USER_OPERATION_VIEW = 4;
	public static final int USER_OPERATION_UPLOAD = 5;
	public static final int USER_OPERATION_DELETE = 6;
	public static final int USER_OPERATION_RECOMMEND = 7;
	/**
	 * 邀请码状态
	 */
	public static final String INVITATIONCODE_VALID = "valid";
	public static final String INVITATIONCODE_EXPIRED = "expired";
	public static final String INVITATIONCODE_USERD = "used";
	public static final String INVITATIONCODE_NONENTITY = "nonentity";
	public static final String INVITATIONCODE_PER_USER = "invitation_codes_per_user";

	/**
	 * 每页显示项目数
	 */
	public static final String ITEMS_PER_PAGE = "items_per_page";
	/**
	 * 每页的评论数，推荐数
	 */
	public static final String COMMENTS_RECOMMENDATION_PER_PAGE = "comments_per_page";
	public static final String FRIENDS_PER_PAGE = "friends_per_page";
	public static final String ACTIVE_USER_NUM = "active_users_num";
	public static final String USERS_PER_PAGE = "users_per_page";
	public static final String MESSAGES_PER_PAGE = "messages_per_page";
	public static final String REQUESTS_PER_PAGE= "requests_per_page";

	
	/**
	 * 表示排列结果的排序顺序
	 */
	public static final int ORDERBY_NAME = 1;
	public static final int ORDERBY_TIME = 2;
	public static final int ORDERBY_CLICK = 3;
	public static final int ORDERBY_COLLECT = 4;
	public static final int ORDERBY_RECOMMEND = 5;
	public static final String MAX_REGISTERED_USER_ALLOWED = "max_registered_user_allowed";
	public static final String INVITATIONCODE_VALID_PERIOD = "invitationcode_valid_period";
	
}
