package edu.tju.ina.seeworld.vo;

public class SearchConditionVO {
	private Integer categoryID;
	private Integer year;
	private Integer area;
	private Integer director;
	private Integer actor;
	private Integer order;
	private Integer pageSize;
	private Integer currentPage;
	private Integer itemSum;
	private Integer parentCategory;
	public SearchConditionVO(Integer actor, Integer area, Integer categoryID,
			Integer currentPage, Integer director, Integer itemSum,
			Integer order, Integer pageSize, Integer year,Integer parentCategory) {
		super();
		this.actor = actor;
		this.area = area;
		this.categoryID = categoryID;
		this.currentPage = currentPage;
		this.director = director;
		this.itemSum = itemSum;
		this.order = order;
		this.pageSize = pageSize;
		this.year = year;
		this.parentCategory= parentCategory;
	}
	public SearchConditionVO(){
		this.actor = 0;
		this.area = 0;
		this.categoryID = 0;
		this.currentPage = 0;
		this.director = 0;
		this.itemSum = 0;
		this.order = 0;
		this.pageSize = 0;
		this.year = 0;
		this.parentCategory=0;
	}
	public String getConditionClause(){
		String SQL="";
		if(year>0){
			SQL+=" and published_year='"+year+"'";
		}
		if(area>0){
			SQL+=" and area="+area;
		}
		if(order==0){
			SQL+="order by addTime desc";
		}else if(order==1){
			SQL+="order by clickCount desc";
		}
		return SQL;
	}
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getDirector() {
		return director;
	}
	public void setDirector(Integer director) {
		this.director = director;
	}
	public Integer getActor() {
		return actor;
	}
	public void setActor(Integer actor) {
		this.actor = actor;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getItemSum() {
		return itemSum;
	}
	public void setItemSum(Integer itemSum) {
		this.itemSum = itemSum;
	}
	public Integer getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Integer parentCategory) {
		this.parentCategory = parentCategory;
	}
	
}
