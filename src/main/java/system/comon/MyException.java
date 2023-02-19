package system.comon;

/**
 * 自定义的异常
 * @author Nott
 * @Date 2023/2/17
 */


public class MyException extends Exception {
    private String msg; // 异常信息
    private Long retcode; // 异常码


    public Long getRetcode() {
        return retcode;
    }

    public void setRetcode(Long retcode) {
        this.retcode = retcode;
    }


    public MyException(String msg){
        this.msg = msg;
        this.retcode = -999L;
    }


    public MyException(String msg,Long retcode){
        super(msg);
        this.msg = msg;
        this.retcode = retcode;
    }


}
