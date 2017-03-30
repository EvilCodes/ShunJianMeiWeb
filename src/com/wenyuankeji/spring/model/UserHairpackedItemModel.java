package com.wenyuankeji.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassNameUserHairpackedItem
 * @Description 发型套餐项
 * @date 2016-01-11 13:19:18
 * @author lnn
 */
@Entity
@Table(name = "user_hairpacked_item")
public class UserHairpackedItemModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer id; //id    
    private Integer hairpackedid; //套餐id    
    private String name; //项目名称    
    private Double price; //价格    
    private Integer style; //发型类型，对应发型类型表中的id
    private Integer state; //状态  0不可用，1可用    
    private Date createtime; //创建时间    
    
    public Integer getId (){    
        return id;    
    }    
    public void setId ( Integer id ){    
        this.id = id ;    
    }    
    @Column(name = "hairpackedid")
    public Integer getHairpackedid (){    
        return hairpackedid;    
    }    
	public void setHairpackedid ( Integer hairpackedid ){    
        this.hairpackedid = hairpackedid ;    
    }    
    @Column(name = "name")
    public String getName (){    
        return name;    
    }    
    public void setName ( String name ){    
        this.name = name ;    
    }    
    @Column(name = "price")
    public Double getPrice (){    
        return price;    
    }    
    public void setPrice ( Double price ){    
        this.price = price ;    
    }    
    @Column(name = "style")
    public Integer getStyle (){    
        return style;    
    }    
    public void setStyle ( Integer style ){    
        this.style = style ;    
    }    
    @Column(name = "state")
    public Integer getState (){    
        return state;    
    }    
    public void setState ( Integer state ){    
        this.state = state ;    
    }    
    @Column(name = "createtime")
    public Date getCreatetime (){    
        return createtime;    
    }    
    public void setCreatetime ( Date createtime ){    
        this.createtime = createtime ;    
    }    
}
