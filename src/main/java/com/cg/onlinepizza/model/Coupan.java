package com.cg.onlinepizza.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coupan")
public class Coupan {
	    @Id
	    @GeneratedValue
		private int coupanId;
	
		public Coupan(int coupanId, String coupanCode, String coupanDescription, float coupanDiscount) {
			this.coupanId = coupanId;
			this.coupanCode = coupanCode;
			this.coupanDescription = coupanDescription;
			this.coupanDiscount = coupanDiscount;
		}
		public Coupan() {
			// TODO Auto-generated constructor stub
		}
		public int getCoupanId() {
			return coupanId;
		}
		public void setCoupanId(int coupanId) {
			this.coupanId = coupanId;
		}
		public String getCoupanCode() {
			return coupanCode;
		}
		public void setCoupanCode(String coupanCode) {
			this.coupanCode = coupanCode;
		}
		public String getCoupanDescription() {
			return coupanDescription;
		}
		public void setCoupanDescription(String coupanDescription) {
			this.coupanDescription = coupanDescription;
		}
		public float getCoupanDiscount() {
			return coupanDiscount;
		}
		public void setCoupanDiscount(float coupanDiscount) {
			this.coupanDiscount = coupanDiscount;
		}
		private String coupanCode;
		private String coupanDescription;
		private float coupanDiscount;

		
	}
