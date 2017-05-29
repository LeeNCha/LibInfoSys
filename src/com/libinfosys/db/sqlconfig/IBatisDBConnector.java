package com.libinfosys.db.sqlconfig;

import java.io.Reader;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IBatisDBConnector {
	private static SqlMapClient mySQLMap;
	
	
	static{
		try{
			//접속설정파일로드
			String resource="com/libinfosys/db/sqlconfig/SqlMapConfig.xml";
			Reader reader=Resources.getResourceAsReader(resource);
			mySQLMap=SqlMapClientBuilder.buildSqlMapClient(reader);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapInstance(){
		//return instance
		return mySQLMap;
	}

}
