package com.winxuan.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason on 2016/5/18.
 */
@Entity
@Table(name = "elib_magazine_copy")
public class Magazine implements Serializable {
    private static final long serialVersionUID = -270890697203726073L;
    /** 期刊ID **/
    private Long id;
    /** 期刊名称 **/
    private String name;
    /** 期刊国内刊号 **/
    private String number;
    /** 期刊邮发代号 **/
    private String code;
    /** 期刊主办单位 **/
    private String sponsor;
    /** 期刊主管单位 **/
    private String organizer;
    /** 期刊封面 **/
    private String cover;
    /** 最新一期ID **/
    private Integer lastItemId;
    /** 授权形式（1资源授权、2接口授权） **/
    private Integer authorize;
    /** 更新时间 **/
    private Date updateTime;
    /** 创建时间 **/
    private Date createTime;
    /** 期刊类型（月刊 半月刊 双周刊 周刊） **/
    private String type;
    /** 纸刊定价 **/
    private Long costprice;

    /**
     * 资源来源(1.中邮 2.农家书屋)
     */
    private Integer source;

   	private Set<MagazineItem> magazineItems;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "sponsor")
    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    @Column(name = "organizer")
    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Column(name = "lastItem_id")
    public Integer getLastItemId() {
        return lastItemId;
    }

    public void setLastItemId(Integer lastItemId) {
        this.lastItemId = lastItemId;
    }

    @Column(name = "authorize")
    public Integer getAuthorize() {
        return authorize;
    }

    public void setAuthorize(Integer authorize) {
        this.authorize = authorize;
    }

    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "costprice")
    public Long getCostprice() {
        return costprice;
    }

    public void setCostprice(Long costprice) {
        this.costprice = costprice;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magazine", orphanRemoval = true)
   	@OrderBy("id")
    public Set<MagazineItem> getMagazineItems() {
    	if(magazineItems == null){
    		magazineItems= new HashSet<MagazineItem>();
    	}
		return magazineItems;
	}

	public void setMagazineItems(Set<MagazineItem> magazineItems) {
		this.magazineItems = magazineItems;
	}

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
