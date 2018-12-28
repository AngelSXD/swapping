package com.sxd.swapping.utils;

import com.xiaoleilu.hutool.io.FileUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 获取根路径 需要自己创建根路径
 *
 * 在docker中部署spring boot服务，根路径在jar包所在的目录下创建根目录，进行挂载
 *
 *
 * docker run --name swapping -itd --net=host -v /etc/localtime:/etc/localtime:ro  -v /etc/timezone:/etc/timezone:ro  -v /apps/swapping/data:/swapping-data  swapping
 */
public class ProjectPath {  
  
    private static String rootPath = "";  
  
    private ProjectPath() {  
        init();  
    }  
  
    private static void init() {  
    	
		String osName = System.getProperties().getProperty("os.name").toLowerCase();
		//windows 测试机 所以在E盘保存
		if(osName.contains("win")){
			rootPath = "E:/swapping";
		}else{
			rootPath = "/swapping-data";
		}
		
		try {
			
			boolean exist = FileUtil.exist(rootPath);
			if(!exist){
				FileUtil.mkdir(new File(rootPath));
			}
		} catch (Exception e) {
			
		}
    }  
  
    /** 
     * 获取应用的根目录，路径分隔符为/，路径结尾无/ 
     *  
     * @return 
     */  
    public static String getProjectPath(String folderName) {  
        if ("".equals(rootPath)) {  
            init();  
        }  
        
        if(StringUtils.isNotBlank(folderName)){
        	return rootPath + "/" + folderName;
        }else{
    	    return rootPath;
        }
    }  
    
    /** 
     * 获取应用的根目录，路径分隔符为/，路径结尾无/ 
     *  
     * @return 
     */  
    public static String getProjectPath() {  
    	return getProjectPath(null);  
    }  
  
}  