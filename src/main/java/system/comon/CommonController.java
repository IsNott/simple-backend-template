package system.comon;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import system.comon.mapper.MyBaseMapper;
import system.utils.FileUtils;
import system.utils.WrapperUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Description("单表查询通用接口")
@Data
@Slf4j
public abstract class CommonController<Service extends ServiceImpl, T> {

    public CommonController(Service s) {
        this.s = s;
    }

    @Autowired
    public Service s;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping("/joinPage")
    public Result joinPage(@RequestBody JSONObject req) throws Exception {
        JSONObject page = req.getJSONObject("page");
        Page<T> tPage = new Page<>(page.getLong("current") > 0 ? page.getLong("current") : 1,
                page.getLong("size") > 0 ? page.getLong("size") : 10);
        QueryWrapper<T> wrapper = new WrapperUtil<T>().generateQueryWrapper(req.getJSONArray("param"));
        IPage<T> paged = s.page(tPage, wrapper);

        List<T> records = paged.getRecords();
        List result = new ArrayList();
        if (!records.isEmpty()) {
            for (T t : records) {
                Object vo = null;
                Field[] fields = t.getClass().getDeclaredFields();
                Set<Field> joinAnnonationPresentSet = Arrays.stream(fields).filter(r -> r.isAnnotationPresent(JoinTable.class)).collect(Collectors.toSet());
                if (!joinAnnonationPresentSet.isEmpty()) {
                    for (Field field : joinAnnonationPresentSet) {
                        vo = this.selectVOByJoin(field, vo, t);
                    }
                    if (vo != null) {
                        result.add(vo);
                    }
                }
            }
        }

        if (!result.isEmpty()) {
            paged.setRecords(result);
        }
        return Result.okData(paged);
    }

    /**
     * 根据注解插表查询
     *
     * @param field JoinTable注解下的Field
     * @param vo    需要赋值的VO
     * @param t     实体
     * @return 赋值后的vo
     * @throws Exception
     */
    private Object selectVOByJoin(Field field, Object vo, T t) throws Exception {
        JoinTable joinTable = field.getAnnotation(JoinTable.class);
        Class voClass = joinTable.voClass();
        if (vo == null) {
            vo = voClass.newInstance();
        }
        Class joinClass = joinTable.joinClass();
        BeanUtils.copyProperties(t, vo);
        String tabledName = joinTable.tableName();
        String targetField = joinTable.field();
        int mode = joinTable.mode();
        field.setAccessible(true);
        String val = field.get(t) + "";
        boolean inSelect = val.contains(",");
        StringBuilder sql = new StringBuilder(String.format("select * from %s _root where _root.%s ", tabledName, targetField));
        if (inSelect) {
            sql.append("in (");
            for (String s : val.split(",")) {
                sql.append(String.format("'%s',", s));
            }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append(")");
        } else {
            sql.append(String.format("= %s", val));
        }
        MyBaseMapper bean = (MyBaseMapper) SpringContextUtils.getBean("myBaseMapper");
        List objects = new ArrayList<>();
        switch (mode) {
            default:
                break;
            case 1:
                sql.append(" limit 1");
                break;
            case 2:
                sql.append(String.format(" limit %s", Integer.MAX_VALUE));
                break;
        }
        log.info(sql.toString());
        List<Map<String, Object>> resultList = bean.selectList(sql.toString());
        if (!resultList.isEmpty()) {
            for (Map<String, Object> map : resultList) {
                JSONObject jsonObject = new JSONObject(map);
                Object javaObject = jsonObject.toJavaObject(joinClass);
                objects.add(javaObject);
                Field declaredField = voClass.getDeclaredField(field.getName() + "List");
                declaredField.setAccessible(true);
                declaredField.set(vo, objects);
            }
        }
        return vo;
    }


    @RequestMapping("/save")
    public Result save(@RequestBody T t) {
        Objects.requireNonNull(t, "新增对象不能为空");
        s.save(t);
        return Result.ok();
    }

    @RequestMapping("/page")
    public Result page(@RequestBody JSONObject req) {
        JSONObject page = req.getJSONObject("page");
        Page<T> tPage = new Page<>(page.getLong("current") > 0 ? page.getLong("current") : 1,
                page.getLong("size") > 0 ? page.getLong("size") : 10);
        QueryWrapper<T> wrapper = new WrapperUtil<T>().generateQueryWrapper(req.getJSONArray("param"));
        IPage<T> paged = s.page(tPage, wrapper);
        return Result.okData(paged);
    }

    @RequestMapping("/updateById/{id}")
    public Result updateById(@PathVariable("id") String id, @RequestBody T t) {
        T obj = (T) s.getById(id);
        Objects.requireNonNull(obj, String.format("id[%s]找不到对象", id));
        s.updateById(t);
        return Result.ok();
    }

    @RequestMapping("/getById/{id}")
    public Result getById(@PathVariable("id") String id) {
        T obj = (T) s.getById(id);
        if (obj == null) {
            log.error("id{}找不到对象", id);
        }
        return Result.okData(obj);
    }

    @RequestMapping("/delById/{id}")
    public Result delById(@PathVariable("id") Long id) {
        T obj = (T) s.getById(id);
        Objects.requireNonNull(obj, String.format("根据id[%s]找不到对象", id));
        s.removeById(id);
        return Result.ok();
    }

    @RequestMapping("/getByIds")
    public Result getById(@RequestBody JSONArray array) {
        List<String> strings = JSON.parseArray(array.toJSONString(), String.class);
        Collection<T> list = null;
        if (!strings.isEmpty()) {
            list = s.listByIds(strings);
        }
        return Result.okData(list);
    }

    @RequestMapping("list")
    public Result list(@RequestBody(required = false) JSONArray array) {
        List<T> list = null;
        if (array != null) {
            QueryWrapper<T> wrapper = new WrapperUtil<T>().generateQueryWrapper(array);
            list = s.list(wrapper);
        } else {
            list = s.list(new LambdaQueryWrapper<T>());
        }
        return Result.okData(list);
    }

    @RequestMapping("/listVOByJoin")
    public Result listVOByJoin(@RequestBody(required = false) JSONArray array) throws Exception {

        List<T> tempList = null;
        if (array != null) {
            QueryWrapper<T> wrapper = new WrapperUtil<T>().generateQueryWrapper(array);
            tempList = s.list(wrapper);
        } else {
            tempList = s.list(new LambdaQueryWrapper<T>());
        }
        try {
            List resultList = new ArrayList();
            if (!tempList.isEmpty()) {
                for (T t : tempList) {
                    Object vo = null;
                    Field[] declaredFields = t.getClass().getDeclaredFields();
                    Set<Field> joinAnnonationPresentSet = Arrays.stream(declaredFields).filter(r -> r.isAnnotationPresent(JoinTable.class)).collect(Collectors.toSet());
                    if (!joinAnnonationPresentSet.isEmpty()) {
                        for (Field field : joinAnnonationPresentSet) {
                            vo = this.selectVOByJoin(field, vo, t);
                        }
                        resultList.add(vo);
                    }
                }

                return Result.okData(resultList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.okData(tempList);
    }

    @RequestMapping("upload")
    public Result upload(MultipartFile file) {
        String upload = FileUtils.upload(file);
        return Result.okData(upload);
    }


}