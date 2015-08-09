package edu.tju.ina.seeworld.util.enums;

/**
 * 指定地域的枚举，现在先写死在这，主要用于规范overview页几个部分的序号与名称
 * RANK：点击排行榜，索引为0
 * EA：欧美，索引为1，对应于数据表中的ID
 * HT：港台，索引为2，对应于数据表中的ID
 * ML：大陆，索引为3，对应于数据表中的ID
 * JK：日韩，索引为4，对应于数据表中的ID
 * UN：未知，索引为5，对应于数据表中的ID
 * @author zhfch
 *
 */
public enum AreaType {
	RANK,
	EA,
	HT,
	ML,
	JK,
	UN
}
