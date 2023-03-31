package system.comon;

/**
 * @author
 * @Date 2023/2/16
 */

// 通用返回结果对象
public class Result {
    // 返回信息
    private String msg;
    // 返回数据
    private Object data;
    // 返回码 成功-200 失败-其他
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public Result() {
    }

    // 返回默认成功信息
    public static Result ok(){
        Result result = new Result();
        result.setMsg("成功");
        result.setCode("200");
        return result;
    }

    // 返回带数据的结果
    public static Result okData(Object obj){
        Result result = new Result();
        result.setMsg("成功");
        result.setCode("200");
        result.setData(obj);
        return result;
    }

    // 返回默认的失败信息
    public static Result fail(){
        Result result = new Result();
        result.setMsg("失败");
        result.setCode("-999");
        return result;
    }

    // 返回自定义的失败信息的结果
    public static Result fail(String msg){
        Result result = new Result();
        result.setMsg(msg);
        result.setCode("-999");
        return result;
    }


}
