package edu.tju.ina.seeworld.vo;

public class SpecialtyVO {
	
		private Integer id;
		private String name;
		private Integer academy_id;
		
		public SpecialtyVO(){
			
		}
		public Integer getAcademy_id() {
			return academy_id;
		}
		
		public void setAcademy_id(Integer academy_id) {
			this.academy_id = academy_id;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
	
}
