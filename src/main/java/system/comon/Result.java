package system.comon;

/**
 * @author Nott
 * @Date 2023/2/16
 */

// 通用返回结果对象
public class Result {
    private String msg;
    private Object data;
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

    public static Result ok(){
        Result result = new Result();
        result.setMsg("成功");
        result.setCode("200");
        return result;
    }

    public static Result okData(Object obj){
        Result result = new Result();
        result.setMsg("成功");
        result.setCode("200");
        result.setData(obj);
        return result;
    }

    public static Result fail(){
        Result result = new Result();
        result.setMsg("失败");
        result.setCode("-999");
        return result;
    }

    public static Result fail(String msg){
        Result result = new Result();
        result.setMsg(msg);
        result.setCode("-999");
        return result;
    }


}
