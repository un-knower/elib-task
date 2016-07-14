package com.winxuan.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jason on 2016/5/18.
 */
@Entity
@Table(name = "elib_magazine_item_copy")
public class MagazineItem implements Serializable {
    private static final long serialVersionUID = 2258120564119055933L;
    /**
     * 子期刊ID
     **/
    private Long id;
    /**
     * 期刊名称
     **/
    private String name;
    /**
     * 期刊封面
     **/
    private String cover;
    /**
     * 期数
     **/
    private Integer count;
    /**
     * 总期刊ID
     **/
    private Magazine magazine;
    /**
     * 文件大小
     **/
    private Integer fileSize;
    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 资源类型（1.源文件 2.接口）
     */
    private Integer resType;

    /**
     * 接口分刊ID
     */
    private Integer itemId;
    /**
     * 接口总刊ID
     */
    private Integer itemMagazineId;
    /**
     * 接口封面
     */
    private String coverImage;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @ManyToOne()
    @JoinColumn(name = "magazine_id")
    @JsonIgnore
    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    @Column(name = "file_size")
    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "res_type")
    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    @Column(name = "item_id")
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Column(name = "item_magazine_id")
    public Integer getItemMagazineId() {
        return itemMagazineId;
    }

    public void setItemMagazineId(Integer itemMagazineId) {
        this.itemMagazineId = itemMagazineId;
    }

    @Column(name = "cover_image")
    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
