package com.routeplanner.shopping;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class DataModel 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;
	
	public DataModel() 
	{
		
	}
	
	public Integer getId() 
	{
		return id;
	}
	
	public void setId(Integer id) 
	{
		this.id = id;
	}
	
}
