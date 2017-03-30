package com.wenyuankeji.spring.util;

import java.util.Comparator;

import com.wenyuankeji.spring.model.StoreinfoModel;  
  
public class ContentComparator implements Comparator<StoreinfoModel>{  
    public int compare(StoreinfoModel o1, StoreinfoModel o2) {  
  
        //将 null 排到最后  
        if(o1 == null){  
            return 1;  
        }  
        if(o2 == null || !(o2 instanceof StoreinfoModel)){  
            return -1;  
        }  
          
        long key1 = o1.getDistance();  
        long key2 = o2.getDistance();  
        return key1 > key2 ? 1 : key1 < key2 ? -1 : 0;  
          
        /* 
        //如果想要按照 name 字段进行排序, 只需将最后三行代码改为下面这一行即可 
        return o1.getName().compareTo(o2.getName()); 
        */  
          
    }  

    
}  