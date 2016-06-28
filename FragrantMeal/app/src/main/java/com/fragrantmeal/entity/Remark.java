package com.fragrantmeal.entity;
/**
 * 圈子备注表
 * @author Administrator
 */
public class Remark {
	
	private String re_id;				//备注主键
	private String remarked_id;			//被备注的用户外键id
	private String remarked_name;		//被备注的用户昵称
	private String u_id;				//改备注的用户外键id
	private String remark_name;			//改备注的用户昵称
	private String fr_id;				//美食圈id
	private String fr_name;				//美食圈名称
	private String re_name;				//圈子备注名称

	public Remark() {
		
	}
	
	public Remark(String re_id, String remarked_id, String remarked_name,
			String u_id, String remark_name, String fr_id, String fr_name,
			String re_name) {
		this.re_id = re_id;
		this.remarked_id = remarked_id;
		this.remarked_name = remarked_name;
		this.u_id = u_id;
		this.remark_name = remark_name;
		this.fr_id = fr_id;
		this.fr_name = fr_name;
		this.re_name = re_name;
	}

	public String getFr_id() {
		return fr_id;
	}



	public String getFr_name() {
		return fr_name;
	}



	public String getRe_id() {
		return re_id;
	}
	

	public String getRe_name() {
		return re_name;
	}


	public String getRemark_name() {
		return remark_name;
	}

	public String getRemarked_id() {
		return remarked_id;
	}

	public String getRemarked_name() {
		return remarked_name;
	}

	public String getU_id() {
		return u_id;
	}
	public void setFr_id(String fr_id) {
		this.fr_id = fr_id;
	}
	public void setFr_name(String fr_name) {
		this.fr_name = fr_name;
	}
	public void setRe_id(String re_id) {
		this.re_id = re_id;
	}

	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}

	public void setRemark_name(String remark_name) {
		this.remark_name = remark_name;
	}

	public void setRemarked_id(String remarked_id) {
		this.remarked_id = remarked_id;
	}
	public void setRemarked_name(String remarked_name) {
		this.remarked_name = remarked_name;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

}
