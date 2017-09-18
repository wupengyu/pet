package com.yf.pet.pets.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetBreed implements Serializable {
    private Integer petBreedId;//ID
    private Integer petSpecies;//宠物种类
    private String petBreedChinaName;//宠物类型中文名
    private String petBreedPic;//宠物类型代表头像
    private String petChinaInitials;
    private String petBreedEnglishName;
    private String petEnlishInitials;
}
