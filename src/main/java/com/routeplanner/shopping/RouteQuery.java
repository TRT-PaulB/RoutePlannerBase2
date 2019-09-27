package com.routeplanner.shopping;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="route_query")
public class RouteQuery extends DataModel 
{
	@Column(name="start")
	private String currRouteStart;
	
	@Column(name="destination")
	private String currRouteDest;
	
	@Transient
	private String routeInfo;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	public RouteQuery() {
		
	}

	public RouteQuery(String currRouteStart, String currRouteDest, String routeInfo) {
		this.currRouteStart = currRouteStart;
		this.currRouteDest = currRouteDest;
		this.routeInfo = routeInfo;
	}


	public String getCurrRouteStart() {
		return currRouteStart;
	}


	public void setCurrRouteStart(String currRouteStart) {
		this.currRouteStart = currRouteStart;
	}


	public String getCurrRouteDest() {
		return currRouteDest;
	}


	public void setCurrRouteDest(String currRouteDest) {
		this.currRouteDest = currRouteDest;
	}


	public String getRouteInfo() {
		return routeInfo;
	}


	public void setRouteInfo(String routeInfo) {
		this.routeInfo = routeInfo;
	}


	@Override
	public String toString() {
		return "RouteQuery [currRouteStart=" + currRouteStart + ", currRouteDest=" + currRouteDest + ", routeInfo="
				+ routeInfo + "]";
	}
	
}
