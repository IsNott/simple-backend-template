package system.adopt.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import system.adopt.entity.AdoptePet;
import system.adopt.entity.AdopterInfo;
import system.adopt.entity.PetCatalog;
import system.adopt.entity.PetInfo;
import system.adopt.mapper.PetCatalogMapper;
import system.adopt.mapper.PetInfoMapper;
import system.comon.Result;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  宠物领养及分类控制器
 * </p>
 *
 * @author jobob
 * @since 2023-02-19
 */
@RestController
@RequestMapping("/adopt/")
public class PetInfoController {

    @Resource
    private PetCatalogMapper petCatalogMapper;
    @Resource
    private PetInfoMapper petInfoMapper;

    // 获取所有宠物分类
    @RequestMapping("catalog")
    public Result catalog(){
        List<PetCatalog> petCatalogs = petCatalogMapper.selectList(new LambdaQueryWrapper<PetCatalog>());
        return Result.okData(petCatalogs);
    }

    // 新增宠物分类
    @RequestMapping("addCatalog")
    public Result addCata(PetCatalog petCatalog){
        if(Objects.isNull(petCatalog)){
            return Result.fail("分类对象为空");
        }
        petCatalogMapper.insert(petCatalog);
        return Result.ok();
    }

    // 根据id删除分类
    @RequestMapping("delCatalog")
    public Result delCata(long catalogId){
        PetCatalog petCatalog = petCatalogMapper.selectById(catalogId);
        if(Objects.isNull(petCatalog)){
            return Result.fail("所选分类对象: "+catalogId+"为空");
        }
        petCatalogMapper.deleteById(petCatalog);
        return Result.ok();
    }

    // 更新分类
    @RequestMapping("updateCata")
    public Result updateCa(PetCatalog petCatalog){
        if(Objects.isNull(petCatalog) || petCatalog.getId() == null){
            return Result.fail("分类对象为空");
        }
        petCatalogMapper.updateById(petCatalog);
        return Result.ok();
    }

    // 发布领养信息
    @RequestMapping("addPetRecord")
    public Result addRecords(AdoptePet pet){
        if(Objects.isNull(pet)){
            return Result.fail("宠物或领养人信息不能为空");
        }
        PetInfo info = new PetInfo();
        info.setCatalogId(pet.getCatalogId());
        info.setArea(pet.getArea());
        info.setPetGender(pet.getGender());
        info.setAdoptFlag("0");
        info.setPetName(pet.getName());
        info.setReleaseTime(LocalDateTime.now());
        petInfoMapper.insert(info);
        return Result.ok();
    }

    // 按领养状态查询宠物列表，不传查status全部宠物
    @RequestMapping("pet")
    public Result pet(String status){
        LambdaQueryWrapper<PetInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(status),PetInfo::getAdoptFlag,status);
        List<PetInfo> petInfos = petInfoMapper.selectList(wrapper);
        return Result.okData(petInfos);
    }

    // 根据账号id找到领养的宠物记录，不传用户id默认查全部宠物记录
    @RequestMapping("findMyPet")
    public Result findPet(long userId){
        LambdaQueryWrapper<PetInfo> wrapper = new LambdaQueryWrapper<PetInfo>()
                .eq(StringUtils.isNotBlank(Long.toString(userId)),PetInfo::getAdopterId, userId);
        List<PetInfo> petInfos = petInfoMapper.selectList(wrapper);
        return Result.okData(petInfos);
    }

    // 根据宠物领养记录取消领养
    @RequestMapping("cancelAdopt")
    public Result cancel(long id){
        PetInfo petInfo = petInfoMapper.selectById(id);
        if(Objects.isNull(petInfo)){
            return Result.fail("不存在宠物记录");
        }
        if("0".equals(petInfo.getAdoptFlag()) || Objects.isNull(petInfo.getAdopterId())){
            return Result.fail("宠物未被领养或者记录不存在领养人id");
        }

        petInfoMapper.setPet2UnAdopt(petInfo.getId());
        return Result.ok();
    }

    // 根据宠物记录id领养宠物
    @RequestMapping("adopt")
    public Result adopt(long petId,AdopterInfo adopterInfo){
        PetInfo petInfo = petInfoMapper.selectById(petId);
        if(Objects.isNull(petInfo)){
            return Result.fail("不存在宠物记录");
        }
        petInfo.setAdoptFlag("1");
        petInfo.setAdoptTime(LocalDateTime.now());
        petInfo.setAdopterPhone(adopterInfo.getPhone());
        petInfo.setAdopterName(adopterInfo.getName());
        petInfo.setAdopterId(adopterInfo.getId());
        petInfo.setAdopterArea(adopterInfo.getArea());
        petInfoMapper.updateById(petInfo);
        return Result.ok();
    }

    // 按宠物记录id更新宠物记录，需要传完整PetInfo类的属性，不传则更新为null
    @RequestMapping("updateRecord")
    public Result updateRecord(PetInfo petInfo){
        PetInfo pet = petInfoMapper.selectById(petInfo.getId());
        if(Objects.isNull(pet)){
            return Result.fail("不存在宠物记录");
        }
        petInfoMapper.updateById(petInfo);
        return Result.ok();
    }

    // 删除宠物记录
    @RequestMapping("delRecord")
    public Result delRecord(long id){
        PetInfo pet = petInfoMapper.selectById(id);
        if(Objects.isNull(pet)){
            return Result.fail("不存在宠物记录");
        }
        petInfoMapper.deleteById(id);
        return Result.ok();
    }


}

